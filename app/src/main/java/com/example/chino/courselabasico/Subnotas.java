package com.example.chino.courselabasico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @name    SubNotasActivity
 * @author  Jeancarlo Fontalvo
 * @since   1.0
 * @description La actividad para las subnotas
 */
public class Subnotas extends AppCompatActivity
{
    //Array Notes
    LinearLayout    linear;
    EditText        etPorcentajeNotas;
    EditText        etPorcentajeParcial;
    EditText        etNotaParcial;
    Button          btnCalcular;
    Button          btnGuardar;
    Button          btnAgregarEditText;
    Button          btnEliminarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subnotas);

        //seteando los respectivos controles
//        layout=(Layout)findViewById(R.id.layoutEtDinamicos);
        etPorcentajeNotas = (EditText)findViewById(R.id.etPorcentajeNotas);
        etPorcentajeParcial = (EditText)findViewById(R.id.etPorcentajeParcial);
        etNotaParcial = (EditText)findViewById(R.id.etPorcentajeParcial);

        btnCalcular =(Button) findViewById( R.id.btnCalcularSubNotas);
        btnGuardar  =(Button) findViewById(R.id.btnGuardarSubNotas);

        //botones de agregado dinamico de edittext
        btnAgregarEditText =( Button) findViewById(R.id.btnAgregarEditText);
        btnEliminarEditText =( Button) findViewById(R.id.btnEliminarEditText);

        linear=(LinearLayout)findViewById(R.id.linearLayoutEtDinamicos);


    }
    int contador=0;
    EditText addEditText;
    public  void agregarEdittext(View v)
    {
        /*
        EditText note = new EditText(v);


        this.linear.addView(  );
        */
    }
    public  void eliminarEditText(View V){


}

}
