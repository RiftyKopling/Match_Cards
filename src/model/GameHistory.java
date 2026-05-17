/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class GameHistory {
    public int id;
    public String nama;
    public String tanggal;
    public int errorCount;
    public long durasiDetik;

    public GameHistory(int id, String nama, String tanggal, int errorCount, long durasiDetik) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.errorCount = errorCount;
        this.durasiDetik = durasiDetik;
    }
    public int getId(){
        return this.id;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    public String getTanggal(){
        return this.tanggal;
    }
    
    public int getError(){
        return this.errorCount;
    }
    
    public Long getDurasi(){
        return this.durasiDetik;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }

    public String getDurasiFormat() {
        long menit = durasiDetik / 60;
        long detik = durasiDetik % 60;
        return menit + ":" + String.format("%02d", detik);
    }

    public String toString() {
        return "ID:" + id + " | " + nama + " | " + tanggal
                + " | Errors:" + errorCount + " | Durasi:" + getDurasiFormat();
    }
}

