package com.ahmed.aziz.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "lists";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CHECKED = "checked";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TITLE + " TEXT, " + KEY_CHECKED + " INTEGER" + ")";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public ArrayList<CheckList> getLists(){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<CheckList> lists = new ArrayList<CheckList>();
        CheckList list;
        int id;
        String title;
        boolean isChecked;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
                title = cursor.getString(1);
                isChecked = intToBoolean(cursor.getInt(2));

                list = new CheckList(id, title, isChecked);
                lists.add(list);
            }while (cursor.moveToNext());
        }

        db.close();

        return lists;
    }


    public void setList(CheckList list){
        String title = list.getTitle();
        int isChecked = booleanToInt(list.isChecked());
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_CHECKED, isChecked);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null, values);
        db.close();
    }


    public void updateList(CheckList list){
        int id = list.getId();
        String title = list.getTitle();
        int isChecked = booleanToInt(list.isChecked());
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_TITLE, title);
        values.put(KEY_CHECKED, isChecked);
        Log.d("aziz", Integer.toString(isChecked));

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + KEY_TITLE + " = \""
                + title + "\" , " + KEY_CHECKED + " = " + isChecked +
                " WHERE " + KEY_ID + " = " + id;

        db.execSQL(query);
        db.close();
    }






    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_CHECKED + " = 1 ");

        db.close();
    }


    private boolean intToBoolean(int a){
        if(a==0) return false;
        else return true;
    }

    private int booleanToInt(boolean b){
        if(b) return 1;
        else return 0;
    }
}
