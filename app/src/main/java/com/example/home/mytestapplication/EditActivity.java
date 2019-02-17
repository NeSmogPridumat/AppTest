package com.example.home.mytestapplication;

import android.content.Intent;
import android.database.Cursor;
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

public class EditActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT_EDIT = "objectId";


    EditText nameObject, descriptionObject, urlImage;
    RadioGroup selectColor;
    RadioButton colorBlue, colorGreen, colorYellow, colorRed;
    int radioButtonId = 0;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameObject = findViewById(R.id.name_object);

        descriptionObject = findViewById(R.id.description_object);

        urlImage = findViewById(R.id.url_image);

        selectColor = findViewById(R.id.radio_group);

        colorBlue = findViewById(R.id.editRadioBlue);
        colorGreen = findViewById(R.id.editRadioGreen);
        colorYellow = findViewById(R.id.editRadioYellow);
        colorRed = findViewById(R.id.editRadioRed);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("DATA", new String[]{"NAME", "DESCRIPTION", "IMAGE_URL",
                        "ID_COLOR"},
                "_id = ?",
                new String[]{Integer.toString((Integer) getIntent().getExtras().get(EXTRA_OBJECT_EDIT))},
                null, null, null);
        if (cursor.moveToFirst()) {
            String nameText = cursor.getString(0);
            String descriptionText = cursor.getString(1);
            String urlText = cursor.getString(2);
            int radioId = cursor.getInt(3);

            EditText name = findViewById(R.id.name_object);
            name.setText(nameText);

            EditText description = findViewById(R.id.description_object);
            description.setText(descriptionText);

            EditText url = findViewById(R.id.url_image);
            url.setText(urlText);
        }
    }

    public void onClickEdit(View view){
        if (nameObject.getText().toString().trim().equals("")) {
            nameObject.setError(getText(R.string.cannot_be_empty));
            nameObject.requestFocus();
            nameObject.setText("");
        } else {
            new EditTask().execute((Integer) getIntent().getExtras().get(EXTRA_OBJECT_EDIT));
            finish();
        }
    }

    public void onClickRemove (View view){

        new RemoveTask().execute((Integer) getIntent().getExtras().get(EXTRA_OBJECT_EDIT));
        finish();

    }

    private class EditTask extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            nameObject = findViewById(R.id.name_object);

            descriptionObject = findViewById(R.id.description_object);

            urlImage = findViewById(R.id.url_image);

            selectColor = findViewById(R.id.radio_group);

            colorBlue = findViewById(R.id.editRadioBlue);
            colorGreen = findViewById(R.id.editRadioGreen);
            colorYellow = findViewById(R.id.editRadioYellow);
            colorRed = findViewById(R.id.editRadioRed);

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
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(EditActivity.this);
            try {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                DatabaseHelper.updateObject(database, nameObject.getText().toString(),
                        descriptionObject.getText().toString(),
                        urlImage.getText().toString(),
                        radioButtonId,
                        (Integer) getIntent().getExtras().get(EXTRA_OBJECT_EDIT));
                database.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast toast = Toast.makeText(EditActivity.this, "Данные обновлены", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        }
    }

    private class RemoveTask extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... objects) {
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(EditActivity.this);
            try {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                DatabaseHelper.deleteObject(database, Integer.toString((Integer) getIntent().getExtras().get(EXTRA_OBJECT_EDIT)));
                database.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast toast = Toast.makeText(EditActivity.this, "Данные удалены", Toast.LENGTH_LONG);
                toast.show();
                }
        }
    }
}

