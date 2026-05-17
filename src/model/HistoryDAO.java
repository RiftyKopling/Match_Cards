/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.DatabaseConnection;
/**
 *
 * @author HP
 */
public class HistoryDAO {

    public boolean tambahHistory(String nama, String tanggal, int errorCount, long durasiDetik) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return false;
            }

            String sql = "INSERT INTO game_history (nama, tanggal, error_count, durasi_detik) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, tanggal);
            stmt.setInt(3, errorCount);
            stmt.setLong(4, durasiDetik);

            int result = stmt.executeUpdate();
            System.out.println("History berhasil disimpan!");
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan history: " + e.getMessage());
            
        }
    }

    /**
     * READ - Ambil semua history dari database
     */
    public ArrayList<GameHistory> getAllHistory() {
        ArrayList<GameHistory> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return list;
            }

            String sql = "SELECT * FROM game_history ORDER BY created_at DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                GameHistory history = new GameHistory(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tanggal"),
                        rs.getInt("error_count"),
                        rs.getLong("durasi_detik")
                );
                list.add(history);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil history: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }

        return list;
    }

    /**
     * READ - Cari history berdasarkan ID
     */
    public GameHistory getHistoryById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return null;
            }

            String sql = "SELECT * FROM game_history WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new GameHistory(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tanggal"),
                        rs.getInt("error_count"),
                        rs.getLong("durasi_detik")
                );
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari history: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }

        return null;
    }

    /**
     * UPDATE - Update history berdasarkan ID
     */
    public boolean updateHistory(int id, String nama, String tanggal, int errorCount, long durasiDetik) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return false;
            }

            String sql = "UPDATE game_history SET nama=?, tanggal=?, error_count=?, durasi_detik=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, tanggal);
            stmt.setInt(3, errorCount);
            stmt.setLong(4, durasiDetik);
            stmt.setInt(5, id);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Gagal update history: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }
    }

    /**
     * DELETE - Hapus history berdasarkan ID
     */
    public boolean deleteHistory(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return false;
            }

            String sql = "DELETE FROM game_history WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus history: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }
    }

    /**
     * DELETE - Hapus semua history
     */
    public boolean deleteAllHistory() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return false;
            }

            String sql = "DELETE FROM game_history";
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus semua history: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }
    }

    /**
     * Hitung total history
     */
    public int getTotalHistory() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                return 0;
            }

            String sql = "SELECT COUNT(*) as total FROM game_history";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Gagal menghitung history: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Error saat menutup: " + e.getMessage());
            }
        }

        return 0;
    }
}
