/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.User;
import model.UserDAO;

/**
 *
 * @author HP
 */
public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }
    public boolean Start(String nama) {
        // validasi tidak boleh kosong
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        User user = new User(nama);
        boolean berhasil = UserDAO.TambahUser(user);

        if (berhasil) {
            JOptionPane.showMessageDialog(null, "Registrasi Berhasil! Silakan Login.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Registrasi Gagal! Username sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
