package com.example.shopeefood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeefood.databinding.ActivityDangKyBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;




public class DangKyActivity extends AppCompatActivity {

//    FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    ActivityDangKyBinding binding;
    DatabaseReference Userref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        firebaseAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Vui lòng đợi ...!");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {validateData();}});

    }
    private String username = "",namecus = "",sdt= "", email = "", password = "";
    private void validateData() {

        //trước khi tạo tài khoản
        username = binding.usernameDk.getText().toString().trim();
        namecus = binding.cusnameDk.getText().toString().trim();
        sdt = binding.SdtDk.getText().toString().trim();
        email = binding.emailDk.getText().toString().trim();
        password = binding.passDk.getText().toString().trim();


        // kiểm tra dữ liệu
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Vui lòng nhập tên ...!", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không hợp lệ ...!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu ...!", Toast.LENGTH_SHORT).show();
        } else  {
//            createUserAccount();
        }
    }
//    private void createUserAccount() {
//        //show progress
//        progressDialog.setMessage("Đang tạo tài khoản...!");
//        progressDialog.show();
//
//        //tạo tài khoản trong firebase
//        firebaseAuth.createUserWithEmailAndPassword(email,password)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        // tạo tài khoản thành công, thêm vào firebase
//                        updateUserInfo();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(DangKyActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//    private void updateUserInfo() {
//        progressDialog.setMessage("Đang lưu thông tin tài khoản...");
//
//        //timestamp
//        long timestamp = System.currentTimeMillis();
//
//        //lấy id hiện tại của tài khoản, khi tài khoản đã đăng ký
//        String uid = firebaseAuth.getUid();
//        //setup dữ liệu thêm vào database
//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("uid",uid);
//        hashMap.put("email",email);
//        hashMap.put("username",username);
//        hashMap.put("namecus",namecus);
//        hashMap.put("profileImage","");
//        hashMap.put("Tài khoản","khách hàng");// tài khoản này có 2 role là khách hàng và quản trị viên
//        hashMap.put("timestamp",timestamp);
//
//        //setdata to db
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//        ref.child(uid)
//                .setValue(hashMap)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        //data to db
//                        progressDialog.dismiss();;
//                        Toast.makeText(DangKyActivity.this, "Tài khoản đã được tạo... ", Toast.LENGTH_SHORT).show();
//                        //khi tài khoản được tạo sẻ chuyển sang trang chính
//                        startActivity(new Intent(DangKyActivity.this, TrangChuActivity.class));
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
//                        Toast.makeText(DangKyActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
}