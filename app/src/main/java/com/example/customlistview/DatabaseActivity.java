package com.example.customlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    //TAG for LogCat
    private static final String TAG = "DatabaseActivity";
    private static final String DATABASE_NAME = "NOTESDATABASE";
    private static final String TABLE_NAME = "notes";
    private static final int DATABASE_VERSION = 1;

    //CREATE
    private static final String CREATE_TABLE = "create table notes(title text,content text);";

    //DROP
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS notes";
    private final Context context;
    private final DatabaseHelper dbHelper;
    private SQLiteDatabase db;


    public DatabaseActivity(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    // Database Helper
    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //To open the Database in Writable mode so as to write/ insert data
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }

    }

    //To open the Database in Writable mode so as to write/ insert data
    public DatabaseActivity open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //Close the database
    public void close() {
        dbHelper.close();
    }

    // DATA INSERTION
    public void insertData(String title, String content) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(CONTENT, content);

        db.insert(TABLE_NAME, null, cv);
    }


    //Delete a note from a database
    public boolean deleteOne(String title) {
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, TITLE + "=?", new String[]{String.valueOf(title)});
        db.close();
        return false;
    }


    //Update a note
    public void updateData(String title, String content) {
        db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(CONTENT, content);
        db.update(TABLE_NAME, cv, TITLE + "=?", new String[]{String.valueOf(title)});
    }


    //Get all the titles from database
    public List<String> getTitle() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{TITLE}, null, null, null, null, null);

        final List<String> titles = new ArrayList<>();

        while (cursor.moveToNext()) {
            titles.add(cursor.getString(0));
        }
        cursor.close();
        return titles;
    }

    //Get all the contents from database
    public List<String> getContent() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{CONTENT}, null, null, null, null, null);
        final List<String> contents = new ArrayList<>();

        while (cursor.moveToNext()) {
            contents.add(cursor.getString(0));
        }
        cursor.close();
        return contents;
    }

}
