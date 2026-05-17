/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/matchcards_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Koneksi ke database berhasil!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal! : \n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
}
