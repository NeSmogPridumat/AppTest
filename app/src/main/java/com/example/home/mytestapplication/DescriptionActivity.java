package com.example.home.mytestapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "objectId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        int objectId = (Integer)getIntent().getExtras().get(EXTRA_OBJECT);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        //try???
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("DATA", new String[]{"NAME", "DESCRIPTION", "IMAGE_URL",
                "ID_COLOR"},
                "_id = ?",
                new String[]{Integer.toString(objectId)},
                null, null, null);
        if (cursor.moveToFirst()) {
            String nameText = cursor.getString(0);
            String descriptionText = cursor.getString(1);
            String urlText = cursor.getString(2);
            int radioId = cursor.getInt(3);


            TextView name = findViewById(R.id.textName);
            name.setText(getText(R.string.name) + nameText);

            TextView description = findViewById(R.id.textDescription);
            description.setText(getText(R.string.description) + descriptionText);

            RadioButton radioButtonBlue = findViewById(R.id.radioDescriptionBlue);
            RadioButton radioButtonGreen = findViewById(R.id.radioDescriptionGreen);
            RadioButton radioButtonYellow = findViewById(R.id.radioDescriptionYellow);
            RadioButton radioButtonRed = findViewById(R.id.radioDescriptionRed);

            switch (radioId){
                case 0:
                   radioButtonBlue.setVisibility(View.VISIBLE);
                case  1:
                    radioButtonGreen.setVisibility(View.VISIBLE);
                case 2:
                    radioButtonYellow.setVisibility(View.VISIBLE);
                case 3:
                    radioButtonRed.setVisibility(View.VISIBLE);
            }
        }
        cursor.close();
        db.close();
    }
}
