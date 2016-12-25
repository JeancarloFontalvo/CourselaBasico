package com.example.chino.courselabasico.baseDatos;

/**
 * Created by CHINO on 22/12/2016.
 */

public class DataBaseManager {
    //estas son las constantes de la tabla estudiante
    public static  final String NOMBRE_TABLA_ESTUDIANTE ="tbl_estudiantes";
    public static  final String ESTUDIANTE_ID ="estudiante_id";
    public static  final String ESTUDIANTE_NOMBRE_MATERIA="estudiante_asignatura";
    public static  final String ESTUDIANTE_NOTA_CORTE1="estudiante_corte1";
    public static  final String ESTUDIANTE_NOTA_CORTE2="estudiante_corte2";
    public static  final String ESTUDIANTE_NOTA_CORTE3="estudiante_corte3";
    public static  final String ESTUDIANTE_FOTO="estudiante_foto";
    //CAMPOS DE LA TABLA DE CONFIGURACION  DE LOS CORTES

    public static  final String NOMBRE_TABLA_CORTES="tbl_cortes";
    public static  final String IDCORTE="corte_id";
    public static  final String CORTE1="corte1";
    public static  final String CORTE2="corte2";
    public static  final String CORTE3="corte3";



    //sentencias sql ara crear la tabla estudiantes
    public static   final String CREAR_TABLA_ESTUDIANTE =
            " create table "+NOMBRE_TABLA_ESTUDIANTE+" ("+
                    ESTUDIANTE_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    ESTUDIANTE_NOMBRE_MATERIA + " TEXT , " +
                    ESTUDIANTE_NOTA_CORTE1    + " TEXT , "+
                    ESTUDIANTE_NOTA_CORTE2    + " TEXT , "+
                    ESTUDIANTE_NOTA_CORTE3    + " TEXT , "+
                    ESTUDIANTE_FOTO           + " INTEGER NOT NULL"+");";

    public static  final String CREAR_TABLA_CORTES=
            "create table " +NOMBRE_TABLA_CORTES+" ( "+
                    IDCORTE + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    CORTE1  + " INTEGER NOT NULL, "+
                    CORTE2  + " INTEGER NOT NULL, "+
                    CORTE3  + " INTEGER NOT NULL ); ";


}

