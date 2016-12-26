package com.example.chino.courselabasico.models;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Materia
{
    private int      id;
    private String   nombre;
    private int      imagenId;
    private Double   materiaDefinitiva;

    public static Corte corte1 = null;
    public static Corte corte2 = null;
    public static Corte corte3 = null;




    public Materia(int id, String nombre, int imagenId, Double materiaDefinitiva)
    {

        this.nombre     = nombre;
        this.imagenId   = imagenId;
        this.materiaDefinitiva = materiaDefinitiva;
    }

    public Materia ( String nombre, int imagenId )
    {
        this.nombre     = nombre;
        this.imagenId   = imagenId;
    }

    public int getId() {
        return id;
    }

    public Materia setId(int id) {
        this.id = id;

        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Materia setNombre(String nombre) {
        this.nombre = nombre;

        return this;
    }

    public int getImagenId() {
        return imagenId;
    }

    public Materia setImagenId(int imagenId) {
        this.imagenId = imagenId;

        return this;
    }


    public Double getMateriaDefinitiva() {
        return materiaDefinitiva;
    }

    public void setMateriaDefinitiva(Double materiaDefinitiva) {
        this.materiaDefinitiva = materiaDefinitiva;
    }

    public static void resetCortes()
    {
        corte1 = null;
        corte2 = null;
        corte3 = null;
    }
}
