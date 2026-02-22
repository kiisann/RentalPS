package controller;

import dao.PSDao;
import dao.TransaksiDao;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.PS;
import model.Transaksi;

public class TransaksiController {

    @FXML private TextField txtNamaPelanggan;
    @FXML private ComboBox<PS> cbPS;
    @FXML private TextField txtDurasi;
    @FXML private TextField txtTotal;
    @FXML private TableView<Transaksi> tableTransaksi;
    @FXML private TableColumn<Transaksi,String> colPelanggan;
    @FXML private TableColumn<Transaksi,String> colPs;
    @FXML private TableColumn<Transaksi,Integer> colDurasi;
    @FXML private TableColumn<Transaksi,Integer> colTotal;
    @FXML private DatePicker dateTanggal;
    @FXML private TableColumn<Transaksi, String> colTanggal;

    private final PSDao psDao = new PSDao();
    private final TransaksiDao transaksiDao = new TransaksiDao();

    @FXML
    public void initialize() {
        txtDurasi.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));
        
        ObservableList<PS> semuaPS = FXCollections.observableArrayList(psDao.getAll());
        ObservableList<PS> psTersedia = semuaPS.filtered(ps -> ps.getStatus().equalsIgnoreCase("TERSEDIA"));

        cbPS.setItems(psTersedia);
        cbPS.setConverter(new StringConverter<>() {
            @Override public String toString(PS p) { return p == null ? "" : p.getJenis_ps(); }
            @Override public PS fromString(String s) { return null; }
        });

        colPelanggan.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNamaPelanggan()));
        colPs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNamaPS()));
        colDurasi.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getDurasi()).asObject());
        colTotal.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getTotal()).asObject());
        colTanggal.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTanggal()));

        loadTable();
    }

    private void loadTable() {
        ObservableList<Transaksi> list = FXCollections.observableArrayList(transaksiDao.getAll());
        tableTransaksi.setItems(list);
    }

    @FXML
    private void hitungTotal() {
        if (cbPS.getValue() == null) {
            alert("Pilih PS terlebih dahulu!");
            return;
        }

        if (txtDurasi.getText().isEmpty()) {
            alert("Durasi tidak boleh kosong!");
            return;
        }
        int durasi = Integer.parseInt(txtDurasi.getText());
        int harga = cbPS.getValue().getHarga_per_jam();
        int total = durasi * harga;
        txtTotal.setText(String.valueOf(total));
    }

    @FXML
    private void simpanTransaksi() {
        if (txtNamaPelanggan.getText().isEmpty() ||
            cbPS.getValue() == null ||
            txtDurasi.getText().isEmpty() ||
            txtTotal.getText().isEmpty()) {
            alert("Lengkapi semua data!");
            return;
        }
        
        if (dateTanggal.getValue() == null) {
            alert("Pilih tanggal!");
        return;
        }

        String tanggal = dateTanggal.getValue().toString();


        Transaksi t = new Transaksi(
                0,
                txtNamaPelanggan.getText(),
                cbPS.getValue().getJenis_ps(),
                Integer.parseInt(txtDurasi.getText()),
                Integer.parseInt(txtTotal.getText()),
                tanggal
        );
        t.setTanggal(tanggal);

        transaksiDao.insert(t, cbPS.getValue().getId_ps());

        alert("Transaksi berhasil disimpan!");
        loadTable();
        reset();
    }

    @FXML
    private void reset() {
        txtNamaPelanggan.clear();
        cbPS.getSelectionModel().clearSelection();
        txtDurasi.clear();
        txtTotal.clear();
        dateTanggal.setValue(null);
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.showAndWait();
    }
}