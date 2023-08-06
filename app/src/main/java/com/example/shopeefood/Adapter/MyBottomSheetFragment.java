package com.example.shopeefood.Adapter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeefood.Model.GioHang;
import com.example.shopeefood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    List<GioHang>list;
    iClickListener clickListener;

    public MyBottomSheetFragment(List<GioHang> list, iClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog =(BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bs_giohang,null);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(view);

        RecyclerView rcvData = view.findViewById(R.id.listgiohang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(layoutManager);

        AdapterGioHang adapterGioHang =new AdapterGioHang(list,new iClickListener(){
            @Override
            public void clickItem(GioHang giohang) {
                clickListener.clickItem(giohang);
            }
        });
        rcvData.setAdapter(adapterGioHang);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return  bottomSheetDialog;

    }
}
