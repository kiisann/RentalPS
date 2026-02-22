package model;

class BaseTransaksi {
    public int hitungTotal(int durasi, int hargaPerJam) {
        return durasi * hargaPerJam;
    }
}

public class Transaksi extends BaseTransaksi {
    private int id_transaksi;
    private String namaPelanggan;
    private String namaPS;
    private int durasi;
    private int total;
    private String tanggal;

    public Transaksi() {}

    public Transaksi(int id_transaksi,
                     String namaPelanggan,
                     String namaPS,
                     int durasi,
                     int total,
                     String tanggal) {

        this.id_transaksi = id_transaksi;
        this.namaPelanggan = namaPelanggan;
        this.namaPS = namaPS;
        this.durasi = durasi;
        this.total = total;
        this.tanggal = tanggal;
    }

    @Override
    public int hitungTotal(int durasi, int hargaPerJam) {
        return durasi * hargaPerJam;
    }

    public int getId_transaksi() { 
        return id_transaksi; 
    }
    public void setId_transaksi(int id_transaksi) { 
        this.id_transaksi = id_transaksi; 
    }

    public String getNamaPelanggan() { 
        return namaPelanggan; 
    }
    public void setNamaPelanggan(String namaPelanggan) { 
        this.namaPelanggan = namaPelanggan; 
    }

    public String getNamaPS() { 
        return namaPS; 
    }
    public void setNamaPS(String namaPS) { 
        this.namaPS = namaPS; 
    }

    public int getDurasi() { 
        return durasi; 
    }
    public void setDurasi(int durasi) { 
        this.durasi = durasi; 
    }

    public int getTotal() { 
        return total; 
    }
    public void setTotal(int total) { 
        this.total = total; 
    }
    
    public String getTanggal() { 
        return tanggal; 
    }
    public void setTanggal(String tanggal) { 
        this.tanggal = tanggal; 
    }
}