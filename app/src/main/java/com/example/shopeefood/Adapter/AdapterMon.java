package com.example.shopeefood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeefood.Activity.CTMonActivity;
import com.example.shopeefood.Activity.CTQuanActivity;
import com.example.shopeefood.Fragment.GiaoHangFragment;
import com.example.shopeefood.Model.GioHang;
import com.example.shopeefood.Model.Mon;
import com.example.shopeefood.R;
import com.example.shopeefood.Utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterMon extends RecyclerView.Adapter<AdapterMon.MonViewHolder> {
    Context context;
    ArrayList<Mon> list;

    OnItemClickListener onItemClickListener;

    public AdapterMon(Context context, ArrayList<Mon> list,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemmon,parent,false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        Mon mon = list.get(position);
        if (mon == null){
            return;
        }
        holder.tvMonName.setText(mon.getTENMON());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.tvMonPrice.setText(String.valueOf(df.format(mon.getGIAMON())+" VNĐ"));
        Glide.with(context).load(mon.getIMGMON()).into(holder.imgMon);
        holder.btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(mon.getMAM());
                if (Utils.manggiohang.size()>0){
                    boolean flag =false;
                    int sl = 1;
                    for (int i = 0; i< Utils.manggiohang.size();i++){
                        if (Utils.manggiohang.get(i).getMAM() == mon.getMAM()){
                            Utils.manggiohang.get(i).setSLMUA(sl+ Utils.manggiohang.get(i).getSLMUA());
                            int gia =mon.getGIAMON()* Utils.manggiohang.get(i).getSLMUA();
                            Utils.manggiohang.get(i).setGIAMON(gia);
                            flag = true;
                        }
                    }
                    if (flag == false){
                        int gia = mon.getGIAMON()*sl;
                        GioHang gioHang = new GioHang();
                        gioHang.setGIAMON(gia);
                        gioHang.setSLMUA(sl);
                        gioHang.setMAM(mon.getMAM());
                        gioHang.setTENMON(mon.getTENMON());
                        gioHang.setIMGMON(mon.getIMGMON());
                        Utils.manggiohang.add(gioHang);
                    }

                }else{
                    int sl = 1;
                    int gia = mon.getGIAMON()*sl;
                    GioHang gioHang = new GioHang();
                    gioHang.setGIAMON(gia);
                    gioHang.setSLMUA(sl);
                    gioHang.setMAM(mon.getMAM());
                    gioHang.setTENMON(mon.getTENMON());
                    gioHang.setIMGMON(mon.getIMGMON());
                    Utils.manggiohang.add(gioHang);
                }

            }
        });

    }

    private void clickOpenBS_giohang(List<GioHang>list){
        Utils.manggiohang = new ArrayList<>();
        MyBottomSheetFragment bottomSheetDialog = new MyBottomSheetFragment(list, new iClickListener() {
            @Override
            public void clickItem(GioHang giohang) {

            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class MonViewHolder extends RecyclerView.ViewHolder{
        TextView tvMonName,tvMonPrice;
        ImageView imgMon;
        AppCompatButton btnDat;
        OnItemClickListener onItemClickListener;

        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonName= itemView.findViewById(R.id.nameMon);
            tvMonPrice = itemView.findViewById(R.id.giaMon);
            imgMon = itemView.findViewById(R.id.imgMon);
            btnDat = itemView.findViewById(R.id.btndat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ictm = new Intent(context, CTMonActivity.class);
                    ictm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(ictm);
                }
            });

        }
        private void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
        private void themgiohang(){

        }
    }
}
