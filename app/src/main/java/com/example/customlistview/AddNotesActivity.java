package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNotesActivity extends AppCompatActivity {

    String title;
    String contents;
    String date;

    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_activity);

        EditText etTitle = findViewById(R.id.tv_edit_Title);
        EditText etContent = findViewById(R.id.et_updateContent);
        Button btnSave = findViewById(R.id.btn_dataUpdate);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("Notes");


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
                    collectionReference.add(notesHelper).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Note Created", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Task Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent = new Intent(getApplicationContext(), AnimationPage.class);
                    intent.putExtra("ActionPerformed", "NewNote");
                    startActivity(intent);
                }
            }
        });
    }
}