package com.example.chino.courselabasico.pojo;

/**
 * Created by CHINO on 25/09/2016.
 */

public class Contacto {

    //atributos
    private int id_contacto;
    private String nombre;
    private String notaCorte1;
    private String notaCorte2;
    private String notaCorte3;
    private int foto;


    public Contacto(int foto, String nombre,String notaCorte1, String notaCorte2, String notaCorte3) {
        this.foto=foto;
        this.nombre = nombre;

        this.notaCorte1 = notaCorte1;
        this.notaCorte2 = notaCorte2;
        this.notaCorte3 = notaCorte3;
    }

    public Contacto() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    //getter and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNotaCorte1() {
        return notaCorte1;
    }

    public void setNotaCorte1(String notaCorte1) {
        this.notaCorte1 = notaCorte1;
    }

    public String getNotaCorte2() {
        return notaCorte2;
    }

    public void setNotaCorte2(String notaCorte2) {
        this.notaCorte2 = notaCorte2;
    }

    public String getNotaCorte3() {
        return notaCorte3;
    }

    public void setNotaCorte3(String notaCorte3) {
        this.notaCorte3 = notaCorte3;
    }
}
