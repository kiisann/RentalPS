package model;

import javafx.beans.property.*;

public class PS {

    private final IntegerProperty id_ps = new SimpleIntegerProperty();
    private final StringProperty jenis_ps = new SimpleStringProperty();
    private final IntegerProperty harga_per_jam = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();

    public int getId_ps() {
        return id_ps.get();
    }

    public void setId_ps(int id_ps) {
        this.id_ps.set(id_ps);
    }

    public IntegerProperty id_psProperty() {
        return id_ps;
    }

    public String getJenis_ps() {
        return jenis_ps.get();
    }

    public void setJenis_ps(String jenis_ps) {
        this.jenis_ps.set(jenis_ps);
    }

    public StringProperty jenis_psProperty() {
        return jenis_ps;
    }

    public int getHarga_per_jam() {
        return harga_per_jam.get();
    }

    public void setHarga_per_jam(int harga_per_jam) {
        this.harga_per_jam.set(harga_per_jam);
    }

    public IntegerProperty harga_per_jamProperty() {
        return harga_per_jam;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    @Override
    public String toString() {
        return jenis_ps.get();
    }
}