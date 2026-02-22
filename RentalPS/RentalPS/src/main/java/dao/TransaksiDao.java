package dao;

import config.Database;
import model.Transaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDao {

    public void insert(Transaksi t, int idPs) {
        String sql = "INSERT INTO transaksi (nama_pelanggan, id_ps, durasi, total, tanggal) VALUES (?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getNamaPelanggan());
            ps.setInt(2, idPs);
            ps.setInt(3, t.getDurasi());
            ps.setInt(4, t.getTotal());
            ps.setString(5, t.getTanggal());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Transaksi> getAll() {
        List<Transaksi> list = new ArrayList<>();
        String sql =
                "SELECT t.id_transaksi, t.nama_pelanggan, ps.jenis_ps AS ps, " +
                "t.durasi, t.total, t.tanggal " +
                "FROM transaksi t " +
                "JOIN ps ps ON t.id_ps = ps.id_ps " +
                "ORDER BY t.id_transaksi DESC";

        try (Connection c = Database.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                Transaksi t = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("ps"),
                        rs.getInt("durasi"),
                        rs.getInt("total"),
                        rs.getString("tanggal")
                );
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getPendapatanBulanIni() {
        String sql =
                "SELECT COALESCE(SUM(total),0) AS total " +
                "FROM transaksi " +
                "WHERE MONTH(tanggal)=MONTH(CURDATE()) " +
                "AND YEAR(tanggal)=YEAR(CURDATE())";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPendapatanMingguIni() {
        String sql =
                "SELECT COALESCE(SUM(total),0) AS total " +
                "FROM transaksi " +
                "WHERE YEARWEEK(tanggal,1)=YEARWEEK(CURDATE(),1)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTransaksiBulanIni() {
        String sql =
                "SELECT COUNT(*) AS jumlah " +
                "FROM transaksi " +
                "WHERE MONTH(tanggal)=MONTH(CURDATE()) " +
                "AND YEAR(tanggal)=YEAR(CURDATE())";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("jumlah");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPelangganBulanIni() {
        String sql =
                "SELECT COUNT(DISTINCT nama_pelanggan) AS jumlah " +
                "FROM transaksi " +
                "WHERE MONTH(tanggal)=MONTH(CURDATE()) " +
                "AND YEAR(tanggal)=YEAR(CURDATE())";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("jumlah");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTransaksiMingguIni() {
    String sql =
            "SELECT COUNT(*) AS jumlah " +
            "FROM transaksi " +
            "WHERE YEARWEEK(tanggal,1)=YEARWEEK(CURDATE(),1)";

    try (Connection c = Database.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("jumlah");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
    }
}