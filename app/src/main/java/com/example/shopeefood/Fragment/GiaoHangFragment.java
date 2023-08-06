package com.example.shopeefood.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.shopeefood.Activity.XacNhanDHActivity;
import com.example.shopeefood.Adapter.AdapterGioHang;
import com.example.shopeefood.Adapter.MyBottomSheetFragment;
import com.example.shopeefood.Adapter.iClickListener;
import com.example.shopeefood.Model.GioHang;
import com.example.shopeefood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GiaoHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GiaoHangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    List<GioHang> list;
    iClickListener clickListener;
    LinearLayout giohang;
    AppCompatButton btngiaohang;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GiaoHangFragment() {
    }

    public GiaoHangFragment(List<GioHang> list, iClickListener clickListener) {
        // Required empty public constructor
        this.list = list;
        this.clickListener = clickListener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GiaoHangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GiaoHangFragment newInstance(String param1, String param2) {
        GiaoHangFragment fragment = new GiaoHangFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bs_giaohang, container, false);
        giohang = view.findViewById(R.id.layout_giohang);
        btngiaohang = view.findViewById(R.id.btngiaohang);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBottomSheetFragment bs = new MyBottomSheetFragment(list, new iClickListener() {
                    @Override
                    public void clickItem(GioHang giohang) {
                        clickListener.clickItem(giohang);
                    }
                });
                bs.show(getActivity().getSupportFragmentManager(),bs.getTag());
            }
        });
        btngiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), XacNhanDHActivity.class);
                getActivity().startActivity(i);
            }
        });
        return view;
    }
}