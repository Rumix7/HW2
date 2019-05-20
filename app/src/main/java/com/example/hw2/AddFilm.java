package com.example.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hw2.tasks.TaskListContent;

import java.util.Random;

public class AddFilm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_film);
    }

    public void Zapisz(View view) {
        EditText nazwa = findViewById ( R.id.editText );
        EditText imie_nazwisko = findViewById ( R.id.editText2 );
        EditText data_prem = findViewById ( R.id.editText3 );
        if(!nazwa.getText ().toString ().equals ( "" ) && !data_prem.getText ().toString ().equals ( "" ) && !imie_nazwisko.getText ().toString ().equals ( "" ))
        {
            Random random = new Random ( );
            String selectedImage;
            Intent intent = getIntent ();
            selectedImage = intent.getStringExtra ( "photo");
            if (intent.getStringExtra ( "photo" )==null) {
                selectedImage="drawable "+(random.nextInt (3)+1);
            }
            TaskListContent.addItem ( new TaskListContent.Task ( "Task." +TaskListContent.ITEMS.size ()+1, nazwa.getText ().toString (), imie_nazwisko.getText ().toString (),data_prem.getText ().toString (), selectedImage ) );
            Intent main_intent=new Intent ( );
            setResult ( RESULT_OK, main_intent );
            finish ();
        }
    }
}