package com.example.mahasiswapc.tugasdatabselokal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Adapterer extends RecyclerView.Adapter<Adapterer.viewHolder> {
    Context context;
    List<DataSekolah> list;
    MainContract.view view;

    public Adapterer(Context context, List<DataSekolah> list, MainContract.view view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvAlamat, tvSiswa,tvGuru , id;
        CardView cardView;

        public viewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_namasekolah);
            tvAlamat = itemView.findViewById(R.id.tv_item_alamat);
            tvSiswa = itemView.findViewById(R.id.tv_item_jumlahsiswa);
            tvGuru = itemView.findViewById(R.id.tv_item_jumlahguru);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }

    @NonNull
    @Override
    public Adapterer.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsekolah, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final Adapterer.viewHolder holder, int position) {
        final DataSekolah item = list.get(position);
        holder.tvAlamat.setText(item.getAlamat_skl());
        holder.tvSiswa.setText(item.getJml_murid());
        holder.tvGuru.setText(item.getJml_guru());
        holder.tvNama.setText(item.getNama_skl());
        holder.id.setText(String.valueOf(item.getId()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
