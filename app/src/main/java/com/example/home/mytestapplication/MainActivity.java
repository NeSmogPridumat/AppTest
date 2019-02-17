package com.example.home.mytestapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnAdd, btnEdit, btnGroup;

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.list);

    }

    public void onClickList (View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void onClickEdit (View view) {
        Intent intent = new Intent(this, ListEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
