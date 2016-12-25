package com.example.chino.courselabasico.models;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Nota
{
    private int     id;
    private double  valor;

    public Nota(double valor)
    {
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
}
