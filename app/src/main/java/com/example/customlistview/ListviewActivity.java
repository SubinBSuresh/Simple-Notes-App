package com.example.customlistview;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListviewActivity extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> title;
    private final List<String> content;

    public ListviewActivity(Activity context, List<String> title, List<String> content) {
        super(context, R.layout.activity_listview, title);
        this.context = context;
        this.title = title;
        this.content = content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.activity_listview, null, true);
        TextView tvTitle = row.findViewById(R.id.tv_title);
        TextView tvContents = row.findViewById(R.id.tv_contents);

        String trimmedContent = content.get(position);
        if (content.get(position).length() > 40) {
            trimmedContent = trimmedContent.substring(0, 30) + "  . . .";
        }
        tvTitle.setText(title.get(position));
        tvContents.setText(trimmedContent);
        return row;
    }
}

