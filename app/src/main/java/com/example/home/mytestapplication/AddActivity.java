package com.example.home.mytestapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {

    EditText nameObject, descriptionObject, urlImage;
    RadioGroup selectColor;
    RadioButton colorBlue, colorGreen, colorYellow, colorRed;
    int radioButtonId = 0;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        nameObject = findViewById(R.id.name_object);

        descriptionObject = findViewById(R.id.description_object);

        urlImage = findViewById(R.id.url_image);

        selectColor = findViewById(R.id.radio_group);

    }

    public void onClickAddDB (){
        String name = nameObject.getText().toString();// переобразовал EditText в String
        String description = descriptionObject.getText().toString();
        String url = urlImage.getText().toString();


        if (colorBlue.isChecked()){// определил цвет RadioButton
            radioButtonId = 0;
        }else if (colorGreen.isChecked()){
            radioButtonId = 1;
        }else if (colorYellow.isChecked()){
            radioButtonId = 2;
        }else if (colorRed.isChecked()){
            radioButtonId = 3;
        }
        DatabaseHelper.insertObject(databaseHelper.getWritableDatabase(), name, description, url, radioButtonId); //внес данные в таблицу (???)
        
    }

}
