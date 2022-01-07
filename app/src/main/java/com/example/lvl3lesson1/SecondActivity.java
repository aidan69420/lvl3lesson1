package com.example.lvl3lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private EditText email, password;
    private ImageView profileImage;
    private TextView editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initClick();
        Intent intent = getIntent();
        String name = intent.getStringExtra("key");
        String password1 = intent.getStringExtra("key1");
        email.setText(name);
        password.setText(password1);


    }

    private void initClick() {
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 123);

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 666);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 777);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();

                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                profileImage.setImageBitmap(image);


            } else if (requestCode == 777 && resultCode == RESULT_OK && data != null) {
                Bitmap photo = (Bitmap)data.getExtras().get("data");
                profileImage.setImageBitmap(photo);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        email = findViewById(R.id.emailbody);
        password = findViewById(R.id.passwordbody);
        profileImage = findViewById(R.id.photo);
        editImage = findViewById(R.id.editphoto);

    }

}