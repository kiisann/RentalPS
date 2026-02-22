package dao;

import config.Database;
import model.PS;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSDao {
    
    public List<PS> getAll() {
        List<PS> list = new ArrayList<>();
        String sql = "SELECT * FROM ps ORDER BY id_ps ASC";

        try (Connection c = Database.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                PS ps = new PS();
                ps.setId_ps(rs.getInt("id_ps"));
                ps.setJenis_ps(rs.getString("jenis_ps"));
                ps.setHarga_per_jam(rs.getInt("harga_per_jam"));
                ps.setStatus(rs.getString("status"));
                list.add(ps);
            }

        } catch (SQLException e) {
            System.err.println("Error getAll PS: " + e.getMessage());
        }

        return list;
    }

    public boolean insert(PS ps) {
        String sql = "INSERT INTO ps (jenis_ps, harga_per_jam, status) VALUES (?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, ps.getJenis_ps());
            p.setInt(2, ps.getHarga_per_jam());
            p.setString(3, ps.getStatus());

            return p.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error insert PS: " + e.getMessage());
            return false;
        }
    }

    public boolean update(PS ps) {
        String sql = "UPDATE ps SET jenis_ps=?, harga_per_jam=?, status=? WHERE id_ps=?";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, ps.getJenis_ps());
            p.setInt(2, ps.getHarga_per_jam());
            p.setString(3, ps.getStatus());
            p.setInt(4, ps.getId_ps());

            return p.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error update PS: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM ps WHERE id_ps=?";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, id);
            return p.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error delete PS: " + e.getMessage());
            return false;
        }
    }

    public PS getById(int id) {
        String sql = "SELECT * FROM ps WHERE id_ps=?";
        PS ps = null;

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    ps = new PS();
                    ps.setId_ps(rs.getInt("id_ps"));
                    ps.setJenis_ps(rs.getString("jenis_ps"));
                    ps.setHarga_per_jam(rs.getInt("harga_per_jam"));
                    ps.setStatus(rs.getString("status"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getById PS: " + e.getMessage());
        }

        return ps;
    }

    public List<PS> getAllAvailable() {
        List<PS> list = new ArrayList<>();
        String sql = "SELECT * FROM ps WHERE status='tersedia' ORDER BY id_ps ASC";

        try (Connection c = Database.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                PS ps = new PS();
                ps.setId_ps(rs.getInt("id_ps"));
                ps.setJenis_ps(rs.getString("jenis_ps"));
                ps.setHarga_per_jam(rs.getInt("harga_per_jam"));
                ps.setStatus(rs.getString("status"));
                list.add(ps);
            }

        } catch (SQLException e) {
            System.err.println("Error getAllAvailable PS: " + e.getMessage());
        }

        return list;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE ps SET status=? WHERE id_ps=?";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, status);
            p.setInt(2, id);
            return p.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updateStatus PS: " + e.getMessage());
            return false;
        }
    }

    public int getJumlahPSTersedia() {
        String sql = "SELECT COUNT(*) AS jumlah FROM ps WHERE status='tersedia'";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("jumlah");
            }

        } catch (SQLException e) {
            System.err.println("Error getJumlahPSTersedia: " + e.getMessage());
        }

        return 0;
    }

    public int getJumlahPSDisewa() {
        String sql = "SELECT COUNT(*) AS jumlah FROM ps WHERE status='disewa'";

        try (Connection c = Database.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("jumlah");
            }

        } catch (SQLException e) {
            System.err.println("Error getJumlahPSDisewa: " + e.getMessage());
        }

        return 0;
    }
    
    public int getTotalPS() {
    String sql = "SELECT COUNT(*) AS jumlah FROM ps";

    try (Connection c = Database.getConnection();
         PreparedStatement p = c.prepareStatement(sql);
         ResultSet rs = p.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("jumlah");
        }

    } catch (SQLException e) {
        System.err.println("Error getTotalPS: " + e.getMessage());
    }

    return 0;
}

}