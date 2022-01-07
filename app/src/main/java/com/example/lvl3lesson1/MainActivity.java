package com.example.lvl3lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button btn_go;
    private ImageView logo;
    private TextInputLayout passwordlayout,usernamelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernamebody);
        password = findViewById(R.id.passwordbody);
        btn_go = findViewById(R.id.btn_go);
        logo = findViewById(R.id.logo);
        passwordlayout = findViewById(R.id.password);
        usernamelayout = findViewById(R.id.username);
        initListeners();
    }

    private void initListeners() {
        Glide.with(this).load("https://i.imgur.com/PM7tOcS.png").into(logo);
        btn_go.setOnClickListener(view -> {
            if (username.getText().length() > 0){
                usernamelayout.setErrorEnabled(false);
                if (password.getText().length() >= 6 ) {
                    passwordlayout.setErrorEnabled(false);
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    String name = username.getText().toString();
                    intent.putExtra("key", name);
                    String password1 = password.getText().toString();
                    intent.putExtra("key1", password1);
                    startActivity(intent);
                } else if(password.getText().length() < 6){
                    passwordlayout.setErrorEnabled(true);
                    passwordlayout.setError("You need enter 6 symbols ");
                }
            }else{
                usernamelayout.setErrorEnabled(true);
                usernamelayout.setError("the field must not be empty");
            }

        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 ) {
                    usernamelayout.setErrorEnabled(false);
                } else {
                    usernamelayout.setErrorEnabled(true);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6 ) {
                    passwordlayout.setErrorEnabled(false);
                } else {
                    passwordlayout.setErrorEnabled(true);
                }
            }
        });



    }
}
