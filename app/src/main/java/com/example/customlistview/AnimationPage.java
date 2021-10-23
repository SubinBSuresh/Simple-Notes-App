package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AnimationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_page);

        ImageView imageView = findViewById(R.id.iv_graphics);

        Bundle extras = getIntent().getExtras();
        String action = extras.getString("ActionPerformed");

        switch (action) {
            case "NewNote":
                Glide.with(getApplicationContext())
                        .load(R.drawable.new_file)
                        .into(imageView);
                break;
            case "DeleteNote":
                Glide.with(getApplicationContext())
                        .load(R.drawable.delete)
                        .into(imageView);
                break;
            case "EditNote":
                Glide.with(getApplicationContext())
                        .load(R.drawable.edit)
                        .into(imageView);
                break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(intent);
            }
        }, 1500);

    }
}