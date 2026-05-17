/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DatabaseConnection;

/**
 *
 * @author HP
 */
public class UserDAO {
    public boolean TambahUser(User user) {
        if (cekNama(user.getNama())) {
            System.out.println("Error: Nama sudah terdaftar!");
            return false;
        }
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO user nama VALUES ('"
                    + user.getNama() + "'";

            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal register: " + e.getMessage());
            return false;
        }
    }

    private boolean cekNama(String nama) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM user WHERE nama = '" + nama + "'";
            ResultSet rs = stmt.executeQuery(sql);

            return rs.next();

        } catch (SQLException e) {
            return true;
        }
    }
}
