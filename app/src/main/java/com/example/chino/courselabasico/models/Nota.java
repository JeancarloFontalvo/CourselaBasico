package com.example.chino.courselabasico.models;

import android.content.Context;

import com.example.chino.courselabasico.baseDatos.DataBaseManager;

import java.io.Serializable;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Nota extends ModelDB implements Serializable
{
    private int     id;
    private double  valor;
    public  int     corteId;

    public Nota()
    {
        super(null);
        this.primaryKey = DataBaseManager.NOTA_ID;
        this.tableName  = DataBaseManager.NOMBRE_TABLA_NOTA;
    }

    public Nota(Context context)
    {
        super(context);
        this.primaryKey = DataBaseManager.NOTA_ID;
    }


    public Nota(double valor)
    {
        this();
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public Nota setId(int id) {
        this.id = id;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Nota setValor(double valor) {
        this.valor = valor;
        return this;
    }

    @Override
    protected void generateColumns()
    {
        this.columnsData.put(this.primaryKey, String.valueOf(this.id) );
        this.columnsData.put(DataBaseManager.NOTA_VALOR, String.valueOf(this.valor) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_ID, String.valueOf(this.corteId) );
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.valor);
    }
}
