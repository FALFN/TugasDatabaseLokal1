package com.example.mahasiswapc.tugasdatabselokal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "sekolah_db")
public class DataSekolah {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nama_skl")
    private String nama_skl;

    @ColumnInfo(name = "alamat_skl")
    private String alamat_skl;

    @ColumnInfo(name = "jml_murid")
    private String jml_murid;

    @ColumnInfo(name = "jml_guru")
    private String jml_guru;

    @NonNull
    public int getId() {return id;}
    public void setId(@NonNull int id) {this.id=id;}

    public String getNama_skl() { return nama_skl; }
    public void setNama_skl(String nama_skl) { this.nama_skl = nama_skl; }

    public String getAlamat_skl() { return alamat_skl; }
    public void setAlamat_skl(String alamat_skl) { this.alamat_skl = alamat_skl; }

    public String getJml_murid() { return jml_murid; }
    public void setJml_murid(String jml_murid) { this.jml_murid = jml_murid; }

    public String getJml_guru() { return jml_guru; }
    public void setJml_guru(String jml_guru) { this.jml_guru = jml_guru; }
}
