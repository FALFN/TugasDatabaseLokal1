package com.example.mahasiswapc.tugasdatabselokal;

import android.view.View;

import java.util.List;

public interface MainContract {

    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataSekolah> list);
        void editData(DataSekolah item);
        void deleteData(DataSekolah item);
    }
    interface presenter {
        void insertData(String nama, String alamat, String siswa, String guru, ddatasekolah database);
        void readData(ddatasekolah database);
        void editData(String nama, String alamat, String siswa, String guru, int id, ddatasekolah database);
        void deleteData(DataSekolah dataDiri, ddatasekolah database);
    }
}

