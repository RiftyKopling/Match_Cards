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
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class HistoryDAO {

    public boolean tambahHistory(String nama, String tanggal, int errorCount, long durasiDetik) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO game_history (nama, tanggal, error_count, durasi_detik) "
                    + "VALUES ('" + nama + "', '" + tanggal + "', " + errorCount + ", " + durasiDetik + ")";

            int result = stmt.executeUpdate(sql);
            return result > 0;

        } catch (Exception e) {
            System.out.println("Gagal menyimpan history: " + e.getMessage());
            return false;
        }
    }

    // UPDATE - Update data menggunakan Statement
    public boolean updateHistory(int id, String nama, String tanggal, int errorCount, long durasiDetik) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "UPDATE game_history SET nama='" + nama + "', tanggal='" + tanggal + "', "
                    + "error_count=" + errorCount + ", durasi_detik=" + durasiDetik + " WHERE id=" + id;

            int result = stmt.executeUpdate(sql);
            return result > 0;

        } catch (Exception e) {
            System.out.println("Gagal update history: " + e.getMessage());
            return false;
        }
    }

    // DELETE - Hapus data menggunakan Statement
    public boolean deleteHistory(int id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM game_history WHERE id=" + id;

            int result = stmt.executeUpdate(sql);
            return result > 0;

        } catch (Exception e) {
            System.out.println("Gagal menghapus history: " + e.getMessage());
            return false;
        }
    }

    // READ - Ambil semua data
    public ArrayList<GameHistory> getAllHistory() {
        ArrayList<GameHistory> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM game_history ORDER BY id DESC";
            ResultSet rs = stmt.executeQuery(sql);

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
        } catch (Exception e) {
            System.out.println("Gagal mengambil history: " + e.getMessage());
        }
        return list;
    }
}
