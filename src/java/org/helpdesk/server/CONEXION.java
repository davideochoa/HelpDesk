package org.helpdesk.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author L.I. David Enrique Ochoa Diaz <davideochoa@gmail.com>
 */

public class CONEXION {
    public Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    public ResultSetMetaData resultado_meta_datos;
    
    public boolean error_conexion = false;
    public boolean TransaccionesActivo = false;

    public boolean valor_logico = false;
    public int valor_entero = 0;
    public String valor_string = new String();
    public String arreglo[][] = new String[0][0];
    public String vector[] = new String[0];

    public java.sql.Date fecha_date;
    public java.sql.Time hora_time;
    public java.sql.Timestamp time_stamp;
    public java.util.Date fecha_util_date;
    public java.util.Calendar fecha_calendar;
    public SimpleDateFormat FormatoFechaSimple = new SimpleDateFormat("dd-MM-yyyy");

    DateFormat formateo_fecha ;
    
    private boolean modo_debug = false;
    private int cantidad_renglones = 0;
    private int cantidad_columnas = 0;
    private int posy = 0;
    private int posx = 0;
    PreparedStatement SentenciaPreparada;
    public CONEXION(String lookup,boolean modo_debug){
        Context init;
        Context context;
        DataSource ds;

        this.modo_debug = modo_debug;

        try{
            init = new InitialContext();
            context = (Context)init.lookup("java:comp/env");
            ds   = (DataSource)context.lookup(lookup);        
            conexion = ds.getConnection();
            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //ejecutar("SET LOCK_TIMEOUT 20000");
        }
        catch(NamingException error){
            on_error(error,"","CONEXION()");
        }
        catch(SQLException sqlerror){
            on_error(sqlerror,"","CONEXION()");
        }
    }//fin CONEXION
    public static CONEXION conectar_bd(){
        CONEXION conexion = new CONEXION("jdbc/helpdesk",false);
        return conexion;
    }//fin conectar_bd
    public void cerrarConexion(){
        try {
            /*
            if(TransaccionesActivo == true)
                FinalizarTransaccion();*/
                        
            if(sentencia != null)
                sentencia.close();
            
            if(resultado != null)
                resultado.close();

            if(conexion != null)
                conexion.close();            
        }
        catch (SQLException error_sql){
            on_error(error_sql,"","cerrarConexion");
        }
    }
    public void IniciarTransaccion(){
        try {
            valor_logico = sentencia.execute("BEGIN TRANSACTION;");
            TransaccionesActivo = true;
        }//fin try
        catch (SQLException error_sql) {
            on_error(error_sql,"BEGIN TRAN;","ejecutar()");
        }//fin catch
    }
    public void IniciarTransaccion(String [] tablas){
        try {
            valor_logico = sentencia.execute("BEGIN TRANSACTION;");            
            TransaccionesActivo = true;
            int pos_y = 0;
            while(pos_y < tablas.length){
                ejecutar_query("SELECT * FROM "+tablas[pos_y].toString()+" WITH (TABLOCKX);");
                pos_y++;
            }
        }//fin try
        catch (SQLException error_sql) {
            on_error(error_sql,"BEGIN TRAN;","ejecutar()");
        }//fin catch
    }
    public void FinalizarTransaccion(){
        try {
            valor_logico = sentencia.execute("COMMIT TRAN;");            
        }//fin try
        catch (SQLException error_sql) {
            TransaccionesActivo = false;
            on_error(error_sql,"COMMIT TRANSACTION;","ejecutar()");
        }//fin catch
    }    
    public boolean ejecutar(String instruccion) {
        if(modo_debug)
            System.out.println("ejecutar: "+instruccion);

        try {            
            valor_logico = sentencia.execute(instruccion);
            
        }//fin try
        catch (SQLException error_sql) {
            on_error(error_sql,instruccion,"ejecutar()");
        }//fin catch
        
        return valor_logico;
    }
    public void ejecutar_query(String instruccion) {
        if(modo_debug)
            System.out.println("ejecutar_query: "+instruccion);
        
        try {
            resultado = sentencia.executeQuery(instruccion);            
        }
        catch (NullPointerException error_sql) {
            on_error(error_sql,instruccion,"ejecutar_query");
        }
        catch (SQLException error_sql) {
            on_error(error_sql,instruccion,"ejecutar_query");
        }
        
    }
    public void ejecutar_update(String instruccion) {
        if(modo_debug)
            System.out.println("ejecutar_update: "+instruccion);
        try{            
            valor_entero = sentencia.executeUpdate(instruccion);
            error_conexion = false;
        }
        catch (SQLException error_sql) {
            on_error(error_sql,instruccion,"ejecutar_update");
        }//fin catch

    }    
    public String[][] ObtenerArregloString(String instruccion){
        cantidad_renglones = 0;
        cantidad_columnas = 0;
        arreglo = new String[0][0];
        ejecutar_query(instruccion);        

        try{            
            while(resultado.next())
                cantidad_renglones++; 
            
            if(cantidad_renglones > 0){
                resultado.beforeFirst();
                cantidad_columnas = resultado.getMetaData().getColumnCount();                
                arreglo = new String[cantidad_renglones][cantidad_columnas];
                posy = 0;
                while(resultado.next()){
                    int posx = 0;
                    while(posx < cantidad_columnas){
                        //System.out.println("resultado.getString(posx+1):"+resultado.getString(posx+1));
                        arreglo[posy][posx] = resultado.getString(posx+1);
                        if(arreglo[posy][posx] == null)
                            arreglo[posy][posx] = new String();
                        posx++;
                    }
                    posy++;
                }
            }
        }
        catch(SQLException error_sql) {
            on_error(error_sql,instruccion,"obtener_arreglo()");
        }
        return arreglo;
    }
    public String[] obtener_vector(String instruccion) {
        cantidad_renglones = 0;
        cantidad_columnas = 0;
        vector = new String[0];
        ejecutar_query(instruccion);
        
        try{
            while(resultado.next())
                cantidad_renglones++;
            //System.out.println("cantidad_renglones:"+cantidad_renglones);

            if(cantidad_renglones > 0){
                resultado.beforeFirst();
                cantidad_columnas = resultado.getMetaData().getColumnCount();
                //System.out.println("cantidad_columnas:"+cantidad_columnas);
                vector = new String[cantidad_columnas];
                resultado.next();
                posx = 0;
                while(posx < cantidad_columnas){
                    vector[posx] = resultado.getString(posx+1);
                    if(vector[posx] == null)
                        vector[posx] = new String();
                    //System.out.println("posx:"+posx+":"+"vector[posx]"+vector[posx].toString());
                    posx++;
                }
            }
        }
        catch(SQLException error_sql) {
            on_error(error_sql,instruccion,"obtener_vector()");
        }
        return vector;
    }
    public int obtener_valor_entero(String instruccion){
        ejecutar_query(instruccion);
        try{
            if(resultado.next())
                valor_entero = resultado.getInt(1);
            else
                valor_entero = 0;
            }//fin try
            catch(SQLException error_sql){
                on_error(error_sql,instruccion,"obtener_valor_entero()");
                valor_entero = 0;
        }//fin catch
        return valor_entero;
    }
    public float ObtenerValorFloat(String instruccion){
        float valor = 0;

        ejecutar_query(instruccion);
        try{
            if(resultado.next())
                valor = resultado.getFloat(1);
            else
                valor = 0;
            }//fin try
            catch(SQLException error_sql){
                on_error(error_sql,instruccion,"obtener_valor_float()");
                valor = 0;
        }//fin catch
        
        return valor;
    }
    public java.sql.Timestamp obtener_timestamp(){
        try{
            resultado = sentencia.executeQuery("SELECT CURRENT_TIMESTAMP;");
            resultado.next();
            time_stamp = resultado.getTimestamp(1);

            fecha_date = new Date(time_stamp.getTime());
            hora_time = new Time(time_stamp.getTime());
            fecha_util_date = new java.util.Date(time_stamp.getTime());
            fecha_calendar = Calendar.getInstance();
            fecha_calendar.setTimeInMillis(time_stamp.getTime());
        }
        catch(SQLException error_sql){
            on_error(error_sql,"SELECT CURRENT_TIMESTAMP();","obtener_timestamp()");
        }
        return time_stamp;
    }
    public void on_error(SQLException error,String instruccion,String lugar){  
        error_conexion = true;
        if(TransaccionesActivo == true){           
            TransaccionesActivo = false;
            valor_logico = ejecutar("ROLLBACK TRANSACTION");
        } 
        
        System.out.println("----------------------------------------");
        System.out.println("LUGAR: "+lugar);
        System.out.println("Sentencia: "+instruccion);        
        System.out.println("error.toString(): "+error.toString());
        System.out.println("error.getLocalizedMessage(): "+error.getLocalizedMessage());
        System.out.println("error.getMessage(): "+error.getMessage());
        System.out.println("error.getSQLState(): "+error.getSQLState());
        System.out.println("error.getErrorCode(): "+error.getErrorCode());
        //System.out.println("error.getCause().toString(): "+error.getCause().toString());
        //System.out.println("error.getCause().getLocalizedMessage(): "+error.getCause().getLocalizedMessage());
        //System.out.println("error.getCause().getMessage(): "+error.getCause().getMessage());
    }//fin on_error
    public void on_error(NullPointerException error,String instruccion,String lugar) {
        error_conexion = true;
        if(TransaccionesActivo == true){           
            TransaccionesActivo = false;
            valor_logico = ejecutar("ROLLBACK TRANSACTION");
        } 
        
        System.out.println("----------------------------------------");
        System.out.println("LUGAR: "+lugar);
        System.out.println("Sentencia: "+instruccion);        
        System.out.println("error.toString(): "+error.toString());
        System.out.println("error.getLocalizedMessage(): "+error.getLocalizedMessage());
        System.out.println("error.getMessage(): "+error.getMessage());
        //System.out.println("error.getSQLState(): "+error.getSQLState());
        //System.out.println("error.getErrorCode(): "+error.getErrorCode());
    }
    public void on_error(NamingException error,String instruccion,String lugar){
        System.out.println("----------------------------------------");
        System.out.println("LUGAR: "+lugar);
        System.out.println("Sentencia: "+instruccion);
        System.out.println("error.toString(): "+error.toString());
        System.out.println("error.getLocalizedMessage(): "+error.getLocalizedMessage());
        System.out.println("error.getMessage(): "+error.getMessage());
        //System.out.println("error.getSQLState(): "+error.getSQLState());
        //System.out.println("error.getErrorCode(): "+error.getErrorCode());
    }
    public PreparedStatement getSentenciaPreparada(String sentencia){
        try {
            SentenciaPreparada = conexion.prepareStatement(sentencia);
        } catch (SQLException ex) {
            //Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            on_error(ex,sentencia,"getSentenciaPreparada");
        }
        return SentenciaPreparada;
    }
//**************************************************************************************************

    public String obtener_valor_string(String instruccion){
        ejecutar_query(instruccion);
        try{
            if(resultado.next())
                valor_string = resultado.getString(1);
            else
                valor_string = new String();
        }//fin try
        catch(SQLException error_sql){
            on_error(error_sql,instruccion,"");
        }//fin catch
        return valor_string;
    }//fin obtener_valor_string

//********************************* NUEVOS *******************************
    public int obtenerCantidadRegistros(ResultSet resultset){
        int pos = 0;
        try {
            while(resultset.next())
                pos++;
            
            resultset.beforeFirst();
        } catch (SQLException ex) {
            on_error(ex,"resultset.next()","CONEXION:obtenerCantidadRegistros");
        }        
        return pos;
    }
}
