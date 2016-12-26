package com.example.chino.courselabasico;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.baseDatos.DataBaseManager;
import com.example.chino.courselabasico.models.Corte;
import com.example.chino.courselabasico.models.Materia;
import com.example.chino.courselabasico.models.Porcentaje;
import com.example.chino.courselabasico.models.ShareData;

import java.util.ArrayList;

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

    public long idMateria = 0;
    public long idCorte1 = 0;
    public long idCorte2 = 0;
    public long idCorte3 = 0;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Intent i = getIntent();
        setIntent(i);


        if(Materia.corte1 != null)
            etNumero1.setText(  String.valueOf( Materia.corte1.getNotaDefinitiva() ) );

        if(Materia.corte2 != null)
            etNumero2.setText(  String.valueOf( Materia.corte2.getNotaDefinitiva() ) );

        if(Materia.corte3 != null)
            etNumero3.setText(  String.valueOf( Materia.corte3.getNotaDefinitiva() ) );



        /*if( i != null && i.getExtras() != null)
        {
            //Corte cort = (Corte) i.getSerializableExtra(Corte.class.toString());
            //etNumero1.setText( String.valueOf( cort.getNotaDefinitiva() ) );
            if(i.getStringExtra( "corteId" ).equals( "1" ) )
            {
                etNumero1.setText(  String.valueOf( i.getExtras().getDouble( "corteDefinitiva" ) ) );
            }

            if(i.getStringExtra( "corteId" ).equals( "2" ) )
            {
                etNumero2.setText(  String.valueOf( i.getExtras().getDouble( "corteDefinitiva" ) ) );
            }

            if(i.getStringExtra( "corteId" ).equals( "3" ) )
            {
                etNumero3.setText(  String.valueOf( i.getExtras().getDouble( "corteDefinitiva" ) ) );
            }
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_notas);

        //mostramos la toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //habilitamos la navegacion hacia atras en el tolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        init();
    }

    private void init()
    {
        etNumero1.addTextChangedListener( new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                Corte corte = Materia.getCorte( 1 );

                if( !s.toString().equals( "" ) )
                {


                    double note = Double.valueOf( s.toString() );

                    if(!corte.tieneDetalle)
                    {
                        corte.setNotaDefinitiva( note )
                                .setNotaParcial( note * 0.5 )
                                .addNota( note * 0.5  );

                        btnSubNota1.setEnabled(corte.tieneDetalle);
                    }
                    else
                        btnSubNota2.setEnabled(true);
                }

                btnSubNota1.setEnabled(true);
            }
        } );

        etNumero2.addTextChangedListener( new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                Corte corte = Materia.getCorte( 2 );

                if( !s.toString().equals( "" ) )
                {


                    double note = Double.valueOf( s.toString() );

                    if(!corte.tieneDetalle)
                    {
                        corte.setNotaDefinitiva( note )
                                .setNotaParcial( note * 0.5 )
                                .addNota( note * 0.5  );

                        btnSubNota2.setEnabled(corte.tieneDetalle);
                    }
                    else
                        btnSubNota2.setEnabled(true);
                }
                else
                    btnSubNota2.setEnabled(true);
            }
        } );

        etNumero3.addTextChangedListener( new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                Corte corte = Materia.getCorte( 3 );

                if( !s.toString().equals( "" ) )
                {


                    double note = Double.valueOf( s.toString() );

                    if(!corte.tieneDetalle)
                    {
                        corte.setNotaDefinitiva( note )
                            .setNotaParcial( note * 0.5 )
                            .addNota( note * 0.5  );

                        btnSubNota3.setEnabled(corte.tieneDetalle);
                    }
                    else
                        btnSubNota3.setEnabled(true);
                }
                else
                    btnSubNota3.setEnabled(true);

            }
        } );
    }


    public  void llamarSubnotas1(View v)
    {
        //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,Subnotas.class);
        i.putExtra("corteId", 1);
        this.startActivity(i);
//        finish();
    }

    public  void llamarSubnotas2(View v)
    {
        //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,Subnotas.class);
        i.putExtra("corteId",2);
        this.startActivity(i);
    }

    public  void llamarSubnotas3(View v)
    {
        //  llama a la actividad en donde esta el recycler view

        Intent i = new Intent(this,Subnotas.class);
        i.putExtra("corteId",3);
        this.startActivity(i);
    }

    public  void caluloNotas()
    {
        String  nombre,numero1,numero2,numero3;
        numero1=etNumero1.getText().toString();
        numero2=etNumero2.getText().toString();
        numero3=etNumero3.getText().toString();

        BaseDatos db                        = new BaseDatos(this);
        ArrayList<Porcentaje> porcentajes   = db.getAllPorcentajes();

        ShareData.put( "porcentajes", porcentajes.get( 0 ) );


        nombre=tvNombreMateria.getText().toString();

        btnGuardar.setEnabled(true);

        //se valida de que nungun editText este vacio
        if(numero1.equals("")|| numero2.equals("")|| numero3.equals("")|| nombre.equals(""))
        {
            Toast.makeText(ActivityIngresarNotas.this, "Por favor Ingrese las todos los campos  ", Toast.LENGTH_SHORT).show();
        }

        else {

            Porcentaje porcentaje = porcentajes.get( 0 );

            resultadoFinal = (Double.parseDouble(numero1) * porcentaje.getcorte1() / 100 ) + (Double.parseDouble(numero2) * porcentaje.getCorte2() / 100) + (Double.parseDouble(numero3) * porcentaje.getCorte3() / 100);

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
        Intent i = new Intent(this,ListaMaterias.class);
        this.startActivity(i);
//        finish();
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
        contentValues.put(DataBaseManager.MATERIA_DEFINITIVA,Nota);
        contentValues.put(DataBaseManager.MATERIA_CORTE1,           Materia.corte1.getNotaDefinitiva());
        contentValues.put(DataBaseManager.MATERIA_CORTE1_PARCIAL,   Materia.corte1.getNotaParcial());
        contentValues.put(DataBaseManager.MATERIA_CORTE1_SUBNOTA,   Materia.corte1.getNotas().size() > 0 ? Materia.corte1.joinNotas() : ""  );
        contentValues.put(DataBaseManager.MATERIA_CORTE2,           Materia.corte2.getNotaDefinitiva());
        contentValues.put(DataBaseManager.MATERIA_CORTE2_PARCIAL,   Materia.corte2.getNotaParcial());
        contentValues.put(DataBaseManager.MATERIA_CORTE2_SUBNOTA,   Materia.corte2.getNotas().size() > 0 ? Materia.corte2.joinNotas() : ""  );
        contentValues.put(DataBaseManager.MATERIA_CORTE3,           Materia.corte3.getNotaDefinitiva());
        contentValues.put(DataBaseManager.MATERIA_CORTE3_PARCIAL,   Materia.corte3.getNotaParcial());
        contentValues.put(DataBaseManager.MATERIA_CORTE3_SUBNOTA,   Materia.corte3.getNotas().size() > 0 ? Materia.corte3.joinNotas() : ""  );
        contentValues.put(DataBaseManager.MATERIA_FOTO,CARITA_MOTICON);

        db.add(DataBaseManager.NOMBRE_TABLA_MATERIA,contentValues);
        Toast.makeText(this, "Se Guardo las notas de la Asignatura"+NombreMat, Toast.LENGTH_SHORT).show();
        //cierra la actividad en curso

        Materia.resetCortes();

        finish();
        //  llama a la actividad en donde esta el recycler view
        Intent i = new Intent(this,ListaMaterias.class);
        this.startActivity(i);

//        inicializarAdaptador();
//        inicializarListaContactos();
    }



}
