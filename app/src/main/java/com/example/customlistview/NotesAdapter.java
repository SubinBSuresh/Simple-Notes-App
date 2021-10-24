package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    Context context;
    ArrayList<NotesHelper> notesList;

    public NotesAdapter(Context context, ArrayList<NotesHelper> notesList) {
        this.context = context;
        this.notesList = notesList;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_listview, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        NotesHelper notesHelper = notesList.get(position);
        holder.tvTitle.setText(notesHelper.getTitle());
        holder.tvContents.setText(notesHelper.getContent());
        holder.tvDate.setText(notesHelper.getDate());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvContents;
        TextView tvDate;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContents = itemView.findViewById(R.id.tv_contents);
            tvDate = itemView.findViewById(R.id.tv_date);

        }
    }
}
