package com.example.home.mytestapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText nameObject, descriptionObject, urlImage;
    RadioGroup selectColor;
    RadioButton colorBlue, colorGreen, colorYellow, colorRed;
    int radioButtonId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameObject = findViewById(R.id.name_object);

        descriptionObject = findViewById(R.id.description_object);

        urlImage = findViewById(R.id.url_image);

        selectColor = findViewById(R.id.radio_group);

        colorBlue = findViewById(R.id.radioBlue);
        colorGreen = findViewById(R.id.radioGreen);
        colorYellow = findViewById(R.id.radioYellow);
        colorRed = findViewById(R.id.radioRed);
    }

    public void onClickAdd (View view) {
        if (nameObject.getText().toString().trim().equals("")) {
            nameObject.setError(getText(R.string.cannot_be_empty));
            nameObject.requestFocus();
            nameObject.setText("");
        } else {
            String name = nameObject.getText().toString();// переобразовал EditText в String
            String description = descriptionObject.getText().toString();
            String url = urlImage.getText().toString();

            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase database = databaseHelper.getWritableDatabase();


            if (colorBlue.isChecked()) {// определил цвет RadioButton
                radioButtonId = 0;
            } else if (colorGreen.isChecked()) {
                radioButtonId = 1;
            } else if (colorYellow.isChecked()) {
                radioButtonId = 2;
            } else if (colorRed.isChecked()) {
                radioButtonId = 3;
            }

            DatabaseHelper.insertObject(database, name, description, url, radioButtonId); //внес данные в таблицу (???)

            Toast toast = Toast.makeText(this, "Данные добавлены", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
