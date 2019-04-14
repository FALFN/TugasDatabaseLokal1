package com.example.mahasiswapc.tugasdatabselokal;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.view {
    private ddatasekolah appDatabase;
    private Presenterer presenter;
    private Adapterer adapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText Namaskl, Alamatskl, Jml_murid, Jml_guru;

    private char gender;
    private boolean edit = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = ddatasekolah.iniDb(getApplicationContext());

        btnOK = findViewById(R.id.btn_submit);
        btnOK.setOnClickListener(this);
        Namaskl = findViewById(R.id.namaskl);
        Alamatskl = findViewById(R.id.alamatskl);
        Jml_murid = findViewById(R.id.jumlahmurid);
        Jml_guru = findViewById(R.id.jumlahguru);
        recyclerView = findViewById(R.id.rc_main);

        /*FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        presenter = new Presenterer(this);

        presenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        Namaskl.setText("");
        Alamatskl.setText("");
        Jml_murid.setText("");
        Jml_guru.setText("");
        btnOK.setText("submit");
    }

    @Override
    public void getData(List<DataSekolah> list) {
        adapter = new Adapterer(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void editData(DataSekolah item) {
        Namaskl.setText(item.getNama_skl());
        Alamatskl.setText(item.getAlamat_skl());
        Jml_murid.setText(item.getJml_murid());
        Jml_guru.setText(item.getJml_guru());
        id = item.getId();
        edit = true;
        btnOK.setText("Update");
    }

    @Override
    public void deleteData(final DataSekolah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View view) {
        if(view == btnOK){
            if(Namaskl.getText().toString().equals("") || Alamatskl.getText().toString().equals("") || Jml_murid.getText().toString().equals("") || Jml_guru.getText().toString().equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            }

                if(!edit) presenter.insertData(Namaskl.getText().toString(), Alamatskl.getText().toString(), Jml_murid.getText().toString(), Jml_guru.getText().toString(), appDatabase);
                else{
                    presenter.editData(Namaskl.getText().toString(), Alamatskl.getText().toString(), Jml_murid.getText().toString(), Jml_guru.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
}

