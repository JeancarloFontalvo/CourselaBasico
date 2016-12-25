package com.example.chino.courselabasico.baseDatos;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chino.courselabasico.pojo.Contacto;
import com.example.chino.courselabasico.models.*;
import java.util.ArrayList;

/**
 * Created by CHINO on 22/12/2016.
 */

public class BaseDatos extends SQLiteOpenHelper
{
    private static final String BD_NOMBRE       =   "bd_notas.sqlite";
    private static final int    BD_VERSION      =   1;

    public BaseDatos(Context context){
        super(context, BD_NOMBRE, null, BD_VERSION);
    }


    //ESTE ES EL METODO QUE PERMITE CREAR LAS TABLAS DE LA BASE DE DATOS EN SQLITE
    @Override
    public void onCreate(SQLiteDatabase db) {
        //se crea la tabla estudiante
        db.execSQL(DataBaseManager.CREAR_TABLA_MATERIA);
        db.execSQL(DataBaseManager.NOMBRE_TABLA_PORCENTAJECORTES);
        db.execSQL(DataBaseManager.CREAR_TABLA_CORTEMATERIA);
        db.execSQL(DataBaseManager.CREAR_TABLA_NOTAS);

    }

    //PARA QUE ACTUALIZE LA BD EN CASO DE TRABAJAR CON OTRA VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //SI LA TABLA YA ESTA QUE LA BORRE
        db.execSQL("DROP TABLE IF EXIST " + DataBaseManager.CREAR_TABLA_NOTAS);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseManager.CREAR_TABLA_CORTEMATERIA);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseManager.CREAR_TABLA_MATERIA);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseManager.NOMBRE_TABLA_PORCENTAJECORTES);

        //CREA LA BD
        onCreate(db);
    }

    //HACE UNA CONSULTA A LA BASE DE DATOS Y GUARDA LOS DATOS EN UN ARRAY
    public ArrayList<Materia> getAllMaterias()
    {
        // Arreglo de materias
        ArrayList<Materia>  materias    = new ArrayList<>();
        String query                    = "SELECT * FROM " + DataBaseManager.NOMBRE_TABLA_MATERIA;

        // Abriendo la base de datos en forma de lectira
        SQLiteDatabase db   = this.getWritableDatabase();
        Cursor registros    = db.rawQuery(query,null);

        // Recorriendo los registros
        while (registros.moveToNext())
        {
            //setea en e campo id
            materias.add( new Materia(
                    // Obtengo el id
                    registros.getInt(
                            registros.getColumnIndex(DataBaseManager.MATERIA_ID)
                    ),
                    // Obtengo el nombre
                    registros.getString(
                            registros.getColumnIndex(DataBaseManager.MATERIA_NOMBRE_MATERIA)
                    ),
                    // obtengo el id de la foto
                    registros.getInt(
                            registros.getColumnIndex(DataBaseManager.MATERIA_FOTO)
                    )
            ) );

        }
        db.close();

        return materias;
    }


    //HACE UNA CONSULTA A LA BASE DE DATOS Y GUARDA LOS DATOS EN UN ARRAY
    public ArrayList<Porcentaje> getAllPorcentajes()
    {
        // Arreglo de materias
        ArrayList<Porcentaje>  porcentajes  = new ArrayList<>();
        String query                        = "SELECT * FROM " + DataBaseManager.NOMBRE_TABLA_PORCENTAJECORTES;

        // Abriendo la base de datos en forma de lectira
        SQLiteDatabase db   = this.getWritableDatabase();
        Cursor registros    = db.rawQuery(query,null);

        // Recorriendo los registros
        while (registros.moveToNext())
        {
            //setea en e campo id
            porcentajes.add( new Porcentaje(
                    // Obtengo el id
                    registros.getDouble(
                            registros.getColumnIndex(DataBaseManager.PORCENTAJECORTES_VALOR)
                    )
            ).setId(
                    registros.getInt(
                            registros.getColumnIndex(DataBaseManager.PORCENTAJECORTES_ID)
                    )
            ) );

        }
        db.close();

        return porcentajes;
    }


    //HACE UNA CONSULTA A LA BASE DE DATOS Y GUARDA LOS DATOS EN UN ARRAY
    public ArrayList<Corte> getAllCortes()
    {
        // Arreglo de materias
        ArrayList<Corte>  cortes        = new ArrayList<>();
        String query                    = "SELECT * FROM " + DataBaseManager.NOMBRE_TABLA_CORTEMATERIA;

        // Abriendo la base de datos en forma de lectira
        SQLiteDatabase db   = this.getWritableDatabase();
        Cursor registros    = db.rawQuery(query,null);

        // Recorriendo los registros
        while (registros.moveToNext())
        {
            //setea en e campo id
            cortes.add( new Corte(
                    // Obtengo el id
                    registros.getDouble(
                            registros.getColumnIndex(DataBaseManager.CORTEMATERIA_NOTADEFINITIVA)
                    ),
                    registros.getDouble(
                            registros.getColumnIndex(DataBaseManager.CORTEMATERIA_NOTAPARCIAL)
                    )
                )
            );

        }
        db.close();

        return cortes;
    }

    //HACE UNA CONSULTA A LA BASE DE DATOS Y GUARDA LOS DATOS EN UN ARRAY
    public ArrayList<Nota> getAllNotas()
    {
        // Arreglo de materias
        ArrayList<Nota>  notas  = new ArrayList<>();
        String query            = "SELECT * FROM " + DataBaseManager.NOMBRE_TABLA_NOTA;

        // Abriendo la base de datos en forma de lectira
        SQLiteDatabase db   = this.getWritableDatabase();
        Cursor registros    = db.rawQuery(query,null);

        // Recorriendo los registros
        while (registros.moveToNext())
        {
            //setea en e campo id
            notas.add( new Nota(
                            // Obtengo el id
                            registros.getDouble(
                                    registros.getColumnIndex(DataBaseManager.NOTA_VALOR)
                            )
                    )
            );

        }
        db.close();

        return notas;
    }

    //metodo que inserta una materia registros de la bd
    public  void  addMateria(String Table, ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Table, null, contentValues);
        db.close();
    }

    // - - - - - - - - - - - - - - - - - - - - - -  - - - - - -
    // - - - - - - ACTUALIZAR  - - - - -  - - - - - -
    // - - - - - - - - - - - - - - - - - - - - - -  - - - - - -


    //metodo que inserta una materia registros de la bd
    public  void  updateMateria(String Table, String column, int id, ContentValues contentValues)
    {
        SQLiteDatabase  db          = this.getWritableDatabase();
        String[]        parameters  = new String[] { String.valueOf( id ) };

        db.update(Table, contentValues, column + " = ?", parameters);
        db.close();
    }

    // - - - - - - - - - - - - - - - - - - - - - -  - - - - - -
    // - - - - - - ELIMINAR  - - - - -  - - - - - -
    // - - - - - - - - - - - - - - - - - - - - - -  - - - - - -


    //metodo que inserta una materia registros de la bd
    public  void  deleteMateria(String Table, String column, int id)
    {
        SQLiteDatabase  db          = this.getWritableDatabase();
        String[]        parameters  = new String[] { String.valueOf( id ) };

        db.delete(Table, column + " = ?", parameters);
        db.close();
    }


}
