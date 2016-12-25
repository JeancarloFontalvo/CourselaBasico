package com.example.chino.courselabasico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;

public class DetalleListaMateria extends AppCompatActivity {
    // se declaran de forma global las vistas correspondientes a xml
    TextView tvNombre;
    TextView tvCorte1;
    TextView tvCorte2;
    TextView tvCorte3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_materia);

        //mostramos la toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //habilitamos la navegacion hacia atras en el tolbar
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // recibe los paraetros
        Bundle parametros = getIntent().getExtras();

        // guarda en una variable el parametro mandad cuando esta actividad es creada
        // los nombres de  parametros vienen de el archivo string.xml
        String nombre = parametros.getString("param_Nombre");
        // en este caso el id del bundle viene por un nombre
        String corte1 = parametros.getString("param_corte1");
        String corte2 = parametros.getString("param_corte2");
        String corte3 = parametros.getString("param_corte3");


        //obteniendo los id de las vistas e ingresandoles los parametros que vienen de la actividad ListaMaterias
        tvNombre = (TextView) findViewById(R.id.tvNombreContacto);
        tvCorte1 = (TextView) findViewById(R.id.tvCorte1);
        tvCorte2 = (TextView) findViewById(R.id.tvCorte2);
        tvCorte3 = (TextView) findViewById(R.id.tvCorte3);


        // le agrega el nombre de los parametros recibidos  a las vistas para colocarle los diferentes nombres
        tvNombre.setText(nombre);
        tvCorte1.setText(corte1);
        tvCorte2.setText(corte2);
        tvCorte3.setText(corte3);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //1. pregunta si se pulso el boton de back
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //levanta un nuevo intent que llama a la actividad ListaContacto
            //startActivity( new Intent(DetalleListaMateria.this,ListaMaterias.class))
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
