package com.example.customlistview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class DetailedActivity extends AppCompatActivity {

    String title;
    String contents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        EditText etTitle = findViewById(R.id.tv_edit_Title);
        EditText etContent = findViewById(R.id.et_updateContent);
        Button btnSave = findViewById(R.id.btn_dataUpdate);

        final DatabaseActivity db = new DatabaseActivity(this);

        ImageView imageView = findViewById(R.id.iv_save);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = etTitle.getText().toString();
                contents = etContent.getText().toString();

                if (title.isEmpty() || contents.isEmpty()) {
                    if (title.isEmpty()) {
                        Toast.makeText(DetailedActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailedActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    db.insertData(title, contents);
                    db.close();
                    btnSave.setVisibility(View.INVISIBLE);
                    ImageView imageView = findViewById(R.id.iv_save);
                    ConstraintLayout constraintLayout = findViewById(R.id.save_body);
                    constraintLayout.setBackgroundColor(Color.WHITE);
                    imageView.setVisibility(View.VISIBLE);
                    Glide.with(getApplicationContext())
                            .load(R.drawable.edit)
                            .into(imageView);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DetailedActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }, 1200);


                }
            }
        });

    }
}