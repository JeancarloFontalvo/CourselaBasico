package com.example.chino.courselabasico;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.baseDatos.DataBaseManager;

public class ActivityIngresarNotas extends AppCompatActivity  {

    // declarando las variables gobales
    EditText etNumero1;
    EditText etNumero2;
    EditText etNumero3;
    TextView tvResultado;
    EditText tvNombreMateria;

    Button btnCalcular;
    Button btnGuardar;

    Button btnSubNota1;
    Button btnSubNota2;
    Button btnSubNota3;


    // se calcula la nota final
    Double resultadoFinal;
    String resultadoParaGuardar;
    Integer CARITA_MOTICON;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_notas);


//estableciendo cada variable java a una respectiva vista del layout
        etNumero1 = (EditText) findViewById(R.id.etNumero1);
        etNumero2 = (EditText) findViewById(R.id.etNumero2);
        etNumero3 = (EditText) findViewById(R.id.etNum3);

        tvResultado = (TextView) findViewById(R.id.tvResul);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnSubNota1 = (Button) findViewById(R.id.btnSubnotas1);
        btnSubNota2 = (Button) findViewById(R.id.btnSubnotas2);
        btnSubNota3 = (Button) findViewById(R.id.btnSubnotas3);

        tvNombreMateria = (EditText) findViewById(R.id.etNombreMateria);


//boton a la esucha de un click
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se optienen los valores de cada editText y se guardan en las respectivas variables
                caluloNotas();
            }
        });

    }

    public  void llamarSubnotas1(View v)
    {
        //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,Subnotas.class);
//        i.putExtra("param_subnotasCorte1","corte1");
        this.startActivity(i);
    }

    public  void llamarSubnotas2(View v)
    {
        //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,Subnotas.class);
//        i.putExtra("param_subnotasCorte1","corte1");
        this.startActivity(i);
    }

    public  void llamarSubnotas3(View v)
    {
        //  llama a la actividad en donde esta el recycler view

        Intent i = new Intent(this,Subnotas.class);
//        i.putExtra("param_subnotasCorte1","corte1");
        this.startActivity(i);
    }




    public  void caluloNotas()
    {
        String  nombre,numero1,numero2,numero3;
        numero1=etNumero1.getText().toString();
        numero2=etNumero2.getText().toString();
        numero3=etNumero3.getText().toString();

        nombre=tvNombreMateria.getText().toString();

        btnGuardar.setEnabled(true);

        //se valida de que nungun editText este vacio
        if(numero1.equals("")|| numero2.equals("")|| numero3.equals("")|| nombre.equals(""))
        {

            Toast.makeText(ActivityIngresarNotas.this, "Por favor Ingrese las todos los campos  ", Toast.LENGTH_SHORT).show();
        }

        else {
            resultadoFinal = (Double.parseDouble(numero1) * 0.2) + (Double.parseDouble(numero2) * 0.3) + (Double.parseDouble(numero3) * 0.5);

            if (String.valueOf(resultadoFinal).length() > 3) {
                resultadoParaGuardar = (String.valueOf(resultadoFinal).substring(0, 3));
                tvResultado.setText(String.valueOf(resultadoFinal).substring(0, 3));
            } else {
                resultadoParaGuardar = (String.valueOf(resultadoFinal));
                tvResultado.setText(resultadoParaGuardar);
            }

            // cambia de color dependiendo de la nota sacada rojo si pierde verde si gana
            if (resultadoFinal < 3)
            {
                tvResultado.setTextColor(getResources().getColor(R.color.colorError));
                CARITA_MOTICON = R.drawable.carita_triste2jpg;
            }

            else {
                tvResultado.setTextColor(getResources().getColor(R.color.colorsuccess));
                CARITA_MOTICON = R.drawable.carita_alegre;
            }
        }
    }
    //metodos de manejan el espiner


    ////////////////////////////////////////////////////////////////////////////////////////////////

    //en caso de pulsar l boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

    //1. pregunta si se pulso el boton de back
    if(keyCode==KeyEvent.KEYCODE_BACK)
    {
        //levanta un nuevo intent que llama a la actividad ListaContacto
        //startActivity( new Intent(DetalleListaMateria.this,ListaMaterias.class))

        finish();
        Intent i = new Intent(this,ListaMaterias.class);
        this.startActivity(i);
    }
    return super.onKeyDown(keyCode, event);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    //se ejecuta esta funcion cuando el usuario pulsa el boton guardar
    public  void  insertarNotas(View v ){
        BaseDatos db = new BaseDatos(this);
        insertarMaterias(db);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public  void insertarMaterias(BaseDatos db)
    {
        String NombreMat=tvNombreMateria.getText().toString();
        String corte1=etNumero1.getText().toString();

        String Nota=resultadoParaGuardar;

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseManager.MATERIA_NOMBRE_MATERIA,NombreMat);
        contentValues.put(DataBaseManager.MATERIA_DEFINITIVA,corte1);
        contentValues.put(DataBaseManager.MATERIA_FOTO,CARITA_MOTICON);

        db.addMateria(DataBaseManager.NOMBRE_TABLA_MATERIA,contentValues);
        Toast.makeText(this, "Se Guardo las notas de la Asignatura"+NombreMat, Toast.LENGTH_SHORT).show();
       //cierra la actividad en curso
        finish();
      //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,ListaMaterias.class);
        this.startActivity(i);

//        inicializarAdaptador();
//        inicializarListaContactos();
    }
}
