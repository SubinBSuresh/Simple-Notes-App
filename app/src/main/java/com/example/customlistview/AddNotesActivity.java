package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNotesActivity extends AppCompatActivity {

    String title;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_activity);

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
                        Toast.makeText(AddNotesActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddNotesActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    db.insertData(title, contents);
                    db.close();
                    Intent intent = new Intent(getApplicationContext(), AnimationPage.class);
                    intent.putExtra("ActionPerformed", "NewNote");
                    startActivity(intent);
                }
            }
        });
    }
}