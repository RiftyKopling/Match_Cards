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
    public JLabel namaLabel = new JLabel();
    public JLabel textLabel = new JLabel();
    public JPanel topPanel = new JPanel();
    public JPanel boardPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();
    public JButton restartButton = new JButton("Restart");
    public JButton historyButton = new JButton("Lihat History");

    public ArrayList<JButton> board = new ArrayList<JButton>();

    public int boardWidth;
    public int boardHeight;

    public GameView(GameModel model) {
        this.model = model;

        boardWidth = model.columns * model.cardWidth;
        boardHeight = model.rows * model.cardHeight;

        setupFrame();
        setupTopPanel();
        setupBoard();
        setupBottomPanel();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Membuat jendela utama
     */
    void setupFrame() {
        frame.setLayout(new BorderLayout());
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Membuat panel atas (nama pemain + error)
     */
    void setupTopPanel() {
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.setPreferredSize(new Dimension(boardWidth, 60));

        // Label nama pemain
        namaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        namaLabel.setHorizontalAlignment(JLabel.CENTER);
        namaLabel.setText("Pemain: " + model.namaPemain);

        // Label error
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors: 0");

        topPanel.add(namaLabel);
        topPanel.add(textLabel);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Membuat papan kartu
     */
    void setupBoard() {
        boardPanel.setLayout(new GridLayout(model.rows, model.columns));

        for (int i = 0; i < model.cardSet.size(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(model.cardWidth, model.cardHeight));
            tile.setOpaque(true);
            tile.setIcon(model.cardSet.get(i).cardImageIcon);
            tile.setFocusable(false);
            board.add(tile);
            boardPanel.add(tile);
        }

        frame.add(boardPanel);
    }

    /**
     * Membuat panel bawah (tombol restart + history)
     */
    void setupBottomPanel() {
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0));
        bottomPanel.setPreferredSize(new Dimension(boardWidth, 40));

        // Tombol Restart
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);

        // Tombol History
        historyButton.setFont(new Font("Arial", Font.PLAIN, 16));
        historyButton.setFocusable(false);
        historyButton.setEnabled(false);

        bottomPanel.add(restartButton);
        bottomPanel.add(historyButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Update tampilan error
     */
    public void updateErrorText(int errorCount) {
        textLabel.setText("Errors: " + errorCount);
    }

    /**
     * Sembunyikan semua kartu (balik ke belakang)
     */
    public void hideAllCards() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(model.cardBackImageIcon);
        }
    }

    /**
     * Refresh tampilan semua kartu sesuai data model (untuk restart)
     */
    public void refreshAllCards() {
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(model.cardSet.get(i).cardImageIcon);
        }
    }
}
