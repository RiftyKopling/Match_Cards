/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.GameModel;
import view.GameView;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class GameController {
    GameModel model;
    GameView view;

    JButton card1Selected;
    JButton card2Selected;

    Timer hideCardTimer;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

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
                    }
                }
            }
        }
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
            view.restartButton.setEnabled(true);
        }
    }

    // Reset permainan dari awal
    void restartGame() {
        model.gameReady = false;
        view.restartButton.setEnabled(false);
        card1Selected = null;
        card2Selected = null;

        model.resetGame(); // acak ulang kartu di model
        view.refreshAllCards(); // tampilkan kartu baru di view

        view.updateErrorText(0);
        hideCardTimer.start(); // tampilkan sebentar lalu sembunyikan
    }
}
