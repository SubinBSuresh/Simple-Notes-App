package com.example.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditNotesActivity extends AppCompatActivity {

    String title;
    String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_activity);

        TextView tvEditTitle = findViewById(R.id.tv_edit_Title);
        EditText etEditContent = findViewById(R.id.et_updateContent);
        Button btnEdit = findViewById(R.id.btn_dataUpdate);

        String[] editDetails;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                editDetails = null;
            } else {
                editDetails = extras.getStringArray("EditDetails");
            }
            assert editDetails != null;
            tvEditTitle.setText(editDetails[0]);
            etEditContent.setText(editDetails[1]);
        }

        final DatabaseActivity db = new DatabaseActivity(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = tvEditTitle.getText().toString();
                contents = etEditContent.getText().toString();
                db.updateData(title, contents);
                db.close();
                Intent intent = new Intent(getApplicationContext(), AnimationPage.class);
                intent.putExtra("ActionPerformed", "EditNote");
                startActivity(intent);
            }
        });
    }
}