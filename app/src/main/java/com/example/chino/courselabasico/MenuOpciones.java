package com.example.chino.courselabasico;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MenuOpciones extends AppCompatActivity {
   private Spinner spMenuOpciones;
    TextView tvMenuContextual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        // agregando soporte con java tambien para versiones anteriores
        Toolbar toolbar =(Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        //habilitamos la navegacion hacia atras en el tolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tvmenuContextual en orbita con el archivo java
        tvMenuContextual=(TextView)findViewById (R.id.tvMenuContexto);
        registerForContextMenu(tvMenuContextual);

    }

    // este metodo toma el recurso xml menu y lo infla(muestra) en la vista
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    // este metodo le da funcionalidad al  hacer click a algunos de los elementos
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mAbuot:
                Toast.makeText(this, " has presionado el boton about", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.mConfiguracion:
//                Toast.makeText(this, " has presionado el boton configuracion ", Toast.LENGTH_SHORT).show();
//                break;
//            case  R.id.mBusqueda:
//                Toast.makeText(this, "Boton refresh precionado ", Toast.LENGTH_SHORT).show();
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------------------------------------------********************************************************************
    // ESTE METODO SIRVE PARA CREAR EL MENU CONTEXTUAL EN  LA VISTA ESTABLECIDA
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual,menu);

    }
    //dando click a las diferentes opciones  que muestra el menu

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuOpcionBorrar:
                tvMenuContextual.setVisibility(View.GONE);
                Toast.makeText(this, "El texto a sido eliminado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuOpcionEditar:
                Toast.makeText(this, "presionado la opcion Editar ", Toast.LENGTH_SHORT).show();

                break;
        }

        return super.onContextItemSelected(item);
    }

    //--------------------------------------------------------------------------------

    //CREANDO UN MENU POPUP
    public  void menuPopup(View v)
    {
        PopupMenu popopMenu = new PopupMenu(this,v);
        popopMenu.getMenuInflater().inflate(R.menu.menu_popup,popopMenu.getMenu());

        //para que los elementos del menu popup sean cliqueables
        popopMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menuPopup:

                        Toast.makeText(getBaseContext(), " Se escogio :Opcion de menu popup", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });
    //muestra el menu popup
        popopMenu.show();
    }

}
