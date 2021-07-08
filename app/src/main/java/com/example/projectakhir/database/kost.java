package com.example.projectakhir.database;

import java.io.Serializable;

public class kost implements Serializable { //pembuatan class bernama kost dan ditambahkan serializble
    String nama,telpon,identitas,tipekamar,tipesewa,kode; // Pendeklarasian variabel string

    public kost(){

    }

    public kost(String nama, String telpon, String identitas, String tipekamar, String tipesewa) { //Pembuatan Konstruktor
        this.nama = nama;
        this.telpon = telpon;
        this.identitas = identitas;
        this.tipekamar = tipekamar;
        this.tipesewa = tipesewa;
    }

    public String getNama() {
        return nama;
    } //Pembuatan method getter untuk menggambil nilai nama

    public void setNama(String nama) {
        this.nama = nama;
    } //Pembuatan method setter untuk memberikan nilai nama

    public String getTelpon() {
        return telpon;
    } //Pembuatan method getter untuk menggambil nilai telpon

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    } //Pembuatan method setter untuk memberikan nilai telpon

    public String getIdentitas() {
        return identitas;
    } //Pembuatan method getter untuk menggambil nilai identitas

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    } //Pembuatan method setter untuk memberikan nilai identitas

    public String getTipekamar() {
        return tipekamar;
    } //Pembuatan method getter untuk menggambil nilai tipekamar

    public void setTipekamar(String tipekamar) {
        this.tipekamar = tipekamar;
    } //Pembuatan method setter untuk memberikan nilai tipekamar

    public String getTipesewa() {
        return tipesewa;
    } //Pembuatan method getter untuk menggambil nilai tipesewa

    public void setTipesewa(String tipesewa) {
        this.tipesewa = tipesewa;
    } //Pembuatan method setter untuk memberikan nilai tipesewa

    public String getKode() {
        return kode;
    } //Pembuatan method getter untuk menggambil nilai kode

    public void setKode(String kode) {
        this.kode = kode;
    } //Pembuatan method setter untuk memberikan nilai kode

    @Override
    public String toString() {
        return "kost{" +
                "nama='" + nama + '\'' +
                ", telpon='" + telpon + '\'' +
                ", identitas='" + telpon + '\'' +
                ", tipekamar='" + telpon + '\'' +
                ", tipesewa='" + telpon + '\'' +
                '}';
    } //pembuatan method string yang akan mengembalikan nilai
}
