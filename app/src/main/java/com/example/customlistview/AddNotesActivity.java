package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNotesActivity extends AppCompatActivity {

    String title;
    String contents;
    String date;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_activity);

        EditText etTitle = findViewById(R.id.tv_edit_Title);
        EditText etContent = findViewById(R.id.et_updateContent);
        Button btnSave = findViewById(R.id.btn_dataUpdate);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Notes");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = etTitle.getText().toString();
                contents = etContent.getText().toString();
                date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
                if (title.isEmpty() || contents.isEmpty()) {
                    if (title.isEmpty()) {
                        Toast.makeText(AddNotesActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddNotesActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    NotesHelper notesHelper = new NotesHelper(title, contents, date);
                    databaseReference.child(title).setValue(notesHelper);

                    Intent intent = new Intent(getApplicationContext(), AnimationPage.class);
                    intent.putExtra("ActionPerformed", "NewNote");
                    startActivity(intent);
                }
            }
        });
    }
}