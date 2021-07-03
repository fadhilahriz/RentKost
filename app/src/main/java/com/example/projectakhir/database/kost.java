package com.example.projectakhir.database;

import java.io.Serializable;

public class kost implements Serializable {
    String nama,telpon,identitas,tipekamar,tipesewa,kode;

    public kost(){

    }

    public kost(String nama, String telpon, String identitas, String tipekamar, String tipesewa) {
        this.nama = nama;
        this.telpon = telpon;
        this.identitas = identitas;
        this.tipekamar = tipekamar;
        this.tipesewa = tipesewa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getTipekamar() {
        return tipekamar;
    }

    public void setTipekamar(String tipekamar) {
        this.tipekamar = tipekamar;
    }

    public String getTipesewa() {
        return tipesewa;
    }

    public void setTipesewa(String tipesewa) {
        this.tipesewa = tipesewa;
    }

    public String getKode() { return kode; }

    public void setKode(String kode) { this.kode = kode; }

    @Override
    public String toString() {
        return "kost{" +
                "nama='" + nama + '\'' +
                ", telpon='" + telpon + '\'' +
                ", identitas='" + telpon + '\'' +
                ", tipekamar='" + telpon + '\'' +
                ", tipesewa='" + telpon + '\'' +
                '}';
    }
}
