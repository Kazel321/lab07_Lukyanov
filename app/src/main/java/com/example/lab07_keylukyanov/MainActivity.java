package com.example.lab07_keylukyanov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txt_key, txt_value;
    Database mydb;
    String key, value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_key = findViewById((R.id.txt_key));
        txt_value = findViewById((R.id.txt_value));

        mydb = new Database(this, "mybase.db", null,1);
    }

    public void on_insert_click(View v)
    {
        key = txt_key.getText().toString();
        value = txt_value.getText().toString();

        mydb.do_insert(key, value);
    }

    public void on_replace_click(View v)
    {

    }

    public void on_select_click(View v)
    {
        key = txt_key.getText().toString();
        value = txt_value.getText().toString();

        txt_value.setText(value);
    }

    public void on_delete_click(View v)
    {

    }
}