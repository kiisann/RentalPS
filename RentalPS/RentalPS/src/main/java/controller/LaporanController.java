package controller;

import dao.TransaksiDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Transaksi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LaporanController {
    @FXML private Label lblTotalTransaksi;
    @FXML private Label lblPendapatan;
    @FXML private DatePicker dateAwal;
    @FXML private DatePicker dateAkhir;
    @FXML private TextField txtSearch;
    @FXML private Button btnFilter;
    @FXML private Button btnResetFilter;
    @FXML private Button btnRefresh;
    @FXML private TableView<Transaksi> tableLaporan;
    @FXML private TableColumn<Transaksi, String> colTanggal;
    @FXML private TableColumn<Transaksi, String> colPelanggan;
    @FXML private TableColumn<Transaksi, String> colPs;
    @FXML private TableColumn<Transaksi, Integer> colDurasi;
    @FXML private TableColumn<Transaksi, Integer> colTotal;

    private final TransaksiDao transaksiDao = new TransaksiDao();
    private ObservableList<Transaksi> masterList = FXCollections.observableArrayList();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    public void initialize() {
        setupTable();
        loadData();

        btnFilter.setOnAction(e -> applyFilter());
        btnResetFilter.setOnAction(e -> resetFilter());
        btnRefresh.setOnAction(e -> refreshData());
        txtSearch.textProperty().addListener((obs, oldV, newV) -> applyFilter());
    }

    private String formatRupiah(int val) {
        return "Rp " + java.text.NumberFormat
                .getInstance(java.util.Locale.of("id", "ID"))
                .format(val);
    }

    private void setupTable() {
        colTanggal.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTanggal()));
        colPelanggan.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNamaPelanggan()));
        colPs.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNamaPS()));
        colDurasi.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getDurasi()).asObject());
        colTotal.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getTotal()).asObject());
    }

    private void loadData() {
        List<Transaksi> data = transaksiDao.getAll();
        masterList.setAll(data);
        tableLaporan.setItems(masterList);
        updateStatistic();
    }


    private void updateStatistic() {
        lblTotalTransaksi.setText(String.valueOf(masterList.size()));
        int total = masterList.stream().mapToInt(Transaksi::getTotal).sum();
        lblPendapatan.setText(formatRupiah(total)); 
    }

    private void applyFilter() {
        ObservableList<Transaksi> filtered = FXCollections.observableArrayList(masterList);

        String search = txtSearch.getText().trim().toLowerCase();

        if (!search.isEmpty()) {
            filtered.removeIf(t ->
                    !t.getNamaPelanggan().toLowerCase().contains(search) &&
                    !t.getNamaPS().toLowerCase().contains(search)
            );
        }
        
        LocalDate awal = dateAwal.getValue();
        LocalDate akhir = dateAkhir.getValue();

        if (awal != null && akhir != null) {
            filtered.removeIf(t -> {
                LocalDate tgl = LocalDate.parse(t.getTanggal(), formatter);
                return tgl.isBefore(awal) || tgl.isAfter(akhir);
            });
        }

        tableLaporan.setItems(filtered);
        updateStatisticFiltered(filtered);
    }

    private void updateStatisticFiltered(List<Transaksi> list) {
        lblTotalTransaksi.setText(String.valueOf(list.size()));
        int total = list.stream().mapToInt(Transaksi::getTotal).sum();
        lblPendapatan.setText(formatRupiah(total));
    }

    private void resetFilter() {
        txtSearch.clear();
        dateAwal.setValue(null);
        dateAkhir.setValue(null);

        tableLaporan.setItems(masterList);
        updateStatistic();
    }

    private void refreshData() {
        loadData();
        resetFilter();
    }
}