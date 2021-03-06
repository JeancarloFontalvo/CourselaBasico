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
    public static  final String MATERIA_CORTE1          =   "materia_corte1";
    public static  final String MATERIA_CORTE1_PARCIAL  =   "materia_corte1_parcial";
    public static  final String MATERIA_CORTE1_SUBNOTA  =   "materia_corte1_subnota";
    public static  final String MATERIA_CORTE2          =   "materia_corte2";
    public static  final String MATERIA_CORTE2_PARCIAL  =   "materia_corte2_parcial";
    public static  final String MATERIA_CORTE2_SUBNOTA  =   "materia_corte2_subnota";
    public static  final String MATERIA_CORTE3          =   "materia_corte3";
    public static  final String MATERIA_CORTE3_PARCIAL  =   "materia_corte3_parcial";
    public static  final String MATERIA_CORTE3_SUBNOTA  =   "materia_corte3_subnota";
    public static  final String MATERIA_DEFINITIVA      =   "materia_definitiva";

    //estas son las constantes de la tabla corteMateria
    public static  final String NOMBRE_TABLA_CORTEMATERIA   =   "tbl_cortemateria";
    public static  final String CORTEMATERIA_ID             =   "cortemateria_id";
    public static  final String CORTEMATERIA_NOTADEFINITIVA =   "cortemateria_notaDefinitiva";
    public static  final String CORTEMATERIA_NOTAPARCIAL        =   "cortemateria_notaParcial";
    public static  final String CORTEMATERIA_PORCENTAJE         =   "cortemateria_porcentaje";
    public static  final String CORTEMATERIA_PORCENTAJENOTA     =   "cortemateria_porcentajeNota";
    public static  final String CORTEMATERIA_PORCENTAJEPARCIAL  =   "cortemateria_porcentajeParcial";

    //estas son las constantes de la tabla corteMateria
    public static  final String NOMBRE_TABLA_NOTA           =   "tbl_nota";
    public static  final String NOTA_ID                     =   "nota_id";
    public static  final String NOTA_VALOR                  =   "nota_valor";
    //public static  final String NOTA_NOTAPARCIAL          =   "nota_notaParcial";

    //CAMPOS DE LA TABLA DE CONFIGURACION  DE LOS CORTES

    public static  final String NOMBRE_TABLA_PORCENTAJECORTES   =   "tbl_porcentajecortes";
    public static  final String PORCENTAJECORTES_ID             =   "corte_id";
    public static  final String PORCENTAJECORTES_CORTE1         =   "corte1";
    public static  final String PORCENTAJECORTES_CORTE2         =   "corte2";
    public static  final String PORCENTAJECORTES_CORTE3         =   "corte3";

    public static  String restultado[] = new String[9];;


    //sentencias sql ara crear la tabla MATERIAs
    public static   final String CREAR_TABLA_MATERIA =
            " create table " + NOMBRE_TABLA_MATERIA+" ("+
                    MATERIA_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    MATERIA_NOMBRE_MATERIA + " TEXT , " +
                    MATERIA_CORTE1     + " REAL  , " +
                    MATERIA_CORTE2     + " REAL  , " +
                    MATERIA_CORTE3     + " REAL  , " +
                    MATERIA_CORTE1_PARCIAL     + " REAL  , " +
                    MATERIA_CORTE2_PARCIAL     + " REAL  , " +
                    MATERIA_CORTE3_PARCIAL     + " REAL  , " +
                    MATERIA_CORTE1_SUBNOTA     + " VARCHAR(100)  , " +
                    MATERIA_CORTE2_SUBNOTA     + " VARCHAR(100)  , " +
                    MATERIA_CORTE3_SUBNOTA     + " VARCHAR(100)  , " +
                    MATERIA_DEFINITIVA     + " REAL  , " +
                    MATERIA_FOTO           + " INTEGER NOT NULL"+");";

    public static  final String CREAR_TABLA_PORCETAJECORTES=
            "create table " + NOMBRE_TABLA_PORCENTAJECORTES+" ( "+
                    PORCENTAJECORTES_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    PORCENTAJECORTES_CORTE1 + " REAL NOT NULL, "+
                    PORCENTAJECORTES_CORTE2 + " REAL NOT NULL,  "+
                    PORCENTAJECORTES_CORTE3 + " REAL NOT NULL ); ";

    public static  final String CREAR_TABLA_CORTEMATERIA=
            "create table " + NOMBRE_TABLA_CORTEMATERIA+" ( "+
                    CORTEMATERIA_ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    CORTEMATERIA_NOTADEFINITIVA     + " REAL NOT NULL, "+
                    CORTEMATERIA_NOTAPARCIAL        + " REAL NOT NULL, "+
                    CORTEMATERIA_PORCENTAJE         + " REAL NOT NULL, "+
                    CORTEMATERIA_PORCENTAJENOTA     + " REAL NOT NULL, "+
                    MATERIA_ID                      + " INTEGER NOT NULL, " +
                    " FOREIGN KEY ('" + MATERIA_ID + "') REFERENCES " +
                    NOMBRE_TABLA_MATERIA + "('" + MATERIA_ID + "') on delete cascade on update cascade); " ;

    public static  final String CREAR_TABLA_NOTAS=
            "create table " +NOMBRE_TABLA_NOTA+" ( "+
                    NOTA_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    NOTA_VALOR          + " REAL NOT NULL, "+
                    CORTEMATERIA_ID     + " INTEGER NOT NULL, "+
                    " FOREIGN KEY ('" + CORTEMATERIA_ID + "') REFERENCES " +
                    NOMBRE_TABLA_CORTEMATERIA + "('" + CORTEMATERIA_ID + "') on delete cascade on update cascade  ); ";

    public static final String CONFIGURE_PORCENTAJES =
            " INSERT INTO " + NOMBRE_TABLA_PORCENTAJECORTES + "(" +
                PORCENTAJECORTES_CORTE1 + "," +
                PORCENTAJECORTES_CORTE2 + "," +
                PORCENTAJECORTES_CORTE3 +
            ")" +
            "VALUES( 20, 30, 50 )";


}

