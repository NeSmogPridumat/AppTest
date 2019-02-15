package com.example.home.mytestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {//создание помощника SQLite

    public static final String DB_NAME = "Data";// название базы данных
    public static final int DB_VERSION = 1;// версия базы данных

    DatabaseHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION); // конструктор SQLite, передаём имя и версию базы данных. null - параметр для использования курсора
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("CREATE TABLE DATA(_id INTEGER PRIMARY KEY AUTOINCREMENT, " //создал таблицу Data с первичным ключом, именем, описанием, id цвета и id картинки
       + "NAME TEXT, "
       + "DESCRIPTION TEXT, "
       + "IMAGE_URL TEXT, "
       + "ID_COLOR INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    static void insertObject(SQLiteDatabase sqLiteDatabase, String name, String description, //метод для заполнения базы данных
                             String url,
                             int colorId){
        ContentValues objectValues = new ContentValues();
        objectValues.put("NAME", name);
        objectValues.put("DESCRIPTION", description);
        objectValues.put("IMAGE_URL", url);
        objectValues.put("ID_COLOR", colorId);
        sqLiteDatabase.insert("DATA", null, objectValues);
    }
}
