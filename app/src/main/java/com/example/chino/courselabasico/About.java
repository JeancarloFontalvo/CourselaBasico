package com.example.chino.courselabasico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        // agregando soporte con java tambien para versiones anteriores
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

//        //habilitamos la navegacion hacia atras en el tolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
