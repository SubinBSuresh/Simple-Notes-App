package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<NotesHelper> {

    Context context;
    ArrayList<NotesHelper> notesList;

    // constructor for our list view adapter.
    public NotesAdapter(@NonNull Context context, ArrayList<NotesHelper> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItemView = convertView;
            if(listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview,parent,false);
            }

            NotesHelper notesHelper = getItem(position);
            TextView tvTitle = listItemView.findViewById(R.id.tv_title);
            TextView tvContent = listItemView.findViewById(R.id.tv_contents);
            TextView tvDate = listItemView.findViewById(R.id.tv_date);

            tvTitle.setText(notesHelper.getTitle());
            tvContent.setText(notesHelper.getContent());
            tvDate.setText(notesHelper.getDate());

            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        return listItemView;
    }
}
