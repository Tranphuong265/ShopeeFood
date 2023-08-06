package com.example.shopeefood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.shopeefood.Adapter.AdapterMon;
import com.example.shopeefood.Adapter.MyBottomSheetFragment;
import com.example.shopeefood.Adapter.OnItemClickListener;
import com.example.shopeefood.Adapter.iClickListener;
import com.example.shopeefood.Model.GioHang;
import com.example.shopeefood.Model.Mon;
import com.example.shopeefood.Model.Quan;
import com.example.shopeefood.R;
import com.example.shopeefood.Utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

public class CTQuanActivity extends AppCompatActivity implements OnItemClickListener {
    TextView tvQuanName;
    int idQuan;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageView imgQuan;
    AdapterMon adapterMon;
    ArrayList<Mon> listmon;
    RecyclerView dsmonpb;
    LinearLayout giohang;
    AppCompatButton btngiaohang;

    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctquan);



        tvQuanName = findViewById(R.id.nameQuan);
        imgQuan = findViewById(R.id.imgQuan);
        tabLayout = findViewById(R.id.Tab_ctquan);
        viewPager = findViewById(R.id.vp_ctquan);
        dsmonpb = findViewById(R.id.dspb);

        Quan quan = (Quan) getIntent().getSerializableExtra("ct");
        idQuan = getIntent().getIntExtra("idq",1);
        tvQuanName.setText(quan.getTENQ());
//        Glide.with(getApplicationContext()).load(quan.getIMGQ()).into(imgQuan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        dsmonpb.setLayoutManager(layoutManager);
        dsmonpb.setHasFixedSize(true);
        getQuanpb();
    }
//    private void getdsMonpb(){
//        cd.add(apiShopeeFood.getMon()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        monModel -> {
//                            if (monModel.isSuccess()){
//                                listmon = monModel.getResult();
//                                adapterMon = new AdapterMon(getApplicationContext(),listmon);
//                                dsmonpb.setAdapter(adapterMon);
//                            }
//                        }
//                ));
//    }
    private void getQuanpb(){
        listmon = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Mon");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear list
                listmon.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    //get data
                    Mon model = ds.getValue(Mon.class);
                    //add to List
                    listmon.add(model);
                }
                //set adapter
                adapterMon = new AdapterMon(getApplicationContext(),listmon,CTQuanActivity.this);
                //set adapter to recycler view
                dsmonpb.setAdapter(adapterMon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        BottomSheetDialog dialog = new BottomSheetDialog(CTQuanActivity.this);
        View view = LayoutInflater.from(CTQuanActivity.this).inflate(R.layout.bs_giaohang,null);
        giohang = view.findViewById(R.id.layout_giohang);
        btngiaohang = view.findViewById(R.id.btngiaohang);
        badge = view.findViewById(R.id.menu_slmua);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btngiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CTQuanActivity.this, XacNhanDHActivity.class);
                startActivity(i);
            }
        });
        if (Utils.manggiohang != null){
            badge.setText(String.valueOf(Utils.manggiohang.size()));
        }
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        dialog.show();
    }

    public void showBSgiohang(List<GioHang>list){
        MyBottomSheetFragment bs = new MyBottomSheetFragment(list, new iClickListener() {
            @Override
            public void clickItem(GioHang giohang) {
//                clickListener.clickItem(giohang);
            }
        });
        bs.show(getSupportFragmentManager(),bs.getTag());
    }
}