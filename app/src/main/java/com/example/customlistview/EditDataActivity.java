package com.example.customlistview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class EditDataActivity extends AppCompatActivity {

    String title;
    String contents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        TextView tvEditTitle = findViewById(R.id.tv_edit_Title);
        EditText etEditContent = findViewById(R.id.et_updateContent);
        Button btnEdit = findViewById(R.id.btn_dataUpdate);

        String[] editDetails;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                editDetails = null;
            } else {
                editDetails = extras.getStringArray("EditDetails");
            }

            assert editDetails != null;
            tvEditTitle.setText(editDetails[0]);
            etEditContent.setText(editDetails[1]);

        }

        final DatabaseActivity db = new DatabaseActivity(this);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = tvEditTitle.getText().toString();
                contents = etEditContent.getText().toString();

                db.updateData(title, contents);
                db.close();


                btnEdit.setVisibility(View.INVISIBLE);
                ImageView imageView = findViewById(R.id.iv_edit);
                ConstraintLayout constraintLayout = findViewById(R.id.edit_body);
                constraintLayout.setBackgroundColor(Color.WHITE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext())
                        .load(R.drawable.save)
                        .into(imageView);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(EditDataActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 1200);

            }

        });

    }
}