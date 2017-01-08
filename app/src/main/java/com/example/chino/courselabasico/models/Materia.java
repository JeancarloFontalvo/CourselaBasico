package com.example.chino.courselabasico.models;

/**
 * Created by FAMILY on 25/12/2016.
 */

public class Materia
{
    private int      id;
    private String   nombre;
    private int      imagenId;

    private Double   notaCorte1;
    private Double   notaCorte2;
    private Double   notaCorte3;
    private Double   materiaDefinitiva;

    public static Corte corte1 = null;
    public static Corte corte2 = null;
    public static Corte corte3 = null;


    public static Corte getCorte( int id )
    {
        Corte cortecito = null;
        switch (id)
        {
            case 1:
                    corte1      = corte1 == null ? new Corte() : corte1;
                    corte1.setId( 1 );
                    cortecito   = corte1;
                break;
            
            case 2:
                    corte2      = corte2 == null ? new Corte() : corte2;
                    corte2.setId( 2 );
                    cortecito   = corte2;
                break;
            
            case 3:
                    corte3      = corte3 == null ? new Corte() : corte3;
                    corte3.setId( 3 );
                    cortecito   = corte3;
                break;
        }

        return  cortecito;
    }

    public Materia(int id, String nombre, int imagenId, Double notaCorte1, Double notaCorte2, Double notaCorte3, Double materiaDefinitiva)
    {

        this.nombre     = nombre;
        this.imagenId   = imagenId;
        this.notaCorte1 = notaCorte1;
        this.notaCorte2 = notaCorte2;
        this.notaCorte3 = notaCorte3;
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

    public Double getNotaCorte1() {
        return notaCorte1;
    }

    public void setNotaCorte1(Double notaCorte1) {
        this.notaCorte1 = notaCorte1;
    }

    public Double getNotaCorte2() {
        return notaCorte2;
    }

    public void setNotaCorte2(Double notaCorte2) {
        this.notaCorte2 = notaCorte2;
    }

    public Double getNotaCorte3() {
        return notaCorte3;
    }

    public void setNotaCorte3(Double notaCorte3) {
        this.notaCorte3 = notaCorte3;
    }
}
