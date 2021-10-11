package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;





public class Splash extends AppCompatActivity {
    private final static int SPLASH_TIME_OUT = 1700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        TextView tvSplash = findViewById(R.id.tv_splash);
        Animation splashAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_bounce);


        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.splash_screen)
                .into(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,MainActivity.class));

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

