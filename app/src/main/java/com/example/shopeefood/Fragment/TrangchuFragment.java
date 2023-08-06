package com.example.shopeefood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopeefood.Activity.MainActivity;
import com.example.shopeefood.Adapter.AdapterMonTC;
import com.example.shopeefood.Adapter.AdapterQuan;
import com.example.shopeefood.Model.Mon;
import com.example.shopeefood.Model.Quan;
import com.example.shopeefood.R;
import com.example.shopeefood.Utils.Utils;
import com.example.shopeefood.databinding.FragmentTrangchuBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangchuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangchuFragment extends Fragment {
    RecyclerView dsQuan,dsMon;
    ViewFlipper viewFlipper;
    String uid;

    ListView listView;
    AdapterQuan adapterQuan;
    ArrayList<Quan>listquan;
    ArrayList<Mon>listmon;
    AdapterMonTC adapterMonTC;
//    ApiShopeeFood apiShopeeFood;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();;
    FragmentTrangchuBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangchuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangchuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangchuFragment newInstance(String param1, String param2) {
        TrangchuFragment fragment = new TrangchuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
//        apiShopeeFood = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopeeFood.class);
        viewFlipper = view.findViewById(R.id.quangcao);
        dsQuan = view.findViewById(R.id.dsTopquan);
        dsMon = view.findViewById(R.id.dsbanchay);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getlistQuan();
        getlistMon();
        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        dsMon.setLayoutManager(layoutManager1);
        dsMon.setHasFixedSize(true);
        dsQuan.setLayoutManager(layoutManager);
        dsQuan.setHasFixedSize(true);
        Quangcao();
//        getdsQuanTop();

        return view;
    }
    private void Quangcao(){
        List<String>quangcao = new ArrayList<>();
        quangcao.add("https://i.ytimg.com/vi/cY0rHIw52kU/maxresdefault.jpg");
        quangcao.add("https://bmdsolutions.vn/wp-content/uploads/2022/10/shopeefood-khuyen-mai-88K-21-1-2022.png");
        quangcao.add("https://cdn.tuoitrethudo.com.vn/stores/news_dataimages/quanngocly/062022/24/15/78665e778df9fb6fae195651ab1604b7.jpg?rt=20220624155154");
        for (int i = 0; i<quangcao.size();i++){
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
    }
    private void getlistMon(){
        listmon = new ArrayList<>();
        DatabaseReference ref = firebaseDatabase.getReference("Mon");
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
                adapterMonTC = new AdapterMonTC(getContext(),listmon);
                //set adapter to recycler view
                dsMon.setAdapter(adapterMonTC);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getlistQuan(){
        listquan = new ArrayList<>();
        DatabaseReference ref = firebaseDatabase.getReference("Quan");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear list
                listquan.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    //get data
                    Quan model = ds.getValue(Quan.class);
                    //add to List
                    listquan.add(model);
                }
                //set adapter
                adapterQuan = new AdapterQuan(getContext(),listquan);
                //set adapter to recycler view
                dsQuan.setAdapter(adapterQuan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkUser() {
        //tài khoản hiện tại
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            //không login , quay lại màn hình chính
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }
//    private void getdsQuanTop(){
//        cd.add(apiShopeeFood.getQuan()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        quanModel -> {
//                            if (quanModel.isSuccess()){
//                                listquan = quanModel.getResult();
//                                adapterQuan = new AdapterQuan(getActivity(),listquan);
//                                dsQuan.setAdapter(adapterQuan);
//                            }
//                        }
//                ));
//    }
//    private void getdsMon(){
//        cd.add(apiShopeeFood.getMon()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        monModel -> {
//                            if (monModel.isSuccess()){
//                                listmon = monModel.getResult();
//                                adapterMonTC = new AdapterMonTC(getActivity(),listmon);
//                                dsMon.setAdapter(adapterMonTC);
//                            }
//                        }
//                ));
//    }

}