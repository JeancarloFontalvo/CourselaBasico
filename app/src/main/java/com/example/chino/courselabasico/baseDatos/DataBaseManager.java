package com.example.chino.courselabasico.baseDatos;

/**
 * Created by CHINO on 22/12/2016.
 */

public class DataBaseManager {
    //estas son las constantes de la tabla estudiante
    public static  final String NOMBRE_TABLA_MATERIA    =   "tbl_materias";
    public static  final String MATERIA_ID              =   "materia_id";
    public static  final String MATERIA_NOMBRE_MATERIA  =   "materia_nombre";
    public static  final String MATERIA_FOTO            =   "materia_foto";
    public static  final String MATERIA_DEFINITIVA      =   "materia_definitiva";

    //estas son las constantes de la tabla corteMateria
    public static  final String NOMBRE_TABLA_CORTEMATERIA   =   "tbl_cortemateria";
    public static  final String CORTEMATERIA_ID             =   "cortemateria_id";
    public static  final String CORTEMATERIA_NOTADEFINITIVA =   "cortemateria_notaDefinitiva";
    public static  final String CORTEMATERIA_NOTAPARCIAL    =   "cortemateria_notaParcial";

    //estas son las constantes de la tabla corteMateria
    public static  final String NOMBRE_TABLA_NOTA           =   "tbl_nota";
    public static  final String NOTA_ID                     =   "nota_id";
    public static  final String NOTA_VALOR                  =   "nota_valor";
    //public static  final String NOTA_NOTAPARCIAL          =   "nota_notaParcial";

    //CAMPOS DE LA TABLA DE CONFIGURACION  DE LOS CORTES

    public static  final String NOMBRE_TABLA_PORCENTAJECORTES   =   "tbl_porcentajecortes";
    public static  final String PORCENTAJECORTES_ID             =   "corte_id";
    public static  final String PORCENTAJECORTES_VALOR          =   "valor";



    //sentencias sql ara crear la tabla MATERIAs
    public static   final String CREAR_TABLA_MATERIA =
            " create table " + NOMBRE_TABLA_MATERIA+" ("+
                    MATERIA_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    MATERIA_NOMBRE_MATERIA + " TEXT , " +
                    MATERIA_DEFINITIVA     + " REAL  , " +
                    MATERIA_FOTO           + " INTEGER NOT NULL"+");";

    public static  final String CREAR_TABLA_PORCETAJECORTES=
            "create table " + NOMBRE_TABLA_PORCENTAJECORTES+" ( "+
                    PORCENTAJECORTES_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    PORCENTAJECORTES_VALOR  + " REAL NOT NULL ); ";

    public static  final String CREAR_TABLA_CORTEMATERIA=
            "create table " + NOMBRE_TABLA_CORTEMATERIA+" ( "+
                    CORTEMATERIA_ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    CORTEMATERIA_NOTADEFINITIVA     + " REAL NOT NULL, "+
                    CORTEMATERIA_NOTAPARCIAL        + " REAL NOT NULL, "+
                    MATERIA_ID                      + " INTEGER NOT NULL, " +
                    PORCENTAJECORTES_ID             + " INTEGER NOT NULL, " +
                    " FOREIGN KEY ('" + MATERIA_ID + "') REFERENCES " +
                    NOMBRE_TABLA_MATERIA + "('" + MATERIA_ID + "'), " +
                    " FOREIGN KEY ('" + PORCENTAJECORTES_ID + "') REFERENCES " +
                    NOMBRE_TABLA_PORCENTAJECORTES + "('" + PORCENTAJECORTES_ID + "')  ); ";

    public static  final String CREAR_TABLA_NOTAS=
            "create table " +NOMBRE_TABLA_NOTA+" ( "+
                    NOTA_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    NOTA_VALOR          + " REAL NOT NULL, "+
                    CORTEMATERIA_ID     + " INTEGER NOT NULL, "+
                    " FOREIGN KEY ('" + CORTEMATERIA_ID + "') REFERENCES " +
                    NOMBRE_TABLA_CORTEMATERIA + "('" + CORTEMATERIA_ID + "')  ); ";


}

