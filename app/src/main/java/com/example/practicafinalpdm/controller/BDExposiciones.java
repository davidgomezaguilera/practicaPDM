package com.example.practicafinalpdm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practicafinalpdm.modelos.Artista;
import com.example.practicafinalpdm.modelos.Comentario;
import com.example.practicafinalpdm.modelos.Exponen;
import com.example.practicafinalpdm.modelos.Exposicion;
import com.example.practicafinalpdm.modelos.Trabajo;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BDExposiciones extends SQLiteOpenHelper {
    private Context contexto;
    // Sentencia SQL para crear la tabla de Usuarios
    private final String SQLCREATEExpo = "CREATE TABLE IF NOT EXISTS Exposiciones (idExpo INTEGER primary key, nombreExp TEXT, descripcionExp TEXT, fechaInicioExp DATE, fechaFinExpo DATE)";
    // Sentencia SQL para eliminar la tabla de Usuarios
    //private final String SQLDROPExpo = "DROP TABLE IF EXISTS Usuarios";


    private final String SQLCREATEExponen = "CREATE TABLE IF NOT EXISTS Exponen (idExpo INTEGER, DniPasaporte TEXT, foreign key (idExpo) references Exposiciones(idExpo), foreign key (DniPasaporte) references Artistas (dniPasaporte), primary key(idExpo, DniPasaporte) )";
    private final String SQLDROP = "DROP TABLE IF EXISTS Artistas";//para borrar cualquier ta bla

    private final String SQLCREATEArtistas = "CREATE TABLE IF NOT EXISTS Artistas(dniPasaporte TEXT primary key, nombre TEXT, direccion TEXT, poblacion TEXT, provincia TEXT, pais TEXT, movilTrabajo TEXT, movilPersonal TEXT, telefonoFijo TEXT, email TEXT, webBlog TEXT, fechaNacimiento TEXT)";
    private final String SQLCREATETrabajos = "CREATE TABLE IF NOT EXISTS Trabajos(nombreTrabajo TEXT primary key,  descripcion TEXT, tamanio INTEGER, peso INTEGER, dniPasaporte TEXT, foto TEXT, foreign key (dniPasaporte) references Artistas(dniPasaporte)) ";
    private final String SQLCREATEComentarios = "CREATE TABLE IF NOT EXISTS Comentarios(idExpo INTEGER, nombreTrabajo TEXT, comentario TEXT, foreign key (idExpo) references Exposiciones(idExpo), foreign key (nombreTrabajo) references Trabajo(nombreTrabajo), primary key(idExpo, nombreTrabajo))";

    // Base de datos
    private SQLiteDatabase bd = null;
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Exposiciones.db";

    public BDExposiciones(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
        //this.onCreate(this.bd);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(SQLCREATEExpo);
        db.execSQL(SQLCREATEExponen);
        db.execSQL(SQLCREATEArtistas);
        db.execSQL(SQLCREATETrabajos);
        db.execSQL(SQLCREATEComentarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        /*
            NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
            eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
            Sin embargo lo normal será que haya que migrar datos de la tabla antigua
            a la nueva, por lo que este método debería ser más elaborado.
         */

        // Se elimina la versión anterior de la tabla
        db.execSQL(SQLDROP);//SQLDROPExponen

        // Se crea la nueva versión de la tabla
        db.execSQL(SQLCREATEExpo);
    }

    public void cerrarBD() {
        if (bd != null) {
            System.out.println("cerrando bd");
            bd.close();
        }
    }

    public void deleteExpo(String idExpo){
        bd = getWritableDatabase();
        System.out.println("DELETE FROM Exposiciones WHERE idExpo = "+Integer.parseInt(idExpo));
        bd.execSQL("DELETE FROM Exposiciones WHERE idExpo="+Integer.parseInt(idExpo));



    }

    public long insertarExposiciones(Exposicion expo) {
        // Obtengo los datos en modos de escritura
        bd = getWritableDatabase();
        long newRowId = 0;
        // Si hemos abierto correctamente la base de datos
        if (bd != null) {
            //Exposicion expo = new Exposicion();
            String id = expo.getIdExposicion();
            String nombre = expo.getNombreExpo();
            String descripcion = expo.getDescripcion();
            Date fechaInicio = expo.getFechaInicio();
            Date fechaFin = expo.getFechaFin();
            try{
                ContentValues valores = new ContentValues();

                valores.put("nombreExp", nombre);
                valores.put("descripcionExp", descripcion);
                valores.put("fechaInicioExp", ""+fechaInicio);
                valores.put("fechaFinExpo", ""+fechaFin);

                newRowId = bd.insert("Exposiciones", "", valores);
                expo.setIdExposicion(""+newRowId);

            }catch (Exception e){
                newRowId = -1;
                e.printStackTrace();
            }

        }
        return newRowId;
    }
    public long insertarArtista(Artista artista){

        bd = getWritableDatabase();
        long newRowId = 1;
        // Si hemos abierto correctamente la base de datos
        if (bd != null) {
            //Exposicion expo = new Exposicion();

            String dniPasaporte = artista.getDniPasaporte();
            String nombre = artista.getNombre();
            String direccion = artista.getDireccion();
            String poblacion = artista.getPoblacion();
            String provincia = artista.getProvincia();
            String pais = artista.getPais();
            String movilTrabajo = artista.getMovilTrabajo();
            String movilPersonal = artista.getMovilPersonal();
            String telefonoFijo = artista.getTelefonoFijo();
            String email = artista.getEmail();
            String webBlog = artista.getWebBlog();
            Date fechaNacimiento = artista.getFechaNacimiento();


            try{
                ContentValues valores = new ContentValues();

                valores.put("dniPasaporte", dniPasaporte);
                valores.put("nombre", nombre);
                valores.put("direccion", direccion);
                valores.put("poblacion", poblacion);
                valores.put("provincia", provincia);
                valores.put("fechaNacimiento", ""+fechaNacimiento);
                valores.put("pais", pais);
                valores.put("movilTrabajo", movilTrabajo);
                valores.put("movilPersonal", movilPersonal);
                valores.put("telefonoFijo", telefonoFijo);
                valores.put("email", email);
                valores.put("webBlog", webBlog);



                newRowId = bd.insert("Artistas", "", valores);


            }catch (Exception e){
                newRowId = -1;
                e.printStackTrace();
            }
        }

    return newRowId;
    }
    public long insertarExponen(Exponen exponen){

        long idInsertado = 0;
        try{
            ContentValues valores = new ContentValues();

            valores.put("idExpo", exponen.getIdExpo());
            valores.put("DniPasaporte", exponen.getDniArtista());
            idInsertado = bd.insert("Exponen", "",valores);

        }catch(Exception e){
            idInsertado = -1;
            e.printStackTrace();
        }

        return idInsertado;
    }


    public long insertarTrabajo(Trabajo trabajo){
        bd = getWritableDatabase();
        long newRowId = 1;
        // Si hemos abierto correctamente la base de datos
        if (bd != null) {
            //Exposicion expo = new Exposicion();

            try{
                ContentValues valores = new ContentValues();
// private final String SQLCREATETrabajos = "CREATE TABLE IF NOT EXISTS Trabajos(nombreTrabajo TEXT primary key,  descripcion TEXT, tamanio INTEGER, peso INTEGER, dniPasaporte TEXT, foto TEXT, foreign key (dniPasaporte) references Artistas(dniPasaporte)) ";
//
                valores.put("nombreTrabajo", trabajo.getNombreTrabajo() );
                valores.put("descripcion", trabajo.getDescripcion());
                valores.put("tamanio", trabajo.getTamanio());
                valores.put("peso", trabajo.getPeso());
                valores.put("dniPasaporte", trabajo.getDniArtista());
                valores.put("foto", trabajo.getFoto());




                newRowId = bd.insert("Trabajos", "", valores);


            }catch (Exception e){
                newRowId = -1;
                e.printStackTrace();
            }
        }

        return newRowId;
    }

    public long insertarComentario(Comentario comentario){
        bd = getWritableDatabase();
        long newRowId = 1;
        // Si hemos abierto correctamente la base de datos
        if (bd != null) {
            //Exposicion expo = new Exposicion();

            try{
                ContentValues valores = new ContentValues();
// private final String SQLCREATETrabajos = "CREATE TABLE IF NOT EXISTS Trabajos(nombreTrabajo TEXT primary key,  descripcion TEXT, tamanio INTEGER, peso INTEGER, dniPasaporte TEXT, foto TEXT, foreign key (dniPasaporte) references Artistas(dniPasaporte)) ";

                valores.put("idExpo", comentario.getIdExpo() );
                valores.put("nombreTrabajo", comentario.getNombreTrabajo());
                valores.put("comentario", comentario.getComentario());

                newRowId = bd.insert("Comentarios", "", valores);

            }catch (Exception e){
                newRowId = -1;
                e.printStackTrace();
            }
        }

        return newRowId;
    }



    public ArrayList<Exposicion> consultarExpo() {
        // Obtengo los datos en modo de lectura
        ArrayList<Exposicion> listaExpo = new ArrayList<Exposicion>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        bd = getReadableDatabase();

        // Si hemos abierto correctamente la base de datos
        if (bd != null) {

            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Exposiciones", // Tabla para consultar
                            null,       // Columnas a devolver
                            null,       // Columnas de la clausula WHERE
                            null,       // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,       // Valores de la clausula HAVING
                            null   // Orden de la clausula ORDER BY
                    );

            int i = 1;
            if(c.moveToFirst()){
                do{
                    Exposicion nueva = new Exposicion(c.getString(0),c.getString(1),c.getString(2),new Date(c.getString(3)),new Date(c.getString(4)));
                    //System.out.println(c.getString(1));
                    listaExpo.add(nueva);
                }while(c.moveToNext());
            }
        }
        return listaExpo;
    }

    public Artista consultarArtista(String[] dni){

        Artista nuevo = null;
        bd = getWritableDatabase();
        if (bd != null) {

            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Artistas", // Tabla para consultar
                            null,       // Columnas a devolver
                            "dniPasaporte=?",       // Columnas de la clausula WHERE
                            dni,       // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,       // Valores de la clausula HAVING
                            null   // Orden de la clausula ORDER BY
                    );

            int i = 1;
            if(c.moveToFirst()){
                do{
                    nuevo = new Artista(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),new Date(c.getString(11)));
                    //System.out.println(c.getString(1));
                }while(c.moveToNext());
            }
        }
        return nuevo;
    }
    public ArrayList<Comentario> consultarComentarios(String[] nombreTrabajo){

        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        Comentario nuevo = null;
        bd = getWritableDatabase();
        if (bd != null) {

            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Comentarios", // Tabla para consultar
                            null,       // Columnas a devolver
                            "nombreTrabajo=?",       // Columnas de la clausula WHERE
                            nombreTrabajo,       // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,       // Valores de la clausula HAVING
                            null   // Orden de la clausula ORDER BY
                    );

            int i = 1;
            if(c.moveToFirst()){
                do{
                    nuevo = new Comentario(c.getString(0),c.getString(1),c.getString(2));
                    //System.out.println(c.getString(1));
                    listaComentarios.add(nuevo);
                }while(c.moveToNext());
            }
        }
        return listaComentarios;
    }
    public ArrayList<Exponen> consultarExponen(String[] idExpo){
        ArrayList<Exponen> listaExponen = new ArrayList<Exponen>();
        bd = getWritableDatabase();
        if (bd != null) {

            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Exponen", // Tabla para consultar
                            null,       // Columnas a devolver
                            "idExpo=?",       // Columnas de la clausula WHERE
                            idExpo,       // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,       // Valores de la clausula HAVING
                            null   // Orden de la clausula ORDER BY
                    );

            int i = 1;
            if(c.moveToFirst()){
                do{
                    Exponen nuevo = new Exponen(c.getString(0), c.getString(1));
                    //System.out.println(c.getString(1));
                    listaExponen.add(nuevo);
                }while(c.moveToNext());
            }
        }
        return listaExponen;

    }

    public Trabajo consultarTrabajo(String[] nombreTrabajo){

        bd = getReadableDatabase();
        Trabajo nuevo = null;
        if(bd!=null){

            Cursor c = bd.query(
                    "Trabajos", // Tabla para consultar
                    null,       // Columnas a devolver
                    "nombreTrabajo=?",       // Columnas de la clausula WHERE
                    nombreTrabajo,       // Valores de la columna de la clausula WHERE
                    null,       // Valores de la clausula GROUP BY
                    null,       // Valores de la clausula HAVING
                    null   // Orden de la clausula ORDER BY
            );
            if(c.moveToFirst()){
                do{
                    nuevo = new Trabajo(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));

                }while(c.moveToNext());
            }
        }

        return nuevo;
    }

    public ArrayList<Trabajo> consultarTrabajos(String[] dni) {
        // Obtengo los datos en modo de lectura
        ArrayList<Trabajo> listaTrabajo = new ArrayList<Trabajo>();
        bd = getReadableDatabase();

        // Si hemos abierto correctamente la base de datos
        if (bd != null) {

            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Trabajos", // Tabla para consultar
                            null,       // Columnas a devolver
                            "dniPasaporte=?",       // Columnas de la clausula WHERE
                            dni,       // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,       // Valores de la clausula HAVING
                            null   // Orden de la clausula ORDER BY
                    );

            int i = 1;
            if(c.moveToFirst()){
                do{
                    Trabajo nuevo = new Trabajo(c.getString(0),c.getString(1),c.getString(2),c.getString(3), c.getString(4), c.getString(5));
                    //System.out.println(c.getString(1));

                    listaTrabajo.add(nuevo);
                }while(c.moveToNext());
            }
        }
        return listaTrabajo;
    }



}