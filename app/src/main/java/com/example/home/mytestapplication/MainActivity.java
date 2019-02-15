package com.example.home.mytestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnAdd, btnEdit, btnGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.list);

    }

    public void onClickAdd(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
