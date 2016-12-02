package com.example.michelle.todomanylists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-11-2016.
 * Database helper
 */

class DBhelper extends SQLiteOpenHelper {
    // Set fields of database schema
    private String DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo_table";

    private String todo_string = "todo_string";
    private String is_checked = "is_checked";

    // Constructor
    DBhelper(Context context, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.DATABASE_NAME = DATABASE_NAME;
    }

    // onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Query
        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT , "
                + todo_string + " TEXT, " + is_checked + " BOOLEAN )";
        db.execSQL(CREATE_TABLE);
    }

    // onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // CRUD methods

    // Create
    void create(ToDo_item item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(todo_string, item.todo_string);
        values.put(is_checked, item.is_checked);

        db.insert(TABLE, null, values);
        db.close();
    }

    // Read
    ArrayList<ToDo_item> read() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ToDo_item> todo_list = new ArrayList<>();

        String query = "SELECT _id , " + todo_string + " , " + is_checked + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String todo_string = cursor.getString(cursor.getColumnIndex(this.todo_string));
            Boolean checked = cursor.getInt(cursor.getColumnIndex(this.is_checked)) > 0;

            ToDo_item item = new ToDo_item(id, todo_string, checked);
            todo_list.add(item);
        }

        cursor.close();
        db.close();
        return todo_list;
    }

    // update
    void update(ToDo_item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(is_checked, item.is_checked);

        db.update(TABLE, values, " _id = ? ", new String[]{String.valueOf(item.id)});
        db.close();
    }

    // delete
    void delete(ToDo_item item) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " _id = ? ", new String[]{String.valueOf(item.id)});
        db.close();
    }

    void clear() {
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT _id , " + todo_string + " , " + is_checked + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.delete(TABLE, " _id = ? ", new String[]{String.valueOf(id)});
        }

        cursor.close();
        db.close();
    }
}