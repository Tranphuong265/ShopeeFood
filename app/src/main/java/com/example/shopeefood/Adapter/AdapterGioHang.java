package com.example.shopeefood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeefood.Model.GioHang;
import com.example.shopeefood.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.GioHangViewHolder> {
    Context context;
    List<GioHang>list;
    iClickListener clickListener;

    public AdapterGioHang(List<GioHang> list, iClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemgiohang,parent,false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        GioHang gioHang = list.get(position);
        if (gioHang == null){
            return;
        }
        holder.tvTenmon.setText(gioHang.getTENMON());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.tvGia.setText(String.valueOf(df.format(gioHang.getGIAMON())+" VNĐ"));
        holder.tvSLmua.setText(gioHang.getSLMUA());
        Glide.with(context).load(gioHang.getIMGMON()).into(holder.imgMon);
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return  list.size();
        }
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        TextView tvSLmua,tvTenmon,tvGia,tvGhichu;
        AppCompatButton btnTrusl,btnCongsl;
        ImageView imgMon;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSLmua = itemView.findViewById(R.id.slmua_gh);
            tvTenmon = itemView.findViewById(R.id.tenMon_gh);
            tvGia = itemView.findViewById(R.id.giaMon_gh);
            tvGhichu = itemView.findViewById(R.id.ghichu_gh);
            imgMon = itemView.findViewById(R.id.imgMon_gh);
            btnTrusl = itemView.findViewById(R.id.trusl_gh);
            btnCongsl = itemView.findViewById(R.id.congsl_gh);
        }
    }
}
