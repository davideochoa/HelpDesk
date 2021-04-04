/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.helpdesk.client.ModeloDatos.ModeloEstadoDiagnostico;
import org.helpdesk.client.ModeloDatos.ModeloFolioIncidencia;
import org.helpdesk.client.ModeloDatosNuevo.BMMDConfiguracion;
import org.helpdesk.client.ModeloDatosNuevo.BMMDGridBusqueda;
import org.helpdesk.client.ServiciosRPC.RPCMovimientosFolios;

/**
 *
 * @author Administrador
 */
public class RPCMovimientosFoliosImpl extends RemoteServiceServlet implements RPCMovimientosFolios {
    public ModeloFolioIncidencia Insertar(ModeloFolioIncidencia MDFolio){
        CONEXION conexion = CONEXION.conectar_bd();

        conexion.IniciarTransaccion();
        Timestamp timestamp = conexion.obtener_timestamp();

        int IdEstatusAbrir = conexion.obtener_valor_entero("SELECT Id from catalogo_estatus WHERE Abrir = 'true'");
        
        conexion.ejecutar_update("INSERT INTO dbo.concentrado_folios_incidencias "+
                                    "(IdUnidad,IdArea,UsuarioReporta,"+
                                    "TelefonoContacto,ReferenciaDocumental,"+
                                    "IdTipoIncidencia,IdBien,"+
                                    "Marca,Modelo,NumeroSerie,NumeroInventario,MotivoReporte,"+
                                    "IdPrioridad,IdUsuarioSoporteAsignado,fecha,marca) "+
                                    "VALUES "+
                                    "('"+
                                    MDFolio.getIdUnidad()+"','"+
                                    MDFolio.getIdArea()+"','"+
                                    MDFolio.getUsuarioReporta()+"','"+
                                    MDFolio.getTelefonoContacto()+"','"+
                                    MDFolio.getReferenciaDocumental()+"','"+
                                    MDFolio.getIdTipoIncidencia()+"','"+
                                    MDFolio.getIdBien()+"','"+
                                    MDFolio.getMarca()+"','"+
                                    MDFolio.getModelo()+"','"+
                                    MDFolio.getNumeroSerie()+"','"+
                                    MDFolio.getNumeroInventario()+"','"+
                                    MDFolio.getMotivoReporte()+"','"+
                                    MDFolio.getIdPrioridad()+"','"+
                                    MDFolio.getIdUsuarioSoporteAsignado()+"',getDate(),'"+timestamp+"')");

        int folio = conexion.obtener_valor_entero("SELECT Folio FROM concentrado_folios_incidencias WHERE marca = '"+timestamp+"'");
        MDFolio.setFolio(folio);

        conexion.ejecutar_update("INSERT INTO dbo.estatus (Folio,IdEstatus,Fecha,Anotacion,IdUsuario) "+
                                    "VALUES "+
                                    "('"+MDFolio.getFolio()+"','"+
                                    IdEstatusAbrir+"',"+
                                    "getDate(),'"+
                                    MDFolio.getMotivoReporte()+"','"+
                                    //"Apertura de Folio','"+
                                    MDFolio.getIdUsuarioSoporteAsignado()+"')");

        conexion.FinalizarTransaccion();

        conexion.cerrarConexion();

        MDFolio = ObtenerFolio(MDFolio.getFolio());

        return MDFolio;
    }
    public ModeloFolioIncidencia ObtenerFolio(int Folio){
        ModeloFolioIncidencia MDFolio = new ModeloFolioIncidencia();
        CONEXION conexion = CONEXION.conectar_bd();

        String[] datosFolio = conexion.obtener_vector("SELECT dbo.concentrado_folios_incidencias.IdUnidad, "+
                                                        "dbo.catalogo_unidades.Nombre, "+
                                                        "dbo.concentrado_folios_incidencias.IdArea, "+
                                                        "dbo.catalogo_areas.Nombre, "+
                                                        "dbo.concentrado_folios_incidencias.UsuarioReporta, "+

                                                        "dbo.concentrado_folios_incidencias.TelefonoContacto, "+
                                                        "dbo.concentrado_folios_incidencias.ReferenciaDocumental, "+

                                                        "dbo.concentrado_folios_incidencias.IdTipoIncidencia, "+
                                                        "dbo.catalogo_tipo_incidencias.Nombre, "+
                                                        "dbo.concentrado_folios_incidencias.IdBien, "+
                                                        "dbo.catalogo_bien.Nombre, "+
                                                        "dbo.concentrado_folios_incidencias.Marca, "+
                                                        "dbo.concentrado_folios_incidencias.Modelo, "+
                                                        "dbo.concentrado_folios_incidencias.NumeroSerie, "+
                                                        "dbo.concentrado_folios_incidencias.NumeroInventario, "+
                                                        "dbo.concentrado_folios_incidencias.MotivoReporte, "+
                                                        "dbo.concentrado_folios_incidencias.IdPrioridad, "+
                                                        "dbo.catalogo_prioridad.Nombre, "+
                                                        "dbo.concentrado_folios_incidencias.IdUsuarioSoporteAsignado, "+
                                                        "dbo.catalogo_usuarios_soporte.NombrePropio, "+
                                                        "dbo.concentrado_folios_incidencias.fecha, "+
                                                        "CASE concentrado_folios_incidencias.Activo WHEN 0 THEN 'false' ELSE 'true' END AS Activo, "+
                                                        "dbo.concentrado_folios_incidencias.IdTipoIncidenciaFinal "+
                                                        "FROM "+
                                                        "dbo.concentrado_folios_incidencias, "+
                                                        "dbo.catalogo_unidades, "+
                                                        "dbo.catalogo_areas, "+
                                                        "dbo.catalogo_tipo_incidencias, "+
                                                        "dbo.catalogo_bien, "+
                                                        "dbo.catalogo_prioridad, "+
                                                        "dbo.catalogo_usuarios_soporte "+
                                                        "WHERE Folio = '"+Folio+"' "+
                                                        "AND dbo.concentrado_folios_incidencias.IdUnidad = dbo.catalogo_unidades.Id "+
                                                        "AND dbo.concentrado_folios_incidencias.IdArea = dbo.catalogo_areas.Id "+
                                                        "AND dbo.concentrado_folios_incidencias.IdTipoIncidencia = dbo.catalogo_tipo_incidencias.Id "+
                                                        "AND dbo.concentrado_folios_incidencias.IdBien = dbo.catalogo_bien.Id "+
                                                        "AND dbo.concentrado_folios_incidencias.IdPrioridad = dbo.catalogo_prioridad.Id "+
                                                        "AND dbo.concentrado_folios_incidencias.IdUsuarioSoporteAsignado = dbo.catalogo_usuarios_soporte.Id");

        if(datosFolio.length > 0){
            MDFolio.setFolio(Folio);
            MDFolio.setIdUnidad(Integer.parseInt(datosFolio[0]));
            MDFolio.setUnidad(datosFolio[1]);
            MDFolio.setIdArea(Integer.parseInt(datosFolio[2]));
            MDFolio.setArea(datosFolio[3]);
            MDFolio.setUsuarioReporta(datosFolio[4]);

            MDFolio.setTelefonoContacto(datosFolio[5]);
            MDFolio.setReferenciaDocumental(datosFolio[6]);

            MDFolio.setIdTipoIncidencia(Integer.parseInt(datosFolio[7]));
            MDFolio.setIncidencia(datosFolio[8]);
            MDFolio.setIdBien(Integer.parseInt(datosFolio[9]));
            MDFolio.setBien(datosFolio[10]);
            MDFolio.setMarca(datosFolio[11]);
            MDFolio.setModelo(datosFolio[12]);
            MDFolio.setNumeroSerie(datosFolio[13]);
            MDFolio.setNumeroInventario(datosFolio[14]);
            MDFolio.setMotivoReporte(datosFolio[15]);
            MDFolio.setIdPrioridad(Integer.parseInt(datosFolio[16]));
            MDFolio.setPrioridad(datosFolio[17]);
            MDFolio.setIdUsuarioSoporteAsignado(Integer.parseInt(datosFolio[18]));
            MDFolio.setUsuarioSoporteAsignado(datosFolio[19]);
            Timestamp dateTS = Timestamp.valueOf(datosFolio[20]);
            //java.sql.Date fechaDate = java.sql.Date.valueOf(datosFolio[19]);
            MDFolio.setFecha(new java.util.Date(dateTS.getTime()));            
            MDFolio.setActivo(Boolean.parseBoolean(datosFolio[21]));            
            MDFolio.setIdTipoIncidenciaFinal(Integer.parseInt(datosFolio[22]));
            String[][] datosEstatus = ObtenerEstatus(Folio);

            MDFolio.setDatosEstatus(datosEstatus);            
        }
        
        conexion.cerrarConexion();
        return MDFolio;
    }
    public String[][] AgregarEstatus(int Folio,int IdEstatus,int IdIncidenciaFinal,boolean Activo,int IdUsuario,String Anotacion,int IdUsuarioSoporteAsignado){
        CONEXION conexion = CONEXION.conectar_bd();
        
        conexion.ejecutar_update("INSERT INTO dbo.estatus (Folio,IdEstatus,Fecha,Anotacion,IdUsuario) "+
                                    "VALUES "+
                                    "('"+Folio+"','"+
                                    IdEstatus+"',"+                                    
                                    "getDate(),'"+
                                    Anotacion+"','"+
                                    IdUsuario+"')");

        if(IdUsuarioSoporteAsignado > 0){
            conexion.ejecutar_update("UPDATE dbo.concentrado_folios_incidencias "+
                                        "SET IdUsuarioSoporteAsignado = '"+IdUsuarioSoporteAsignado+"' "+                                        
                                        "WHERE Folio = '"+Folio+"'");
        }

        conexion.ejecutar_update("UPDATE dbo.concentrado_folios_incidencias "+
                                        "SET IdTipoIncidenciaFinal = '"+IdIncidenciaFinal+"' "+
                                        "WHERE Folio = '"+Folio+"'");

        if(Activo == false){
            conexion.ejecutar_update("UPDATE dbo.concentrado_folios_incidencias "+
                                        "SET Activo = 'false' "+
                                        "WHERE Folio = '"+Folio+"'");
        }        
        
        
        conexion.cerrarConexion();

        String[][] datosEstatus = ObtenerEstatus(Folio);

        return datosEstatus;
    }
    public String[][] ObtenerEstatus(int Folio){
        CONEXION conexion = CONEXION.conectar_bd();
        
        String[][] datosEstatus = conexion.ObtenerArregloString("SELECT catalogo_estatus.Nombre AS Estatus,"+
                                                                "Anotacion, "+
                                                                "Fecha, "+
                                                                "catalogo_usuarios_soporte.NombrePropio "+
                                                                "FROM estatus FULL OUTER JOIN catalogo_estatus "+
                                                                "ON estatus.IdEstatus = catalogo_estatus.Id,catalogo_usuarios_soporte "+
                                                                "WHERE Folio = '"+Folio+"' "+
                                                                "AND dbo.estatus.IdUsuario = dbo.catalogo_usuarios_soporte.Id "+
                                                                "ORDER BY Fecha");
        
        conexion.cerrarConexion();
         
        return datosEstatus;
    }
    public String[][] ListarFolios(int IdUsuarioSoporteAsignado,boolean Administrador){
        //System.out.println(IdUsuarioSoporteAsignado);
        String[][] datos;
         CONEXION conexion = CONEXION.conectar_bd();
         
         String sentencia = "SELECT concentrado_folios_incidencias.Folio, "+
                            "CASE concentrado_folios_incidencias.Activo WHEN 0 THEN 'false' ELSE 'true' END AS Activo, "+
                            "catalogo_unidades.Nombre AS Unidad, "+
                            "catalogo_areas.Nombre AS Area, "+
                            "UsuarioReporta, "+
                            "catalogo_tipo_incidencias.Nombre AS Incidencia, "+
                            "catalogo_bien.Nombre As Bien, "+
                            "Marca, "+
                            "Modelo, "+
                            "NumeroSerie, "+
                            "NumeroInventario, "+
                            "MotivoReporte, "+
                            "catalogo_prioridad.Nombre, "+
                            "tabla.Anotacion, "+
                            "catalogo_usuarios_soporte.NombrePropio AS UsuarioAsignado, "+
                            "fecha,tabla.Estatus "+
                            "FROM concentrado_folios_incidencias,catalogo_unidades,catalogo_areas,catalogo_tipo_incidencias, "+
                            "catalogo_bien,catalogo_prioridad,catalogo_usuarios_soporte, "+
                            "(SELECT estatus.Folio,catalogo_estatus.Nombre as Estatus,Anotacion "+
                            "FROM estatus,catalogo_estatus "+
                            "WHERE estatus.Id IN(SELECT MAX(estatus.Id) FROM estatus GROUP BY Folio) "+
                            "AND IdEstatus = catalogo_estatus.Id) as tabla ";
         
         if(Administrador){
             sentencia = sentencia + "WHERE concentrado_folios_incidencias.IdUsuarioSoporteAsignado IN( "+
                                    "SELECT IdUsuario FROM relacion_usuario_areasoporte "+
                                    "WHERE IdAreaSoporte IN (SELECT IdAreaSoporte FROM relacion_usuario_areasoporte " +
                                    "WHERE IdUsuario = "+IdUsuarioSoporteAsignado+" GROUP BY IdAreaSoporte) "+
                                    "GROUP BY IdUsuario) ";
         }
         else{
            sentencia = sentencia + "WHERE concentrado_folios_incidencias.IdUsuarioSoporteAsignado = '"+IdUsuarioSoporteAsignado+"' ";
         }

         sentencia = sentencia + "AND concentrado_folios_incidencias.IdUnidad = catalogo_unidades.Id "+
                                    "AND concentrado_folios_incidencias.IdArea = catalogo_areas.Id "+
                                    "AND concentrado_folios_incidencias.IdTipoIncidencia = catalogo_tipo_incidencias.Id "+
                                    "AND concentrado_folios_incidencias.IdBien = catalogo_bien.Id "+
                                    "AND concentrado_folios_incidencias.IdPrioridad = catalogo_prioridad.Id "+
                                    "AND concentrado_folios_incidencias.IdUsuarioSoporteAsignado = catalogo_usuarios_soporte.Id "+
                                    "AND concentrado_folios_incidencias.Folio = tabla.Folio "+
                                    "AND concentrado_folios_incidencias.Activo = 'True' "+
                                    "ORDER BY concentrado_folios_incidencias.Folio DESC";
         
         
         datos = conexion.ObtenerArregloString(sentencia);

         conexion.cerrarConexion();
         
         return datos;
    }
    public ModeloEstadoDiagnostico EstadoDiagnostico(int Folio){
        ModeloEstadoDiagnostico modelo = new ModeloEstadoDiagnostico();
        String[][] datos;
        CONEXION conexion = CONEXION.conectar_bd();
        
        datos = conexion.ObtenerArregloString("SELECT * FROM estatus WHERE Folio = '"+Folio+"' "+
                                                "AND IdEstatus IN "+
                                                "(SELECT Id FROM catalogo_estatus WHERE DiagnostinoInicial = 'True')");
        
        if(datos.length > 0)
            modelo.setDiagnostinoInicial(true);

        datos = conexion.ObtenerArregloString("SELECT * FROM estatus WHERE Folio = '"+Folio+"' "+
                                                "AND IdEstatus IN "+
                                                "(SELECT Id FROM catalogo_estatus WHERE DiagnostinoFinal = 'True')");

        if(datos.length > 0)
            modelo.setDiagnostinoFinal(true);
        
        conexion.cerrarConexion();

        return modelo;
    }    
    public BMMDGridBusqueda[] buscar(int IdUnidad,
                                        int IdArea,
                                        String UsuarioReporta,
                                        String ReferenciaDocumental,
                                        int IdTipoIncidencia,
                                        int IdTipoBien,
                                        String Marca,
                                        String Modelo,
                                        String NumeroSerie,
                                        String NumeroInventario){
        //System.out.println("RPCMovimientosFoliosImpl:buscar()");
        //System.out.println("buscar:"+IdUnidad+":"+IdTipoIncidencia+":"+ReferenciaDocumental);
        BMMDGridBusqueda Listado[] = new BMMDGridBusqueda[0];
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "SELECT Folio," +
                        "CASE Activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS Estado," +
                        "catalogo_unidades.Nombre AS Unidad,catalogo_areas.Nombre AS Area," +
                        "catalogo_tipo_incidencias.Nombre AS TipoIncidencia,catalogo_bien.Nombre AS TipoBien," +
                        "Marca,Modelo,NumeroSerie,NumeroInventario," +
                        "UsuarioReporta,ReferenciaDocumental " +
                        "FROM concentrado_folios_incidencias " +
                        "LEFT JOIN catalogo_unidades ON concentrado_folios_incidencias.IdUnidad = catalogo_unidades.Id " +
                        "LEFT JOIN catalogo_areas ON concentrado_folios_incidencias.IdArea = catalogo_areas.Id " +
                        "LEFT JOIN catalogo_tipo_incidencias ON concentrado_folios_incidencias.IdTipoIncidencia = catalogo_tipo_incidencias.Id " +
                        "LEFT JOIN catalogo_bien ON concentrado_folios_incidencias.IdBien = catalogo_bien.Id ";
        
        if(IdUnidad != 0)
            cadena = cadena + "WHERE concentrado_folios_incidencias.IdUnidad = '"+IdUnidad+"' ";
        else
            cadena = cadena + "WHERE concentrado_folios_incidencias.IdUnidad != '0' ";
        
        if(IdArea != 0)
            cadena = cadena + "AND IdArea = '"+IdArea+"' ";
        else
            cadena = cadena + "AND IdArea != '0' ";
        
        if(UsuarioReporta.length() > 0)
            cadena = cadena + "AND UsuarioReporta = '"+UsuarioReporta+"' ";
        else
            cadena = cadena + "AND (UsuarioReporta != '' OR UsuarioReporta != 'NULL') ";
        
        if(ReferenciaDocumental.length() > 0)
            cadena = cadena + "AND ReferenciaDocumental LIKE '%"+ReferenciaDocumental+"%' ";
        else
            cadena = cadena + "AND (ReferenciaDocumental != '' OR ReferenciaDocumental != 'NULL') ";
        
        if(IdTipoIncidencia != 0)
            cadena = cadena + "AND concentrado_folios_incidencias.IdTipoIncidencia = '"+IdTipoIncidencia+"' ";
        else
            cadena = cadena + "AND concentrado_folios_incidencias.IdTipoIncidencia != '0' ";
        
        if(IdTipoBien != 0)
            cadena = cadena + "AND IdBien = '"+IdTipoBien+"' ";
        else
            cadena = cadena + "AND IdBien != '0' ";
        
        if(Marca.length() > 0)
            cadena = cadena + "AND Marca = '"+Marca+"' ";
        else
            cadena = cadena + "AND (Marca != '' OR Marca != 'NULL') ";
        
        if(Modelo.length() > 0)
            cadena = cadena + "AND Modelo = '"+Modelo+"' ";
        else
            cadena = cadena + "AND (Modelo != '' OR Modelo != 'NULL') ";
        
        if(NumeroSerie.length() > 0)
            cadena = cadena + "AND NumeroSerie LIKE '%"+NumeroSerie+"%' ";
        else
            cadena = cadena + "AND (NumeroSerie != '' OR NumeroSerie != 'NULL') ";
        
        if(NumeroInventario.length() > 0)
            cadena = cadena + "AND NumeroInventario LIKE '%"+NumeroInventario+"%'";
        else
            cadena = cadena + "AND (NumeroInventario != '' OR NumeroInventario != 'NULL') ";
        
        cadena = cadena +  "ORDER BY Activo," +
                            "catalogo_unidades.Nombre,catalogo_areas.Nombre," +
                            "catalogo_tipo_incidencias.Nombre,catalogo_bien.Nombre," +
                            "Marca,Modelo,NumeroSerie,NumeroInventario," +
                            "Folio," +
                            "UsuarioReporta,ReferenciaDocumental ASC";
        
        //System.out.println("RPCMovimientosFoliosImpl:buscar():cadena:"+cadena);        
        try {
            resultset = sentencia.executeQuery(cadena);
            int cantidadRegistros = conexion.obtenerCantidadRegistros(resultset);
            Listado = new BMMDGridBusqueda[cantidadRegistros];
            
            int posy = 0;
            while(resultset.next()){
                Listado[posy] = new BMMDGridBusqueda(
                        resultset.getInt("Folio"),
                        resultset.getString("Unidad"),resultset.getString("Area"),
                        
                        resultset.getString("UsuarioReporta"),resultset.getString("ReferenciaDocumental"),
                        
                        resultset.getString("TipoIncidencia"),resultset.getString("TipoBien"),
                        
                        resultset.getString("Marca"),resultset.getString("Modelo"),
                        resultset.getString("NumeroSerie"),resultset.getString("NumeroInventario"),
                        resultset.getString("Estado")
                );
                //System.out.println("posy:"+posy+":"+Listado[posy].get("Folio"));
                
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCMarcaImpl:getListado2()");
            Listado = new BMMDGridBusqueda[0];
        }

        conexion.cerrarConexion();       
        //System.out.println("RPCMovimientosFoliosImpl:buscar():FIN");
        
        return Listado;
    }
    public BMMDConfiguracion configuracion(){
        BMMDConfiguracion configuracion = new BMMDConfiguracion();
        
        CONEXION conexion = CONEXION.conectar_bd();
        
        Statement sentencia = conexion.sentencia;
        ResultSet resultset = conexion.resultado;
        
        String cadena = "SELECT diasMinimoAmarillo,diasMaximoAmarillo,etiquetaTipoBien "+
                        "FROM Configuracion";
        
        try {
            resultset = sentencia.executeQuery(cadena);
            
            int posy = 0;
            while(resultset.next()){
                configuracion = new BMMDConfiguracion(
                        resultset.getInt("diasMinimoAmarillo"),
                        resultset.getInt("diasMaximoAmarillo"),
                        resultset.getString("etiquetaTipoBien"));
                //System.out.println("posy:"+posy+":"+Listado[posy].get("Folio"));                
                posy++;
            }
        } catch (SQLException ex) {
            conexion.on_error(ex,cadena,"RPCMarcaImpl:getListado2()");
            configuracion = new BMMDConfiguracion();
        }
        
        
        return configuracion;
    }
}
