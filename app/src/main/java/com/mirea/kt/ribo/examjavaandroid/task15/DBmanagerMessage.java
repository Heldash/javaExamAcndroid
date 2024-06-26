package com.mirea.kt.ribo.examjavaandroid.task15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBmanagerMessage {
    private SQLHelper sqlHelper;

    public DBmanagerMessage() {

    }

    public DBmanagerMessage(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.query("Message",null,
                null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                Message mes = new Message();
                mes.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                mes.setText(cursor.getString(cursor.getColumnIndexOrThrow("message_text")));
                mes.setDatetime(cursor.getString(cursor.getColumnIndexOrThrow("datetime")));
                mes.setWatched(cursor.getInt(cursor.getColumnIndexOrThrow("watched")));
                messages.add(mes);
            }while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }

    public boolean putMessage(Message message){
        if (getMessage(message.getId())!=null) {
            SQLiteDatabase db = sqlHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("id", message.getId());
            cv.put("message_text", message.getText());
            cv.put("datetime", message.getDatetime());
            cv.put("watched", message.getWatched());
            long result = db.insert("Message", null, cv);
            cv.clear();
            db.close();
            return result == -1;
        }else{
            return false;
        }
    }
    public Message getMessage(int id){
        Message message = new Message();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.query("Message",null,
                "id = ?",new String[]{""+id},null,null,null);
        if (cursor.moveToFirst()){
            message.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            message.setText(cursor.getString(cursor.getColumnIndexOrThrow("message_text")));
            message.setDatetime(cursor.getString(cursor.getColumnIndexOrThrow("datetime")));
            message.setWatched(cursor.getInt(cursor.getColumnIndexOrThrow("watched")));
        }
        cursor.close();
        db.close();
        return message;
    }
    public void changeWatched(int id,int statusWathed){
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("watched",statusWathed);
        db.update("Message",cv,"id = ?",new String[]{""+id});
        cv.clear();
        db.close();
    }
}
