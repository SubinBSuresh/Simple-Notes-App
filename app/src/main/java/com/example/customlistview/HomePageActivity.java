package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class HomePageActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        final DatabaseActivity db = new DatabaseActivity(this);
        List<String> title = db.getTitle();
        List<String> content = db.getContent();


        ListviewActivity adapter = new ListviewActivity(this, title, content);

        gridView = findViewById(R.id.gv_gridView);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String[] details = {title.get(position), content.get(position)};
                Intent intent = new Intent(HomePageActivity.this, ViewNotesActivity.class);
                intent.putExtra("Details", details);
                startActivity(intent);
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