/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.GameModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class GameView {
    public GameModel model;

    // Komponen UI
    public JFrame frame = new JFrame("Poker Match Cards");
    public JLabel textLabel = new JLabel();
    public JPanel textPanel = new JPanel();
    public JPanel boardPanel = new JPanel();
    public JPanel restartPanel = new JPanel();
    public JButton restartButton = new JButton("Restart");

    public ArrayList<JButton> board = new ArrayList<JButton>();

    public int boardWidth;
    public int boardHeight;

    public GameView(GameModel model) {
        this.model = model;

        boardWidth = model.columns * model.cardWidth;
        boardHeight = model.rows * model.cardHeight;

        setupFrame();
        setupErrorLabel();
        setupBoard();
        setupRestartButton();

        frame.pack();
        frame.setVisible(true);
    }

    // Membuat jendela utama
    void setupFrame() {
        frame.setLayout(new BorderLayout());
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Membuat label error di atas
    void setupErrorLabel() {
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors : 0");
        textPanel.setPreferredSize(new Dimension(boardWidth, 30));
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
    }

    // Membuat papan kartu
    void setupBoard() {
        boardPanel.setLayout(new GridLayout(model.rows, model.columns));

        for (int i = 0; i < model.cardSet.size(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(model.cardWidth, model.cardHeight));
            tile.setOpaque(true);
            tile.setIcon(model.cardSet.get(i).cardImageIcon); // tampilkan gambar kartu
            tile.setFocusable(false);
            board.add(tile);
            boardPanel.add(tile);
        }

        frame.add(boardPanel);
    }

    // Membuat tombol restart di bawah
    void setupRestartButton() {
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        restartPanel.add(restartButton);
        frame.add(restartPanel, BorderLayout.SOUTH);
    }

    // Update tampilan error
    public void updateErrorText(int errorCount) {
        textLabel.setText("Errors : " + errorCount);
    }

    // Sembunyikan semua kartu (balik ke belakang)
    public void hideAllCards() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(model.cardBackImageIcon);
        }
    }

    // Refresh tampilan semua kartu sesuai data model (untuk restart)
    public void refreshAllCards() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(model.cardSet.get(i).cardImageIcon);
        }
    }
}
