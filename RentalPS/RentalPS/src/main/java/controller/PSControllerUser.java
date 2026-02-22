package controller;

import dao.PSDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.PS;

public class PSControllerUser {

    @FXML private TableView<PS> tablePS;
    @FXML private TableColumn<PS, Integer> colId;
    @FXML private TableColumn<PS, String> colJenis;
    @FXML private TableColumn<PS, Integer> colHarga;
    @FXML private TableColumn<PS, String> colStatus;

    private final PSDao psDao = new PSDao();

    @FXML
    public void initialize() {
        colJenis.setCellValueFactory(cellData -> cellData.getValue().jenis_psProperty());
        colHarga.setCellValueFactory(cellData -> cellData.getValue().harga_per_jamProperty().asObject());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        loadTable();
    }

    private void loadTable() {
        try {
            ObservableList<PS> list = FXCollections.observableArrayList(psDao.getAll());
            tablePS.setItems(list);
        } catch (Exception e) {
            showError("Gagal memuat data PS!\n" + e.getMessage());
        }
    }

    @FXML
    private void setTersedia() {
        PS ps = tablePS.getSelectionModel().getSelectedItem();

        if (ps == null) {
            alert("Pilih PS terlebih dahulu!");
            return;
        }

        boolean success = psDao.updateStatus(ps.getId_ps(), "tersedia");

        if (success) {
            loadTable();
        } else {
            showError("Gagal memperbarui status!");
        }
    }

    @FXML
    private void setDisewa() {
        PS ps = tablePS.getSelectionModel().getSelectedItem();

        if (ps == null) {
            alert("Pilih PS terlebih dahulu!");
            return;
        }

        boolean success = psDao.updateStatus(ps.getId_ps(), "disewa");

        if (success) {
            loadTable();
        } else {
            showError("Gagal memperbarui status!");
        }
    }
    
    @FXML
    private void handleTableClick() {
        PS selected = tablePS.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Dipilih: " + selected.getJenis_ps());
        }
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.showAndWait();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.showAndWait();
    }
}