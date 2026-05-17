/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.*;
import view.GameView;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class GameController {
    GameModel model;
    GameView view;
    public HistoryModel historyModel;

    JButton card1Selected;
    JButton card2Selected;
    int kartuTerbuka = 0; // menghitung berapa pasang kartu sudah terbuka

    Timer hideCardTimer;

    public GameController(GameModel model, GameView view, HistoryModel historyModel) {
        this.model = model;
        this.view = view;
        this.historyModel = historyModel;

        // Timer untuk menyembunyikan kartu yang tidak cocok setelah 1.5 detik
        hideCardTimer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideCards();
            }
        });
        hideCardTimer.setRepeats(false);

        // Pasang listener ke setiap kartu di papan
        for (int i = 0; i < view.board.size(); i++) {
            view.board.get(i).addActionListener(new CardClickListener());
        }

        // Pasang listener ke tombol restart
        view.restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        // Mulai game: tampilkan kartu sebentar lalu sembunyikan
        hideCardTimer.start();
    }

    // Logika saat kartu diklik
    class CardClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!model.gameReady) {
                return; // abaikan klik sebelum game siap
            }

            JButton tile = (JButton) e.getSource();

            // Hanya proses jika kartu masih tertutup
            if (tile.getIcon() == model.cardBackImageIcon) {

                if (card1Selected == null) {
                    // Pilihan pertama: buka kartu
                    card1Selected = tile;
                    int index = view.board.indexOf(card1Selected);
                    card1Selected.setIcon(model.cardSet.get(index).cardImageIcon);

                } else if (card2Selected == null) {
                    // Pilihan kedua: buka kartu
                    card2Selected = tile;
                    int index = view.board.indexOf(card2Selected);
                    card2Selected.setIcon(model.cardSet.get(index).cardImageIcon);

                    // Cek apakah kedua kartu cocok
                    if (card1Selected.getIcon() != card2Selected.getIcon()) {
                        // Tidak cocok: tambah error dan sembunyikan setelah 1.5 detik
                        model.errorCount++;
                        view.updateErrorText(model.errorCount);
                        hideCardTimer.start();
                    } else {
                        // Cocok: reset pilihan, kartu tetap terbuka
                        card1Selected = null;
                        card2Selected = null;
                        kartuTerbuka++;

                        // Cek apakah semua pasang kartu sudah terbuka (game selesai)
                        // Total pasang = jumlah kartu dibagi 2
                        if (kartuTerbuka == model.cardSet.size() / 2) {
                            gameSelesai();
                        }
                    }
                }
            }
        }
    }

    // Dipanggil saat semua kartu berhasil dipasangkan
    void gameSelesai() {
        model.gameReady = false;

        // Ambil tanggal dan durasi sekarang
        String tanggal = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        long durasi = model.getDurasiDetik();

        // Simpan ke histori (CREATE)
        historyModel.tambahHistori(tanggal, model.errorCount, durasi);

        // Tampilkan pesan selamat
        JOptionPane.showMessageDialog(
                view.frame,
                "Selamat! Kamu menang!\n"
                + "Error: " + model.errorCount + "\n"
                + "Durasi: " + durasi + " detik\n\n"
                + "Total histori tersimpan: " + historyModel.getTotalHistori(),
                "Game Selesai",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Menyembunyikan kartu (dipanggil timer atau saat start/restart)
    void hideCards() {
        if (model.gameReady && card1Selected != null && card2Selected != null) {
            // Sembunyikan dua kartu yang tidak cocok
            card1Selected.setIcon(model.cardBackImageIcon);
            card2Selected.setIcon(model.cardBackImageIcon);
            card1Selected = null;
            card2Selected = null;
        } else {
            // Sembunyikan semua kartu (awal game atau restart)
            view.hideAllCards();
            model.gameReady = true;
            model.mulaiTimer(); // mulai hitung durasi saat game siap
            view.restartButton.setEnabled(true);
        }
    }

    // Reset permainan dari awal
    void restartGame() {
        model.gameReady = false;
        view.restartButton.setEnabled(false);
        card1Selected = null;
        card2Selected = null;
        kartuTerbuka = 0; // reset hitungan kartu terbuka

        model.resetGame(); // acak ulang kartu di model
        view.refreshAllCards(); // tampilkan kartu baru di view

        view.updateErrorText(0);
        hideCardTimer.start(); // tampilkan sebentar lalu sembunyikan
    }
}
