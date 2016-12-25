package com.example.chino.courselabasico;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chino.courselabasico.adaptador.ContactosAdaptador;
import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.configuracion.Configuracion;
import com.example.chino.courselabasico.pojo.Contacto;

import java.util.ArrayList;

public class ListaContactos extends AppCompatActivity {
//coleccion de contactos ;
    ArrayList <Contacto>contactos;
    private RecyclerView listaContactosCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);

        // agregando soporte con java tambien para versiones anteriores
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

//        //habilitamos la navegacion hacia atras en el tolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaContactosCV=(RecyclerView)findViewById(R.id.rvListaContactos);
        // se define la forma en que se muestra el recycer view

        //***************************************** EN FORMA DE LISTA VERTICAL
        LinearLayoutManager llm= new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);  //definiendo la orientacion del  la vista
         listaContactosCV.setLayoutManager(llm);  // este objeto se comporte como un linear layout
        //****************************************

        //************************************* MUESTRA LOS ELEMENTOS EN FORMA DE GRID
//        GridLayoutManager glm = new GridLayoutManager(this,2);
//        listaContactosCV.setLayoutManager(glm);  // este objeto se comporte como un linear layout

        inicializarListaContactos();
        inicializarAdaptador();
        // crea la base de daos con la tabla estudiantes

       // BaseDatos crearbd = new BaseDatos(this);
        //SQLiteDatabase d =crearbd.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mAbuot:

                Intent i = new Intent(this,About.class);
                this.startActivity(i);

                break;
            case R.id.mConfiguracion:

                Intent j = new Intent(this,Configuracion.class);
                this.startActivity(j);

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void nuevocontacto (View v)
    {

        Intent i = new Intent(this,ActivityIngresarNotas.class);
        this.startActivity(i);
        finish();

    }

    //instancia de contacto adaptador
    public  void inicializarAdaptador()
    {
        ContactosAdaptador adaptador = new ContactosAdaptador(contactos,this);
        listaContactosCV.setAdapter(adaptador);
    }

    public  void inicializarListaContactos()
    {
        BaseDatos db = new BaseDatos(this);
        contactos = new ArrayList<Contacto>();

        // cuando carga el elemento se carga el array list
        contactos=db.obtenerTodosContactos();

    }



}
