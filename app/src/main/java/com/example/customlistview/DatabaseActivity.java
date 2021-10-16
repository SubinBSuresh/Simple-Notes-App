package com.example.customlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends SQLiteOpenHelper {

    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";



    private static final String DATABASE_NAME = "NOTESDATABASE";
    private static final String TABLE_NAME = "notes";
    private static final int DATABASE_VERSION = 1;

    //CREATE
    private static final String CREATE_TABLE = "create table notes(title text,content text);";

    //DROP
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS notes";


    //CONSTRUCTOR
    public DatabaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    // DATA INSERTION
    public void insertData(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);

        db.insert(TABLE_NAME, null, cv);
    }


    //Delete a note from a database
    public void delete(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_TITLE + "=?", new String[]{String.valueOf(data)});
    }


    //Update a note
    public void updateData(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, name);
        cv.put(KEY_CONTENT, phone);
        db.update(TABLE_NAME, cv, KEY_TITLE + "=?", new String[]{String.valueOf(name)});
    }



    //Get all the titles from database
    public List<String> getTitle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_TITLE}, null, null, null, null, null);
        final List<String> titles = new ArrayList<>();

        while (cursor.moveToNext()) {
            titles.add(cursor.getString(0));
        }
        cursor.close();
        return titles;
    }

        //Get all the contents from database
        public List<String> getContent () {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_CONTENT}, null, null, null, null, null);
            final List<String> contents = new ArrayList<>();

            while (cursor.moveToNext()) {
                contents.add(cursor.getString(0));
            }
            cursor.close();
            return contents;
        }
    }
