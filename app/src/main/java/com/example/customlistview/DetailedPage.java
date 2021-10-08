package com.example.customlistview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_page_activity);

        TextView tvTitleDetailed = findViewById(R.id.own_title);
        TextView tvSubtitleDetailed = findViewById(R.id.own_subtitle);
        TextView tvDateDetailed = findViewById(R.id.own_date);

        String[] ownDetails;

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                ownDetails=null;
            }else{
                ownDetails=extras.getStringArray("Details");
            }

            assert ownDetails != null;
            tvTitleDetailed.setText(ownDetails[0]);
            tvSubtitleDetailed.setText(ownDetails[1]);
            tvDateDetailed.setText(ownDetails[2]);


        }
    }
}
