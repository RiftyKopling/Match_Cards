/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import javax.swing.ImageIcon;
/**
 *
 * @author HP
 */
public class Card {
    public String cardName;
    public ImageIcon cardImageIcon;

    public Card(String cardName, ImageIcon cardImageIcon) {
        this.cardName = cardName;
        this.cardImageIcon = cardImageIcon;
    }

    public String toString() {
        return cardName;
    }
}
