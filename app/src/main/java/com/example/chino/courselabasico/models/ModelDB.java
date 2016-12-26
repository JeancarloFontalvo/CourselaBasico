package com.example.chino.courselabasico.models;

import android.content.ContentValues;
import android.content.Context;

import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.baseDatos.DataBaseManager;

import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Created by FAMILY on 25/12/2016.
 */

public abstract class  ModelDB
{
    protected Context                   context                     = null;
    protected BaseDatos                 db                          = null;
    protected String                    tableName                   = "";
    protected HashMap<String, String>   columnsData                 = null;
    protected String                    primaryKey                  = "";

    // Create the parent object
    public ModelDB(Context context)
    {
        this.columnsData = new HashMap<>();
        this.context     = context;
    }

    // Get the current database object from BaseDatos
    protected BaseDatos getDb()
    {
        if(issetContext())
        {
            if(this.db == null)
            {
                this.db = new BaseDatos(context);
            }
        }
        else
            throw new InvalidParameterException("La propiedad context debe definirse");

        return this.db;
    }

    public long save()
    {
        return this.getDb().add(
                this.tableName,
                this.generateContentValue( this.columnsData )
        );
    }

    public long edit(int id)
    {
        if(this.getPrimary().equals( " " ))
            throw new InvalidParameterException("Debe especificar la columna que representa la llave primaria");

        return this.getDb().update(
                this.tableName,
                this.getPrimary(),
                id,
                this.generateContentValue( this.columnsData )
        );
    }

    public long delete(int id)
    {
        if(this.getPrimary().equals( " " ))
            throw new InvalidParameterException("Debe especificar la columna que representa la llave primaria");

        return this.getDb().delete(
                this.tableName,
                this.getPrimary(),
                id
        );
    }

    public ModelDB setContext(Context context)
    {
        this.context = context;
        return this;
    }

    protected boolean issetContext()
    {
        return context != null;
    }

    protected ContentValues generateContentValue(HashMap<String, String> data )
    {
        ContentValues contentData = new ContentValues();
        // Recorremos las columnas para agregarlas al content value

        for(String column : this.columnsData.keySet())
        {
            contentData.put( column, this.columnsData.get( column ) );
        }

        return contentData;
    }

    public String getPrimary()
    {
        return this.primaryKey;
    }

    protected void generateColumns()
    {
        throw new UnsupportedOperationException("generateColumns() debe implementarse");
    }
}
