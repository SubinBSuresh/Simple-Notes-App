package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseActivity db = new DatabaseActivity(this);
        List<String> title = db.getTitle();
        List<String> content = db.getContent();

        com.example.customlistview.ListviewActivity adapter = new com.example.customlistview.ListviewActivity(this, title, content);
        listView = findViewById(R.id.lv_listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String[] details = {title.get(position), content.get(position)};
                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                intent.putExtra("Details", details);
                startActivity(intent);
            }
        });

        FloatingActionButton fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new) {
            Toast.makeText(getApplicationContext(), "Going to next activity", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}