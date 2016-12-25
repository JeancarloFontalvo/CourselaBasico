package com.example.chino.courselabasico.models;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Corte
{
    private int     id;
    private double  notaDefinitiva;
    private double  notaParcial;

    public Corte(double notaDefinitiva, double notaParcial)
    {
        this.notaDefinitiva = notaDefinitiva;
        this.notaParcial    = notaParcial;
    }

    public int getId() {
        return id;
    }

    public Corte setId(int id) {
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
}
