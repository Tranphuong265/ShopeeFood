package com.example.shopeefood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.shopeefood.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtTentk,edtPass;
    AppCompatButton btnDN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDN = findViewById(R.id.nutDn);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TrangChuActivity.class);
                startActivity(i);
            }
        });
    }
}