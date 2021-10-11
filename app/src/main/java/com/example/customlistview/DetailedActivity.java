package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

                    db.open();
                    db.insertData(title,contents);
                    db.close();
                    Toast.makeText(DetailedActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailedActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}