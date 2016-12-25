package com.example.chino.courselabasico.adaptador;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chino.courselabasico.DetalleListaMateria;
import com.example.chino.courselabasico.R;
import com.example.chino.courselabasico.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by CHINO on 02/10/2016.
 * Se puede decir, que los adaptadores son colecciones de datos, que asignamos a una vista para que ésta los muestre, por ejemplo, podemos crear un ArrayAdapter a partir de un array de string ya creado y con datos, y asignar este adaptador a un ListView, así, el ListView mostrará los datos del array.
 *
 */

public class ContactosAdaptador extends RecyclerView.Adapter<ContactosAdaptador.ConntactoViewHolder>{
    ArrayList <Contacto> contactos;

    Activity activity;

    //creando un costructor de la claseContactoAdaptadror

    public ContactosAdaptador (ArrayList<Contacto> contactos ,Activity activity){
        this.contactos=contactos;
        this.activity= activity;
    }


//Infla el layout card_videcontacto y lo pasara al view holder para que obtenga los view
    //asocisa cada elemento de la lista con cada view
    @Override
    public ConntactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //le da vida al layout, ES DE CIR INFlar en layout para que se vea como una vista, la idea es asodical cardViewContacto al recycler view

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_materias,parent,false);

        return  new ConntactoViewHolder(v);
    }

    //le manda la lista de contactos a cada elementoConntactoViewHolder

    @Override
    public void onBindViewHolder(ConntactoViewHolder contacto_ViewHolder, int position) {
    // a traves del objeto contacto accede a los elemntos del array Contacto
        final Contacto contacto= contactos.get(position);
        contacto_ViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contacto_ViewHolder.tvNombreCv.setText(contacto.getNombre());

        contacto_ViewHolder.tvCorte1.setText(contacto.getNotaCorte1());
        contacto_ViewHolder.tvCorte2.setText(contacto.getNotaCorte2());
        contacto_ViewHolder.tvCorte3.setText(contacto.getNotaCorte3());



        //HACIENDO QUE LA IMAGEN SEA CLIKEABLE

        contacto_ViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, " HOLA : "+contacto.getNombre(), Toast.LENGTH_SHORT).show();

                //llamando a el activity de detalles y se le mandan por parametros los textos
                Intent i = new Intent(activity,DetalleListaMateria.class);

                i.putExtra("param_Nombre",contacto.getNombre());
                i.putExtra("param_corte1",contacto.getNotaCorte1());
                i.putExtra("param_corte2",contacto.getNotaCorte2());
                i.putExtra("param_corte3",contacto.getNotaCorte3());

                activity.startActivity(i);
            }
        });

            //haciendo que el boton like sea clicleable
//        contacto_ViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(activity, "like a "+contacto.getNombre(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    //este metodo obtiene la cantidad de item de la lista recicler view
    @Override
    public int getItemCount() {
        return contactos.size();
    }
//  en esta clase se da la logica de darle vida a los views para que se muestren en una pantalla activa

    public static class ConntactoViewHolder extends RecyclerView.ViewHolder
    {

        // se definen todos los views que estan en el archivo cardViewContacto.xml estas variables deben de concordar con las vistas respectivas

        private ImageView imgFoto;
        private TextView tvNombreCv,tvCorte1,tvCorte2,tvCorte3;


        public ConntactoViewHolder(View itemView) {
            super(itemView);
            //asociando los objetos con el respectivi vew
            imgFoto=(ImageView)itemView.findViewById(R.id.imgFoto);
            tvNombreCv=(TextView)itemView.findViewById(R.id.tvNombreCv);

            tvCorte1=(TextView)itemView.findViewById(R.id.tvNotaCorte1);

        }
    }

}
