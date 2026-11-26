import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public List<Mahasiswa> getAll() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT id, nim, nama, jurusan FROM mahasiswa ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mahasiswa m = new Mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jurusan")
                );
                list.add(m);
            }
        }
        return list;
    }

    public boolean insert(Mahasiswa m) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nim, nama, jurusan) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Memulai transaction secara manual

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, m.getNim());
                ps.setString(2, m.getNama());
                ps.setString(3, m.getJurusan());

                int affected = ps.executeUpdate();
                conn.commit(); // Commit transaction jika berhasil
                return affected > 0;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction jika terjadi kesalahan
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public boolean update(Mahasiswa m) throws SQLException {
        String sql = "UPDATE mahasiswa SET nim = ?, nama = ?, jurusan = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Memulai transaction

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, m.getNim());
                ps.setString(2, m.getNama());
                ps.setString(3, m.getJurusan());
                ps.setInt(4, m.getId());

                int affected = ps.executeUpdate();
                conn.commit(); // Commit transaction
                return affected > 0;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE id = ?";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Memulai transaction

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);

                int affected = ps.executeUpdate();
                conn.commit(); // Commit transaction
                return affected > 0;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
