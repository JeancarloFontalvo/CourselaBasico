package com.example.chino.courselabasico.models;

/**
 * Created by FAMILY on 25/12/2016.
 */
/**
 * @author Juan David Redondo
 * @description Modelo POCO
 * */
public class Porcentaje
{
    private int     id;
    private double  valor;

    public Porcentaje(double valor){ this.valor = valor; }

    public int getId() {
        return id;
    }


    public Porcentaje setId(int id) {
        this.id = id;

        return this;
    }

    public double getValor() {
        return valor;
    }

    public Porcentaje setValor(double valor) {
        this.valor = valor;

        return this;
    }
}
