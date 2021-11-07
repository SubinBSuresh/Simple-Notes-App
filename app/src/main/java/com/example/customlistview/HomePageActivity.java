package com.example.customlistview;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    GridView listView;
    Button btnNew;

    ArrayList<NotesHelper> notesHelperArrayList;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        btnNew = findViewById(R.id.btn_new);

        listView = findViewById(R.id.rv_recyclerView);
        notesHelperArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Notes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        NotesHelper notesHelper = d.toObject(NotesHelper.class);
                        notesHelperArrayList.add(notesHelper);
                    }
                    NotesAdapter adapter = new NotesAdapter(getApplicationContext(), notesHelperArrayList);

                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "No data found in database", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });


        //New Button
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddNotesActivity.class));
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"This item is clicked",Toast.LENGTH_SHORT).show();
            }
        });

        //Longclick listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Dialog deleteDialog = new Dialog(getApplicationContext());
                deleteDialog.setContentView(R.layout.delete_dialog);
                deleteDialog.setCancelable(false);
                Button btnDeleteConfirm = deleteDialog.findViewById(R.id.btn_confirmDialog);
                Button btnDeleteCancel = deleteDialog.findViewById(R.id.btn_deleteCancel);
                deleteDialog.show();
                //Confirm delete
                btnDeleteConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                //Cancel delete
                btnDeleteCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.cancel();
                    }
                });

                return false;
            }
        });


    }
}