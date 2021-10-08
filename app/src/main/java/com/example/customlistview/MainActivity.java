package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] title = {"Create a note", "Choose Templates", "Create folders", "Hide notes", "Make remainders","Plan lessons","Make a timetable"};
        String[] subtitle = {
                "Tap the plus to create a note.",
                "Use templates to change backgrounds and styles of your notes.",
                "Organize your notes in folders.",
                "Protect private notes with a password.",
                "Set remainder to keep up",
                "Plan ahead of your goals",
                "Time what you are about to do"};
        String[] date = {"17 August 2020","29 June 2019","27 November 2020","1 April 2021","30 January 2019","4 December 2020","29 September 2021"};


        CustomListAdapter adapter = new CustomListAdapter(this, title, subtitle, date);

        listView = (ListView) findViewById(R.id.lv_listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] details = {title[position],subtitle[position],date[position]};
                Intent intent = new Intent(MainActivity.this,DetailedPage.class);
                intent.putExtra("Details",details);
                startActivity(intent);
            }
        });
    }
}