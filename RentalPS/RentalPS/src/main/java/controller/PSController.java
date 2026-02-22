package controller;

import dao.PSDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.PS;

public class PSController {

    @FXML
    private TextField txtJenisPS;

    @FXML
    private TextField txtHarga;

    @FXML
    private ComboBox<String> cbStatus;

    @FXML
    private TableView<PS> tablePS;

    @FXML
    private TableColumn<PS, Integer> colId;

    @FXML
    private TableColumn<PS, String> colJenis;

    @FXML
    private TableColumn<PS, Integer> colHarga;

    @FXML
    private TableColumn<PS, String> colStatus;

    private PSDao dao = new PSDao();
    private ObservableList<PS> data;

    private int selectedId = -1;

    @FXML
    public void initialize() {
        cbStatus.setItems(FXCollections.observableArrayList("tersedia", "disewa"));
        colJenis.setCellValueFactory(cell -> cell.getValue().jenis_psProperty());
        colHarga.setCellValueFactory(cell -> cell.getValue().harga_per_jamProperty().asObject());
        colStatus.setCellValueFactory(cell -> cell.getValue().statusProperty());
        
        loadTable();
    }

    private void loadTable() {
        data = FXCollections.observableArrayList(dao.getAll());
        tablePS.setItems(data);
    }

    @FXML
    public void simpan() {
        if (!validate()) return;

        PS ps = new PS();
        ps.setJenis_ps(txtJenisPS.getText());
        ps.setHarga_per_jam(Integer.parseInt(txtHarga.getText()));
        ps.setStatus(cbStatus.getValue());

        if (dao.insert(ps)) {
            alertInfo("Berhasil", "Data berhasil ditambahkan!");
            reset();
            loadTable();
        } else {
            alertError("Gagal", "Gagal menambahkan data!");
        }
    }
    
    @FXML
    public void update() {
        if (selectedId == -1) {
            alertError("Error", "Silakan pilih data terlebih dahulu!");
            return;
        }

        if (!validate()) return;

        PS ps = new PS();
        ps.setId_ps(selectedId);
        ps.setJenis_ps(txtJenisPS.getText());
        ps.setHarga_per_jam(Integer.parseInt(txtHarga.getText()));
        ps.setStatus(cbStatus.getValue());

        if (dao.update(ps)) {
            alertInfo("Berhasil", "Data berhasil diupdate!");
            reset();
            loadTable();
        } else {
            alertError("Gagal", "Gagal update data!");
        }
    }

    @FXML
    public void hapus() {
        if (selectedId == -1) {
            alertError("Error", "Silakan pilih data dulu!");
            return;
        }

        if (dao.delete(selectedId)) {
            alertInfo("Berhasil", "Data berhasil dihapus!");
            reset();
            loadTable();
        } else {
            alertError("Gagal", "Gagal menghapus data!");
        }
    }

    @FXML
    public void handleTableClick() {
        PS ps = tablePS.getSelectionModel().getSelectedItem();

        if (ps != null) {
            selectedId = ps.getId_ps();
            txtJenisPS.setText(ps.getJenis_ps());
            txtHarga.setText(String.valueOf(ps.getHarga_per_jam()));
            cbStatus.setValue(ps.getStatus());
        }
    }

    @FXML
    public void reset() {
        selectedId = -1;
        txtJenisPS.clear();
        txtHarga.clear();
        cbStatus.getSelectionModel().clearSelection();
        tablePS.getSelectionModel().clearSelection();
    }

    private boolean validate() {
        if (txtJenisPS.getText().isEmpty() ||
            txtHarga.getText().isEmpty() ||
            cbStatus.getValue() == null) {

            alertError("Validasi Error", "Semua field harus diisi!");
            return false;
        }

        try {
            Integer.parseInt(txtHarga.getText());
        } catch (NumberFormatException e) {
            alertError("Error", "Harga harus berupa angka!");
            return false;
        }

        return true;
    }

    private void alertInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(msg);
        a.show();
    }

    private void alertError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setContentText(msg);
        a.show();
    }
}