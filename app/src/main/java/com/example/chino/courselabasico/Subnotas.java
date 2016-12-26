package com.example.chino.courselabasico;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import com.example.chino.courselabasico.models.Corte;
import com.example.chino.courselabasico.models.Materia;
import com.example.chino.courselabasico.models.Nota;

public class Subnotas extends AppCompatActivity implements View.OnClickListener {

    LinearLayout    subNotasGroup;

    EditText        etPorcentajeNotas;
    EditText        etPorcentajeParcial;
    EditText        etNotaParcial;
    Button          btnCalcular;
    Button          btnGuardar;
    Button          btnAgregarEditText;
    Button          btnEliminarEditText;
    TextView        tvResult;

    int         contador    =   0;
    long        corteId     =   0;
    EditText    addEditText;

    // Notes List
    ArrayList<EditText> subNotesEditText    = new ArrayList<EditText>();
    ArrayList<Nota>     notas               = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subnotas);

        //mostramos la toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //habilitamos la navegacion hacia atras en el tolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        this.corteId = i.getIntExtra( "corteId", 0 );

        this.init();

        if( this.corteId > 0 )
            initWithData();
    }

    private void init()
    {
        //seteando los respectivos controles
        //layout=(Layout)findViewById(R.id.layoutEtDinamicos);
        etPorcentajeNotas   =   (EditText)findViewById(R.id.etPorcentajeNotas);
        etPorcentajeNotas.setText( "50" );
        etPorcentajeParcial =   (EditText)findViewById(R.id.etPorcentajeParcial);
        etPorcentajeParcial.setText( "50" );
        etNotaParcial       =   (EditText)findViewById(R.id.etNotaParcial);

        // Nota final (resultado)
        tvResult            =   (TextView)findViewById(R.id.tvResul);

        // botones de crud
        btnCalcular         =   (Button) findViewById( R.id.btnCalcularSubNotas);
        btnCalcular.setOnClickListener( this );

        btnGuardar          =   (Button) findViewById(R.id.btnGuardarSubNotas);
        btnGuardar.setOnClickListener( this );

        //botones de agregado dinamico de edittext
        btnAgregarEditText  =   ( Button) findViewById(R.id.btnAgregarEditText);
        btnEliminarEditText =   ( Button) findViewById(R.id.btnEliminarEditText);

        // Seccion de subnotas
        subNotasGroup       =   (LinearLayout)findViewById(R.id.linearLayoutEtDinamicos);

        this.registerEditText();
    }

    public void initWithData()
    {
        Corte corte =  Materia.getCorte( Integer.valueOf( String.valueOf(this.corteId) ) );
        if( corte.getId() > 0 && corte.tieneDatos())
        {
            etPorcentajeNotas   .setText( String.valueOf( corte.getPorcentajeNota() ) );
            etPorcentajeParcial .setText( String.valueOf( corte.getPorcentajeParcial() ) );
            etNotaParcial       .setText( String.valueOf( corte.getNotaParcial() ) );
            getNotes(this, corte.getNotas());
        }
    }

    private void registerEditText()
    {
        int         count   = this.subNotasGroup.getChildCount();
        EditText    v       = null;

        for(int i = 0; i < count; i++)
        {
            v = (EditText) this.subNotasGroup.getChildAt(i);
            this.subNotesEditText.add( v );
        }
    }

    public  void agregarEdittext(View v)
    {
        int count           = this.subNotesEditText.size();
        EditText editText   = new EditText(v.getContext());
                 editText.setId( count + 1 );
                 editText.setSingleLine();
                 editText.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                 editText.setHint( "Nota " + String.valueOf( count + 1 ) );
        this.subNotesEditText.add( editText );

        this.subNotasGroup.addView( editText );
    }

    public  void eliminarEditText(View V){

        int count           = this.subNotesEditText.size();
        if(count > 1)
        {
            this.subNotasGroup.removeViewAt( count - 1 );
            this.subNotesEditText.remove( count - 1 );
        }

    }


    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnGuardarSubNotas:
                    if(this.saveHandler() > -1)
                        this.etPorcentajeNotas.setTextColor(Color.GREEN);
                break;

            case R.id.btnCalcularSubNotas:
                    // Checkeamos si los campos estan llenos
                    // Si estan llenos, los calculamos
                    if( this.allFilledFields() )
                    {
                        this.tvResult.setText( String.format("%.2f", this.calculateHandler() )  );
                        this.btnGuardar.setEnabled(true);
                    }
                break;
        }
    }

    private double calculateHandler()
    {
        // 1. Array de porcentages
        // ej. [ 20%, 30% ]
        double[] porcentages = new double[]{
                Double.valueOf( this.etPorcentajeParcial.getText().toString() ),
                Double.valueOf( this.etPorcentajeNotas.getText().toString() ),
        };
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // Nota definitiva, ej. 4.5
        double                          notaDefinitiva  = 0;
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Notas tanto subnotas como la nota del parcial, donde 0 es la nota del parcial y 1 la demas notas
        /*
            ej.
            [
                0 => [ 5.0 ],
                1 => [ 3.2, 5.0, 5 ]
            ]
         */
        HashMap<Integer, List<Double>>  notas           = getNotesHash();

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Calculo de las notas :v
        for(int i = 0; i < porcentages.length; i++)
        {
            double          notaPorcetual   = 0;
            List<Double>    notitas         = notas.get( i );

            for (double notita : notitas) {
                notaPorcetual += notita;
            }
            notaPorcetual   /= notitas.size();
            notaPorcetual   *= porcentages[ i ] / 100;
            notaDefinitiva  += notaPorcetual;

        }
        // retornamos la nota calculada
        return notaDefinitiva;
    }

    private int saveHandler() {
        //throw new UnsupportedOperationException("Aun no se puede guardar");  //llamando a el activity de detalles y se le mandan por parametros los textos


        Intent  intentito = new Intent( this, ActivityIngresarNotas.class );

        Corte   cortecito = new Corte(
                Double.valueOf( this.tvResult.getText().toString() ),
                Double.valueOf( this.etNotaParcial.getText().toString() )
        );

        cortecito.setContext(this);

        cortecito.tieneDetalle = true;
        cortecito.setId( this.corteId )
                .setPorcentajeParcial  ( Double.valueOf( this.etPorcentajeParcial.getText().toString() ) )
                .setPorcentajeNota     ( Double.valueOf( this.etPorcentajeNotas.getText().toString() ) );

        List<Double> notas = this.getNotes();

        intentito.putExtra("corteId", this.corteId);
        intentito.putExtra("corteDefinitiva",   cortecito.getNotaDefinitiva());
        intentito.putExtra("cortePorcentaje",   cortecito.getPorcentaje());
        intentito.putExtra("corteNotaParcial",  cortecito.getNotaParcial());


        int i = 0;
        for(double note : notas)
        {
            cortecito.addNota( note );
            intentito.putExtra("corteNota" + i, note);
            i++;
        }



        switch (String.valueOf(this.corteId))
        {
            case "1":
                    cortecito.setId( 1 );
                    Materia.corte1 = cortecito;
                break;

            case "2":
                    cortecito.setId( 2 );
                    Materia.corte2 = cortecito;
                break;

            case "3":
                    cortecito.setId( 3 );
                    Materia.corte3 = cortecito;
                break;
        }

        intentito.putExtra("countNotas",        cortecito.getNotas().size());

        //long id = cortecito.save();

        //intentito.putExtra(Corte.class.toString(), cortecito);
        startActivity( intentito );

        return 0;
    }

    private List<Double> getNotes() {

        List<Double>    notas = new ArrayList<>();
        int             count = this.subNotesEditText.size();

        for (int i = 0; i < count; i++)
        {
            notas.add(
                    Double.valueOf(
                            this.subNotesEditText.get( i )
                                            .getText()
                                                .toString()
                    )
            );
        }

        return notas;
    }

    private ArrayList<EditText> getNotes(Context context, List<Nota> notas) {

        int count   = this.notas.size();
        int countS  = 0;
        for (int i = 0; i < count; i++)
        {
            countS  = this.subNotesEditText.size();

            EditText editText   = new EditText(context);

            editText.setId( countS + 1 );
            editText.setSingleLine();
            editText.setText( String.valueOf( this.notas.get( i ).getValor() ) );

            editText.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

            editText.setHint( "Nota " + String.valueOf( countS + 1 ) );

            this.subNotesEditText.add( editText );

            this.subNotasGroup.addView( editText );
        }

        return this.subNotesEditText;
    }

    private HashMap<Integer, List<Double>> getNotesHash()
    {
        HashMap<Integer, List<Double>> notesHash = new HashMap<>();

        // 1. Nota del parcial
        notesHash.put(0, Arrays.asList( Double.valueOf( this.etNotaParcial.getText().toString() ) ) );

        // 2. Notas restantes
        notesHash.put(1, this.getNotes() );

        return notesHash;
    }


    // Validation functions
    // -------------------------------------------------
    private boolean allFilledFields()
    {
        return
                this.filled( this.etPorcentajeNotas, "Ingresa por favor el porcetaje de las notas" )
                &&
                this.filled( this.etPorcentajeParcial, "Ingresa por favor del parcial" )
                &&
                this.filled( this.etNotaParcial, "Ingresa por favor la nota del parcial" )
                &&
                this.filled( this.subNotesEditText, "Ingresa todas las notas por favor");

    }
    private boolean filled(EditText field, String Message)
    {
        boolean result = true;

        if(field.getText().toString().equals( "" ))
        {
            result = false;
            field.setError( Message );
        }

        return result;
    }

    private boolean filled(ArrayList<EditText> fields, String Message)
    {
        boolean result = true;

        for( EditText field : fields )
        {
            if(field.getText().toString().equals( "" ))
            {
                result = false;
                field.setError( Message );
            }
        }

        return  result;
    }
}
