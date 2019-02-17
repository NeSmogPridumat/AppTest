package com.example.home.mytestapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list = findViewById(R.id.list);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        // try ???
        db = databaseHelper.getReadableDatabase();
        cursor = db.query("DATA", new String[]{"_id", "NAME"}, null,
                null, null, null, null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                new int[]{android.R.id.text1}, 0);
        list.setAdapter(listAdapter);
        //catch???

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> list, View itemView,
                                            int position, long id) {
                        Intent intent = new Intent(ListActivity.this,
                                DescriptionActivity.class);
                        intent.putExtra(DescriptionActivity.EXTRA_OBJECT, (int) id);
                        startActivity(intent);
                    }
                };
        list.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
