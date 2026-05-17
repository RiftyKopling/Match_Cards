/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class HistoryModel {
    private ArrayList<GameHistory> daftarHistori = new ArrayList<GameHistory>();
    private int idBerikutnya = 1; // auto increment sederhana

    // ========================
    // CREATE - Tambah histori baru
    // ========================
    public void tambahHistori(String tanggal, int errorCount, long durasiDetik) {
        GameHistory history = new GameHistory(idBerikutnya, tanggal, errorCount, durasiDetik);
        daftarHistori.add(history);
        idBerikutnya++;
    }

    // ========================
    // READ - Ambil semua histori
    // ========================
    public ArrayList<GameHistory> getSemuaHistori() {
        return daftarHistori;
    }

    // READ - Cari histori berdasarkan ID
    public GameHistory getHistoriById(int id) {
        for (GameHistory history : daftarHistori) {
            if (history.id == id) {
                return history;
            }
        }
        return null; // tidak ditemukan
    }

    // ========================
    // UPDATE - Ubah data histori
    // ========================
    public boolean updateHistori(int id, String tanggalBaru, int errorBaru, long durasiBaru) {
        GameHistory history = getHistoriById(id);

        if (history != null) {
            history.tanggal = tanggalBaru;
            history.errorCount = errorBaru;
            history.durasiDetik = durasiBaru;
            return true; // berhasil
        }

        return false; // id tidak ditemukan
    }

    // ========================
    // DELETE - Hapus histori
    // ========================
    public boolean hapusHistori(int id) {
        GameHistory history = getHistoriById(id);

        if (history != null) {
            daftarHistori.remove(history);
            return true; // berhasil
        }

        return false; // id tidak ditemukan
    }

    // Hapus semua histori sekaligus
    public void hapusSemuaHistori() {
        daftarHistori.clear();
    }

    // Cek apakah histori kosong
    public int getTotalHistori() {
        return daftarHistori.size();
    }
}
