package com.example.home.mytestapplication;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            new AddTask().execute();//добавление объекта в фоновом потоке
        }
    }

    private class AddTask extends AsyncTask <Integer, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            nameObject = findViewById(R.id.name_object);

            descriptionObject = findViewById(R.id.description_object);

            urlImage = findViewById(R.id.url_image);

            selectColor = findViewById(R.id.radio_group);

            colorBlue = findViewById(R.id.radioBlue);
            colorGreen = findViewById(R.id.radioGreen);
            colorYellow = findViewById(R.id.radioYellow);
            colorRed = findViewById(R.id.radioRed);

            if (colorBlue.isChecked()) {// определил цвет RadioButton
                radioButtonId = 0;
            } else if (colorGreen.isChecked()) {
                radioButtonId = 1;
            } else if (colorYellow.isChecked()) {
                radioButtonId = 2;
            } else if (colorRed.isChecked()) {
                radioButtonId = 3;
            }
        }

        @Override
        protected Boolean doInBackground(Integer... objects) {
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(AddActivity.this);
            try {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                DatabaseHelper.insertObject(database, nameObject.getText().toString(),
                        descriptionObject.getText().toString(),
                        urlImage.getText().toString(), radioButtonId);
                database.close();
                return true;
            }catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success){
                Toast toast = Toast.makeText(AddActivity.this, "Данные добавлены", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
