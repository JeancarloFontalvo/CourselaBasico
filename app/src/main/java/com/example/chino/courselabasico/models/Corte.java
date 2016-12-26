package com.example.chino.courselabasico.models;

import android.content.Context;

import com.example.chino.courselabasico.baseDatos.BaseDatos;
import com.example.chino.courselabasico.baseDatos.DataBaseManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Corte extends ModelDB implements Serializable
{
    private long            id;
    private double          notaDefinitiva;
    private double          notaParcial;
    private double          porcentaje;
    private double          porcentajeParcial;
    private double          porcentajeNota;
    private ArrayList<Nota> notas;

    private static Corte corte = null;

    public static Corte getCorte()
    {
        if(corte == null)
            corte = new Corte();

        return corte;
    }

    public static void setCorte(Corte Cortes)
    {
        corte = Cortes;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public Corte addNota(Nota nota)
    {
        this.notas.add( nota );
        return this;
    }

    public Corte addNota(double nota)
    {
        this.notas.add( new Nota( nota ) );
        return this;
    }

    public Corte setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public Corte setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public double getPorcentajeParcial() {
        return porcentajeParcial;
    }

    public Corte setPorcentajeParcial(double porcentajeParcial) {
        this.porcentajeParcial = porcentajeParcial;
        return this;
    }

    public double getPorcentajeNota() {
        return porcentajeNota;
    }

    public Corte setPorcentajeNota(double porcentajeNota) {
        this.porcentajeNota = porcentajeNota;
        return  this;
    }

    public Corte()
    {
        super(null);
        this.notaDefinitiva = 1;
        this.notas      = new ArrayList<>();
        this.primaryKey = DataBaseManager.CORTEMATERIA_ID;
        this.tableName  = DataBaseManager.NOMBRE_TABLA_CORTEMATERIA;
        generateColumns();
    }

    public Corte(Context context)
    {
        super(context);
        this.notaDefinitiva = 1;
        generateColumns();
    }

    public Corte(double notaDefinitiva, double notaParcial)
    {
        this();
        this.notaDefinitiva = notaDefinitiva;
        this.notaParcial    = notaParcial;
    }

    public long getId() {
        return id;
    }

    public Corte setId(long id) {
        this.id = id;
        return this;
    }

    public double getNotaDefinitiva() {
        return notaDefinitiva;
    }

    public Corte setNotaDefinitiva(double notaDefinitiva) {
        this.notaDefinitiva = notaDefinitiva;
        return this;
    }

    public double getNotaParcial() {
        return notaParcial;
    }

    public Corte setNotaParcial(double notaParcial) {
        this.notaParcial = notaParcial;
        return this;
    }

    @Override
    public Corte setContext(Context context)
    {
        super.setContext( context );
        return this;
    }

    public long save()
    {
        long id = super.save();

        if(id > 0)
        {
            for(Nota nota : this.notas)
            {
                nota.save();
            }
        }

        return id;
    }

    @Override
    protected void generateColumns()
    {
        this.columnsData.put(this.primaryKey, String.valueOf(this.id) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_NOTADEFINITIVA, String.valueOf(this.notaDefinitiva) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_NOTAPARCIAL, String.valueOf(this.notaParcial) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_PORCENTAJE, String.valueOf(this.porcentaje) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_PORCENTAJENOTA, String.valueOf(this.porcentajeNota) );
        this.columnsData.put(DataBaseManager.CORTEMATERIA_PORCENTAJEPARCIAL, String.valueOf(this.porcentajeParcial) );
    }



}
