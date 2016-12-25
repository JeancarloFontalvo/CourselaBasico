package com.example.chino.courselabasico.baseDatos;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chino.courselabasico.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by CHINO on 22/12/2016.
 */

public class BaseDatos extends SQLiteOpenHelper
{
    private static final String BD_NOMBRE="bd_notas.sqlite";
    private static final int  BD_VERSION=1;

    public BaseDatos(Context context){
        super(context, BD_NOMBRE, null, BD_VERSION);
    }


    //ESTE ES EL METODO QUE PERMITE CREAR LAS TABLAS DE LA BASE DE DATOS EN SQLITE
    @Override
    public void onCreate(SQLiteDatabase db) {
        //se crea la tabla estudiante
        db.execSQL(DataBaseManager.CREAR_TABLA_ESTUDIANTE);
        db.execSQL(DataBaseManager.CREAR_TABLA_CORTES);

    }

    //PARA QUE ACTUALIZE LA BD EN CASO DE TRABAJAR CON OTRA VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //SI LA TABLA YA ESTA QUE LA BORRE
        db.execSQL("DROP TABLE IF EXIST "+DataBaseManager.CREAR_TABLA_ESTUDIANTE);
        db.execSQL("DROP TABLE IF EXIST "+DataBaseManager.CREAR_TABLA_CORTES);
        //CREA LA BD
        onCreate(db);
    }

//HACE UNA CONSULTA A LA BASE DE DATOS Y GUARDA LOS DATOS EN UN ARRAY
    public ArrayList<Contacto>obtenerTodosContactos(){
        ArrayList<Contacto>contactos = new ArrayList<>();
        String query ="SELECT * FROM "+DataBaseManager.NOMBRE_TABLA_ESTUDIANTE;
        //abriendo la base de datos en forma de lectira
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        //recorriendo los registros
        while (registros.moveToNext())
        {
            //objeto de la clase contacto
            Contacto contactoActual = new Contacto();
            //setea en e campo id
            contactoActual.setId_contacto(registros .getInt  (0));
            contactoActual.setNombre(registros     .getString(1));
            contactoActual.setNotaCorte1(registros .getString(2));
            contactoActual.setNotaCorte2(registros .getString(3));
            contactoActual.setNotaCorte3(registros .getString(4));
            contactoActual.setFoto(registros       .getInt   (5));
            contactos.add(contactoActual);

        }
        db.close();



        return contactos;
    }
//metodo que inserta UN ESTUDIANTE registros de la bd
    public  void  insertarContacto(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseManager.NOMBRE_TABLA_ESTUDIANTE,null,contentValues);
        db.close();
    }

    // ESTE METODO DE ESTA CLASE PERMITE GUARDAR DATOS EN LA TABLACORTES
    public  void  insertarcortes(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseManager.NOMBRE_TABLA_CORTES,null,contentValues);
        db.close();
    }


}
