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
    private double  corte1;
    private double  corte2;
    private double  corte3;

    public Porcentaje(double corte1,double corte2,double corte3)
    {
        this.corte1 = corte1;
        this.corte2 = corte2;
        this.corte3 = corte3;
    }

    public int getId() {
        return id;
    }


    public Porcentaje setId(int id) {
        this.id = id;

        return this;
    }

    public double getcorte1() {
        return corte1;
    }

    public Porcentaje setcorte1(double corte1) {
        this.corte1 = corte1;

        return this;
    }

    public double getCorte2() {
        return corte2;
    }

    public Porcentaje setCorte2(double corte2) {
        this.corte2 = corte2;
        return  this;
    }

    public double getCorte3() {
        return corte3;
    }

    public Porcentaje setCorte3(double corte3) {
        this.corte3 = corte3;
        return  this;
    }
}
