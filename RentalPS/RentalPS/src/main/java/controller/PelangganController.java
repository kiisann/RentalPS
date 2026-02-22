//package controller;
//
//import dao.PelangganDao;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import model.Pelanggan;
//
//public class PelangganController {
//
//    @FXML private TextField txtNama;
//    @FXML private TextField txtNoHp;
//
//    @FXML private TableView<Pelanggan> tablePelanggan;
//    @FXML private TableColumn<Pelanggan,Integer> colId;
//    @FXML private TableColumn<Pelanggan,String> colNama;
//    @FXML private TableColumn<Pelanggan,String> colNoHp;
//
//    private final PelangganDao dao = new PelangganDao();
//
//    @FXML
//    public void initialize() {
//        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId_pelanggan()).asObject());
//        colNama.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNama()));
//        colNoHp.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNo_hp()));
//
//        loadTable();
//    }
//
//    private void loadTable() {
//        ObservableList<Pelanggan> list = FXCollections.observableArrayList(dao.getAll());
//        tablePelanggan.setItems(list);
//    }
//
//    @FXML
//    private void simpan() {
//        if (txtNama.getText().isEmpty() || txtNoHp.getText().isEmpty()) {
//            alert("Lengkapi data!");
//            return;
//        }
//
//        Pelanggan p = new Pelanggan();
//        p.setNama(txtNama.getText());
//        p.setNo_hp(txtNoHp.getText());
//        dao.insert(p);
//
//        loadTable();
//        reset();
//    }
//
//    @FXML
//    private void update() {
//        Pelanggan selected = tablePelanggan.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//            alert("Pilih data dulu!");
//            return;
//        }
//
//        selected.setNama(txtNama.getText());
//        selected.setNo_hp(txtNoHp.getText());
//        dao.update(selected);
//
//        loadTable();
//        reset();
//    }
//
//    @FXML
//    private void hapus() {
//        Pelanggan selected = tablePelanggan.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//            alert("Pilih data dulu!");
//            return;
//        }
//
//        dao.delete(selected.getId_pelanggan());
//        loadTable();
//        reset();
//    }
//
//    @FXML
//    private void reset() {
//        txtNama.clear();
//        txtNoHp.clear();
//        tablePelanggan.getSelectionModel().clearSelection();
//    }
//
//    @FXML
//    private void handleTableClick() {
//        Pelanggan p = tablePelanggan.getSelectionModel().getSelectedItem();
//        if (p != null) {
//            txtNama.setText(p.getNama());
//            txtNoHp.setText(p.getNo_hp());
//        }
//    }
//
//    private void alert(String msg) {
//        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
//        a.showAndWait();
//    }
//}