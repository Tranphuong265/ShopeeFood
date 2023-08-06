package com.example.shopeefood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeefood.Activity.CTQuanActivity;
import com.example.shopeefood.Model.Mon;
import com.example.shopeefood.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterMonTC extends RecyclerView.Adapter<AdapterMonTC.MonViewHolder> {
    Context context;
    ArrayList<Mon> listMon;

    public AdapterMonTC(Context context, ArrayList<Mon> listMon) {
        this.context = context;
        this.listMon = listMon;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lauyout_itemtcmon,parent,false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        Mon mon = listMon.get(position);
        if (mon == null){
            return;
        }
//        String uri ="https://i.pinimg.com/736x/7c/b5/49/7cb5492889809cb8303b76b80759f0df.jpg";
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.tvMontcName.setText(mon.getTENMON());
        holder.tvMontcPrice.setText(df.format(mon.getGIAMON())+" VNĐ");
        Glide.with(context).load(mon.getIMGMON()).into(holder.imgMontc);
//        Glide.with(context).load(uri).into(holder.imgMontc);

    }

    @Override
    public int getItemCount() {
        if (listMon != null){
            return listMon.size();
        }
        return 0;
    }

    public class MonViewHolder extends RecyclerView.ViewHolder{
        TextView tvMontcName,tvMontcPrice;
        ImageView imgMontc;
        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMontcName = itemView.findViewById(R.id.tvMonName_tc);
            tvMontcPrice = itemView.findViewById(R.id.tvMonPrice_tc);
            imgMontc = itemView.findViewById(R.id.imgMon_tc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ict= new Intent(context, CTQuanActivity.class);
                    ict.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(ict);
                }
            });
        }
    }
}
