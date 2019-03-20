package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    public DBAdapter (Context context) {super(context, "name", null,1);}

    public void onCreate (SQLiteDatabase db){
        String createTable= "CREATE TABLE depense (id integer Primary key, achat text,prix text, date text);";
        db.execSQL(createTable);
    }

    public void onUpgrade (SQLiteDatabase db, int i, int il){
        String deleteTable= "DROP Table IF EXISTS depense;";
        db.execSQL(deleteTable);
        onCreate(db);
    }

    public void ajoutdepense(NewDepense jdayed)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("achat",jdayed.getAchat());
        contentValues.put("prix",jdayed.getPrix());
        contentValues.put("date",jdayed.getDate());

        db.insert("depense",null,contentValues);
    }

    public ArrayList<NewDepense> afficher() {
        SQLiteDatabase db = getReadableDatabase();
        String selectall = "SELECT * FROM depense;";

        Cursor cursor = db.rawQuery(selectall, null);
        ArrayList<NewDepense> depenses = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                NewDepense jdayed = new NewDepense(cursor.getString(1), cursor.getString(2));
                jdayed.setNum(cursor.getInt(0));
                jdayed.setDate(cursor.getString(3));
                depenses.add(jdayed);
            }
            while (cursor.moveToNext());}
        return depenses;
    }

    public void delete(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("depense","id="+Integer.toString(id),null);}

    public Float somme()
    {   float s =0f ;

        SQLiteDatabase db= (SQLiteDatabase) getReadableDatabase();
        String selectall="SELECT * FROM depense";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<NewDepense> depenses=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                s+=cursor.getFloat(2);
            }
            while(cursor.moveToNext());
        }

        return s ;}
}
