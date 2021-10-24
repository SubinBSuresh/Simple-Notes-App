package com.example.customlistview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    NotesAdapter notesAdapter;
    ArrayList<NotesHelper> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        recyclerView = findViewById(R.id.rv_recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Notes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        notesAdapter = new NotesAdapter(this, list);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    //Sort data
                    Collections.sort(list, new Comparator<NotesHelper>() {
                        @Override
                        public int compare(NotesHelper o1, NotesHelper o2) {
                            return o1.title.compareToIgnoreCase(o2.title);
                        }
                    });
                    //Reverse the list
//                    Collections.reverse(list);
                    NotesHelper notesHelper = dataSnapshot.getValue(NotesHelper.class);
                    list.add(notesHelper);
                }
                notesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button btnNew = findViewById(R.id.btn_new);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });
    }
}