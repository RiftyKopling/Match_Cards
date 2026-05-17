package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Main to edit this template
 */
import controller.GameController;
import model.GameModel;
import view.GameView;

/**
 *
 * @author HP
 */
public class Main {
     public static void main(String[] args) {
        GameModel model = new GameModel();       // 1. Buat data
        GameView view = new GameView(model);     // 2. Buat tampilan
        GameController controller = new GameController(model, view); // 3. Hubungkan keduanya
    }
}
