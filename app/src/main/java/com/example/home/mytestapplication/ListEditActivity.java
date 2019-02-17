package com.example.home.mytestapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListEditActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        ListView list = findViewById(R.id.edit_list);
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
                        Intent intent = new Intent(ListEditActivity.this,
                                EditActivity.class);
                        intent.putExtra(EditActivity.EXTRA_OBJECT_EDIT, (int) id);
                        startActivity(intent);
                    }
                };
        list.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
