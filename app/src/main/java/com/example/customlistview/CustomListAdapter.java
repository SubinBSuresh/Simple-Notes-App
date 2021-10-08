package com.example.customlistview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

    public class CustomListAdapter extends ArrayAdapter<String> {
         private final Activity context;
        private final String[] title;
        private final String[] subtitle;
        private final String[] date;

        public CustomListAdapter(Activity context, String[] title, String[] subtitle, String[] date) {
            super(context, R.layout.custom_listview, title);
            this.context = context;
            this.title = title;
            this.subtitle = subtitle;
            this.date = date;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
           @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.custom_listview,null, true);
            TextView tvTitle =(TextView) row.findViewById(R.id.tv_title);
            TextView tvSubtitle =(TextView) row.findViewById(R.id.tv_subtitle);
            TextView tvDate = (TextView)row.findViewById(R.id.tv_date);

            tvTitle.setText(title[position]);
            tvSubtitle.setText(subtitle[position]);
            tvDate.setText(date[position]);

            return row;
        }

    }

