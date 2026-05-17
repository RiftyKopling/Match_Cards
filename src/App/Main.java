package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Main to edit this template
 */
import controller.GameController;
import model.*;
import view.GameView;
import view.StartView;

/**
 *
 * @author HP
 */
public class Main {
     public static void main(String[] args) {
        GameModel model = new GameModel();       // 1. Buat data
         new StartView().setVisible(true);
    }
}
