package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author HP
 */
public class MatchCards {
    class Card{
        String cardName;
        ImageIcon cardImageIcon;
        Card(String cardName, ImageIcon cardImageIcon){
            this.cardName = cardName;
            this.cardImageIcon = cardImageIcon;
        }
        
        public String toString(){
            return cardName;
        }
    }
    
    String[]cardList = {
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    };
    
    int rows = 4;
    int columns = 5;
    int cardWidth = 90;
    int cardHeight = 128;
    
    ArrayList<Card> cardSet; // Membuat dek kartu dengan cardNames dan cardImageIcons
    ImageIcon cardBackImageIcon;
    
    // ui
    int boardWidth = columns * cardWidth;
    int boardHeight = rows * cardHeight;
    
    JFrame frame = new JFrame("Poker Match Cards");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartPanel = new JPanel();
    JButton restartButton = new JButton();
    
    
    int errorCount = 0;
    ArrayList<JButton> board;
    Timer hideCardTimer;
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;
    
    
    
    MatchCards(){
        setupCards();
        shuffleCards();
        
        //frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //error count
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors : " + Integer.toString(errorCount));
        textPanel.setPreferredSize(new Dimension(boardWidth, 30));
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        
        //cardgame board
        board = new ArrayList<JButton>();
        boardPanel.setLayout(new GridLayout(rows, columns));
        for (int i = 0; i < cardSet.size(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
            tile.setOpaque(true);
            tile.setIcon(cardSet.get(i).cardImageIcon);
            tile.setFocusable(false);
            tile.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if (!gameReady){
                        return;
                    }
                    JButton tile = (JButton) e.getSource();
                    if (tile.getIcon() == cardBackImageIcon){
                        if(card1Selected == null){
                            card1Selected = tile;
                            int index = board.indexOf(card1Selected);
                            card1Selected.setIcon(cardSet.get(index).cardImageIcon);
                        }
                        else if(card2Selected == null){
                            card2Selected = tile;
                            int index = board.indexOf(card2Selected);
                            card2Selected.setIcon(cardSet.get(index).cardImageIcon);
                            
                            if(card1Selected.getIcon() != card2Selected.getIcon()){
                                errorCount += 1;
                                textLabel.setText("Errors : " + Integer.toString(errorCount));
                                hideCardTimer.start();
                            }
                            else{
                                card1Selected = null;
                                card2Selected = null;
                            }
                        }
                    }
                }
            });
            board.add(tile);
            boardPanel.add(tile);
        }
        frame.add(boardPanel);
        
        //restart game
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameReady){
                    return;
                }
                
                gameReady = false;
                restartButton.setEnabled(false);
                card1Selected = null;
                card2Selected = null;
                shuffleCards();
                
                // memasang ulang tombol pada kartu baru
                for (int i = 0; i < board.size(); i++) {
                    board.get(i).setIcon(cardSet.get(i).cardImageIcon);
                }
                
                errorCount = 0;
                textLabel.setText("Errors : " + Integer.toString(errorCount));
                hideCardTimer.start();
            }
        });
        restartPanel.add(restartButton);
        frame.add(restartPanel, BorderLayout.SOUTH);
        
        
        
        frame.pack();
        frame.setVisible(true);
        
        // start game
        hideCardTimer = new Timer(1500, new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
              hideCards();
          }
        });
        hideCardTimer.setRepeats(false);
        hideCardTimer.start();
    } 
    
    void setupCards(){
        cardSet = new ArrayList<Card>();
        for(String cardName : cardList){
            // memuat setiap gambar kartu
            Image cardImg = new ImageIcon(getClass().getResource("/img/" + cardName + ".png")).getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
            
            // membuat objek kartu dan menambahkan ke cardSet
            Card card = new Card(cardName, cardImageIcon);
            cardSet.add(card);
        }
        cardSet.addAll(cardSet);
        
        // memuat gambar belakang kartu
        Image cardBackImg = new ImageIcon(getClass().getResource("/img/back.png")).getImage();
        cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
    }
  
    void shuffleCards(){
        System.out.println(cardSet);
        // Mengocok
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
        System.out.println(cardSet);
    }
    
    void hideCards(){
        if(gameReady && card1Selected != null && card2Selected != null){
            card1Selected.setIcon(cardBackImageIcon);
            card1Selected = null;
            card2Selected.setIcon(cardBackImageIcon);
            card2Selected = null;
        }
        else{
            for (int i = 0; i < board.size(); i++) {
                board.get(i).setIcon(cardBackImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
        }
       
    }
}
