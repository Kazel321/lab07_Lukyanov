package com.example.lab07_keylukyanov;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    EditText txt_key, txt_value;
    Database mydb;
    String key, value;
    Button btn_Yes, btn_No;
    AlertDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_key = findViewById((R.id.txt_key));
        txt_value = findViewById((R.id.txt_value));

        mydb = new Database(this, "mybase.db", null,1);

        LayoutInflater dialogLayout = LayoutInflater.from(this);
        View dialogView = dialogLayout.inflate(R.layout.delete_dialog, null);
        deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(dialogView);

        btn_Yes = dialogView.findViewById(R.id.btn_yes);
        btn_No = dialogView.findViewById(R.id.btn_no);

    }

    public void on_insert_click(View v)
    {
        key = txt_key.getText().toString();
        if (mydb.do_find(key))
            Toast.makeText(this, "This key is not empty, use UPDATE", Toast.LENGTH_SHORT).show();
        else {
            value = txt_value.getText().toString();
            mydb.do_insert(key, value);
        }
    }

    public void on_replace_click(View v)
    {
        key = txt_key.getText().toString();
        value = txt_value.getText().toString();
        mydb.do_update(key, value);
    }

    public void on_select_click(View v)
    {
        key = txt_key.getText().toString();
        value = mydb.do_select(key);

        txt_value.setText(value);
    }

    public void on_delete_click(View v)
    {
        key = txt_key.getText().toString();
        if (mydb.do_find(key))
        {
            deleteDialog.show();
            btn_Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mydb.do_delete(key);
                    deleteDialog.cancel();
                }
            });
            btn_No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteDialog.cancel();
                }
            });
        }
        else Toast.makeText(this, "Key does not exist, use INSERT to create", Toast.LENGTH_SHORT).show();
    }
}