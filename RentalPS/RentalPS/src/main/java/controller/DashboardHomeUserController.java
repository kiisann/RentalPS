package controller;

import dao.PSDao;
import dao.TransaksiDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardHomeUserController {

    @FXML private Label lblTransaksiMingguan;
    @FXML private Label lblTransaksiBulanan;
    @FXML private Label lblPelangganBulanan;
    @FXML private Label lblPsTersedia;
    @FXML private Label lblPsDisewa;
    @FXML private Label lblTotalPs;

    private final TransaksiDao transaksiDao = new TransaksiDao();
    private final PSDao psDao = new PSDao();

    @FXML
    public void initialize() {
        updateCards();
    }

    private void updateCards() {
        int transaksiMingguIni = transaksiDao.getTransaksiMingguIni();
        int transaksiBulanIni  = transaksiDao.getTransaksiBulanIni();
        int pelangganBulanIni  = transaksiDao.getPelangganBulanIni();

        int psTersedia = psDao.getJumlahPSTersedia();
        int psDisewa   = psDao.getJumlahPSDisewa();
        int totalPs    = psDao.getTotalPS();

        lblTransaksiMingguan.setText(transaksiMingguIni + " transaksi");
        lblTransaksiBulanan.setText(transaksiBulanIni + " transaksi");
        lblPelangganBulanan.setText(pelangganBulanIni + " pelanggan");
        lblPsTersedia.setText(psTersedia + " unit");
        lblPsDisewa.setText(psDisewa + " unit");
        lblTotalPs.setText(totalPs + " unit");
    }
}