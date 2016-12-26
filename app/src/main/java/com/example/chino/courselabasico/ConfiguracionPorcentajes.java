package com.example.chino.courselabasico;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.baseDatos.DataBaseManager;
import com.example.chino.courselabasico.models.Porcentaje;
import com.example.chino.courselabasico.models.ShareData;

import java.util.ArrayList;

public class ConfiguracionPorcentajes extends AppCompatActivity {

    TextView tvCorte1;
    TextView tvCorte2;
    TextView tvCorte3;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        //mostramos la toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //habilitamos la navegacion hacia atras en el tolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //comunicandose con  los elementso del archivo xml
        tvCorte1 = (TextView) findViewById(R.id.tvPorcentajeCorte1);
        tvCorte2 = (TextView) findViewById(R.id.tvPorcentajeCorte2);
        tvCorte3 = (TextView) findViewById(R.id.tvPorcentajeCorte3);
        btnGuardar= (Button) findViewById(R.id.btnGuardarCortes);

        init();
    }

    private void init()
    {
        BaseDatos db = new BaseDatos(this);
        ArrayList<Porcentaje> porcentajes = db.getAllPorcentajes();

        if( porcentajes.size() > 0 )
        {
            Porcentaje littlePorcentaje = porcentajes.get( 0 );
            tvCorte1.setText( String.valueOf( littlePorcentaje.getcorte1() ) );
            tvCorte2.setText( String.valueOf( littlePorcentaje.getCorte2() ) );
            tvCorte3.setText( String.valueOf( littlePorcentaje.getCorte3() ) );

            ShareData.put( "porcentajes", littlePorcentaje );
        }
    }

    public  void  insertarCortes(View v ){
        BaseDatos db = new BaseDatos(this);
        insertarCortes(db);
        //cierra la actividad en curso
        finish();

    }
//    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public  void insertarCortes(BaseDatos db)
    {
        Double Corte1 = Double.parseDouble(tvCorte1.getText().toString());
        Double Corte2 = Double.parseDouble(tvCorte2.getText().toString());
        Double Corte3 = Double.parseDouble(tvCorte3.getText().toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseManager.PORCENTAJECORTES_CORTE1,Corte1);
        contentValues.put(DataBaseManager.PORCENTAJECORTES_CORTE2,Corte2);
        contentValues.put(DataBaseManager.PORCENTAJECORTES_CORTE3,Corte3);


        if( db.update( DataBaseManager.NOMBRE_TABLA_PORCENTAJECORTES, DataBaseManager.PORCENTAJECORTES_ID, 1 , contentValues) > 0 );
        {
            Toast.makeText(this, "Se Guardo con exito ", Toast.LENGTH_SHORT).show();
            //cierra la actividad en curso
            finish();
            //llama a la actividad en donde esta el recycler view
            Intent i = new Intent(this,ListaMaterias.class);
            this.startActivity(i);
        }
    }
}
