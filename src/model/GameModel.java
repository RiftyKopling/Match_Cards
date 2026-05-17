/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author HP
 */
public class GameModel {
    public int rows = 4;
    public int columns = 5;
    public int cardWidth = 90;
    public int cardHeight = 128;

    public String[] cardList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public ArrayList<Card> cardSet;
    public ImageIcon cardBackImageIcon;

    public int errorCount = 0;
    public boolean gameReady = false;
    
    public long waktuMulai = 0;
    
    public String namaPemain = "";

    public void mulaiTimer() {
        waktuMulai = System.currentTimeMillis();
    }

    public long getDurasiDetik() {
        return (System.currentTimeMillis() - waktuMulai) / 1000;
    }

    public GameModel() {
        setupCards();
        shuffleCards();
    }

    // Membuat dan menduplikat kartu (setiap kartu ada 2)
    public void setupCards() {
        cardSet = new ArrayList<Card>();

        for (String cardName : cardList) {
            Image cardImg = new ImageIcon(getClass().getResource("/img/" + cardName + ".png")).getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));

            Card card = new Card(cardName, cardImageIcon);
            cardSet.add(card);
        }

        cardSet.addAll(cardSet); // duplikat kartu untuk pasangan

        // Gambar belakang kartu
        Image cardBackImg = new ImageIcon(getClass().getResource("/img/back.png")).getImage();
        cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
    }

    // Mengocok kartu secara acak
    public void shuffleCards() {
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    // Reset data untuk mulai ulang
    public void resetGame() {
        errorCount = 0;
        gameReady = false;
        setupCards();
        shuffleCards();
    }
}
