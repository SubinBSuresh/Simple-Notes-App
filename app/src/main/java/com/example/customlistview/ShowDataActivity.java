package com.example.customlistview;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        TextView tvTitleDetailed = findViewById(R.id.tv_edit_Title);
        TextView tvSubtitleDetailed = findViewById(R.id.et_updateContent);
        ImageButton ibDelete = findViewById(R.id.ib_deleteData);
        ImageButton ibEdit = findViewById(R.id.ib_editData);

        final DatabaseActivity db = new DatabaseActivity(this);


        String[] ownDetails;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                ownDetails = null;
            } else {
                ownDetails = extras.getStringArray("Details");
            }

            assert ownDetails != null;
            tvTitleDetailed.setText(ownDetails[0]);
            tvSubtitleDetailed.setText(ownDetails[1]);

        }
        //Edit Data
        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] editDetails = {tvTitleDetailed.getText().toString(), tvSubtitleDetailed.getText().toString()};
                Intent editIntent = new Intent(ShowDataActivity.this, EditDataActivity.class);
                editIntent.putExtra("EditDetails", editDetails);
                startActivity(editIntent);

            }
        });

        //Delete Data
        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(ShowDataActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setCancelable(true);


                Button btnConfirmDialog = dialog.findViewById(R.id.btn_confirmDialog);
                Button btnCancelDialog = dialog.findViewById(R.id.btn_cancelDialog);

                dialog.show();
                btnCancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                btnConfirmDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String data = tvTitleDetailed.getText().toString();

                        db.open();
                        Boolean aBoolean = db.deleteOne(data);
                        if (!aBoolean) {
                            Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Note could not be deleted", Toast.LENGTH_SHORT).show();
                        }
                        db.close();

                        Intent intent = new Intent(ShowDataActivity.this, com.example.customlistview.MainActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });


    }
}