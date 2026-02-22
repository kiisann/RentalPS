package controller;

import dao.PSDao;
import dao.TransaksiDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.NumberFormat;
import java.util.Locale;

public class DashboardHomeController {

    @FXML private Label lblPendapatanBulanan;
    @FXML private Label lblPendapatanMingguan;
    @FXML private Label lblTransaksiBulanan;
    @FXML private Label lblPelangganBulanan;
    @FXML private Label lblPsTersedia;
    @FXML private Label lblPsDisewa;

    private final TransaksiDao transaksiDao = new TransaksiDao();
    private final PSDao psDao = new PSDao();

    @FXML
    public void initialize() {
        updateCards();
    }

    private void updateCards() {

        lblPendapatanBulanan.setText("Rp " + format(transaksiDao.getPendapatanBulanIni()));
        lblPendapatanMingguan.setText("Rp " + format(transaksiDao.getPendapatanMingguIni()));
        lblTransaksiBulanan.setText(transaksiDao.getTransaksiBulanIni() + " transaksi");
        lblPelangganBulanan.setText(transaksiDao.getPelangganBulanIni() + " pelanggan");
        lblPsTersedia.setText(psDao.getJumlahPSTersedia() + " unit");
        lblPsDisewa.setText(psDao.getJumlahPSDisewa() + " unit");
    }

    private String format(int val) {
        return NumberFormat.getInstance(Locale.of("id","ID")).format(val);
    }
}