/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.Timestamp;
import org.helpdesk.client.ModeloDatos.ModeloAreasSoporte_Asignadas_NoAsignadas;
import org.helpdesk.client.ModeloDatos.ModeloDatosUsuario;
import org.helpdesk.client.ServiciosRPC.RPCUsuarioSoporte;

/**
 *
 * @author Administrador
 */
public class RPCUsuarioSoporteImpl extends RemoteServiceServlet implements RPCUsuarioSoporte {
    public String[] getDatos(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[][] getListado(String valor) {
        CONEXION conexion = CONEXION.conectar_bd();

        String obj[][] = new String[0][0];

        if(valor == null)
            valor = new String();

        if(valor.length() > 0){
            obj = conexion.ObtenerArregloString("SELECT "+
                                                "Id,"+
                                                "NombrePropio,Correo,"+
                                                "NombreUsuario,"+
                                                "CASE EsAdministrador WHEN 0 THEN 'false' ELSE 'true' END  AS Administrador, "+
                                                "CASE EsReseteadoPassword WHEN 0 THEN 'false' ELSE 'true' END AS ResetPassword "+
                                                "FROM dbo.catalogo_usuarios_soporte "+
                                                "WHERE NombrePropio like '%"+valor+"%' "+                    
                                                "ORDER BY NombrePropio,Id");
        }
        else{
            obj = conexion.ObtenerArregloString("SELECT "+
                                                "Id,"+
                                                "NombrePropio,Correo,"+
                                                "NombreUsuario,"+
                                                "CASE EsAdministrador WHEN 0 THEN 'false' ELSE 'true' END  AS Administrador, "+
                                                "CASE EsReseteadoPassword WHEN 0 THEN 'false' ELSE 'true' END  AS ResetPassword "+
                                                "FROM dbo.catalogo_usuarios_soporte "+
                                                "ORDER BY NombrePropio,Id");
        }

        conexion.cerrarConexion();

        return obj;
    }
    public ModeloAreasSoporte_Asignadas_NoAsignadas getListadosAreasAsignadas(int IdUsuarioSoporte){
        CONEXION conexion = CONEXION.conectar_bd();
        ModeloAreasSoporte_Asignadas_NoAsignadas Areas = new ModeloAreasSoporte_Asignadas_NoAsignadas();

        Areas.setAreasSiAsignadas(getAreasAsignadas(IdUsuarioSoporte));
        Areas.setAreasNoAsignadas(getAreasNoAsignadas(IdUsuarioSoporte));
        
        conexion.cerrarConexion();

        return Areas;
    }
    public Boolean Agregar(String NombrePropio, String Correo,
                            String NombreUsuario,boolean EsAdministrador,
                            ModeloAreasSoporte_Asignadas_NoAsignadas Areas) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("INSERT INTO dbo.catalogo_usuarios_soporte (NombrePropio,Correo,"+
                                                                            "NombreUsuario,Password,"+
                                                                            "EsAdministrador,EsReseteadoPassword) VALUES ('"+
                                                                            NombrePropio+"','"+
                                                                            Correo+"','"+
                                                                            NombreUsuario+"',"+
                                                                            "HashBytes('MD5','"+NombreUsuario+"'),'"+
                                                                            EsAdministrador+"','true')");

        int IdUsuario = conexion.obtener_valor_entero("SELECT Id FROM dbo.catalogo_usuarios_soporte "+
                                                        "WHERE NombrePropio = '"+NombrePropio+"' "+
                                                        "AND Correo = '"+Correo+"' "+
                                                        "AND NombreUsuario = '"+NombreUsuario+"' "+
                                                        "AND EsAdministrador = '"+EsAdministrador+"'");
        
        String[][] datos = Areas.getAreasSiAsignadas();
        
        int size = datos.length;
        //GWT.log("size:"+size);
        int posy = 0;
        while(posy < size){
            conexion.ejecutar_update("INSERT INTO dbo.relacion_usuario_areasoporte (IdUsuario,IdAreaSoporte) "+
                                        "VALUES ('"+IdUsuario+"','"+datos[posy][0]+"')");
            //System.out.println("IdUsuario:"+IdUsuario+"datos[posy][0]:"+datos[posy][0]);
            posy++;
        }

        estado = true;
        
        conexion.cerrarConexion();

        return estado;
    }
    public Boolean Editar(int Id,String NombrePropio, String Correo,
                            String NombreUsuario,boolean EsAdministrador,boolean EsReseteadoPassword,
                            ModeloAreasSoporte_Asignadas_NoAsignadas Areas) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("UPDATE dbo.catalogo_usuarios_soporte "+
                                    "SET NombrePropio = '"+NombrePropio+"', "+
                                    "Correo = '"+Correo+"', "+
                                    "NombreUsuario = '"+NombreUsuario+"', "+
                                    "EsAdministrador = '"+EsAdministrador+"', "+
                                    "EsReseteadoPassword = '"+EsReseteadoPassword+"' "+
                                    "WHERE Id = '"+Id+"'");

        if(EsReseteadoPassword == true){
            conexion.ejecutar_update("UPDATE dbo.catalogo_usuarios_soporte "+
                                    "SET Password = HashBytes('MD5','"+NombreUsuario+"') "+
                                    "WHERE Id = '"+Id+"'");
        }

        conexion.ejecutar_update("DELETE FROM dbo.relacion_usuario_areasoporte WHERE IdUsuario = '"+Id+"'");

        String[][] datos = Areas.getAreasSiAsignadas();

        int size = datos.length;
        //GWT.log("size:"+size);
        int posy = 0;
        while(posy < size){
            conexion.ejecutar_update("INSERT INTO dbo.relacion_usuario_areasoporte (IdUsuario,IdAreaSoporte) "+
                                        "VALUES ('"+Id+"','"+datos[posy][0]+"')");
            //System.out.println("IdUsuario:"+IdUsuario+"datos[posy][0]:"+datos[posy][0]);
            posy++;
        }

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public Boolean Elimiar(int Id) {
        CONEXION conexion = CONEXION.conectar_bd();

        Boolean estado = false;

        conexion.ejecutar_update("DELETE FROM dbo.catalogo_usuarios_soporte "+
                                    "WHERE Id = '"+Id+"'");

        conexion.cerrarConexion();

        estado = true;

        return estado;
    }
    public void EditarPassword(ModeloDatosUsuario MDUsuario){
        CONEXION conexion = CONEXION.conectar_bd();
        
        conexion.ejecutar_update("UPDATE dbo.catalogo_usuarios_soporte "+
                                    "SET EsReseteadoPassword = 'false', "+
                                    "Password = HashBytes('MD5','"+MDUsuario.getPasword()+"') "+
                                    "WHERE Id = '"+MDUsuario.getId()+"'");
        
        conexion.cerrarConexion();
        
        conexion.cerrarConexion();
    }
    public ModeloDatosUsuario verificaLogin(ModeloDatosUsuario MDUsuario){
        boolean ExisteUsuario = false;
        boolean EsContraseñaCorrecta = false;
        CONEXION conexion = CONEXION.conectar_bd();     
        String sentencia = "SELECT Id,NombrePropio,"+
                            "CASE EsAdministrador WHEN 0 THEN 'false' ELSE 'true' END, "+
                            "CASE EsReseteadoPassword WHEN 0 THEN 'false' ELSE 'true' END, "+
                            "getDate() AS Fecha "+
                            "FROM catalogo_usuarios_soporte "+
                            "WHERE NombreUsuario = '"+MDUsuario.getNombreUsuario()+"' "+
                            "AND Password = HashBytes('MD5','"+MDUsuario.getPasword()+"')";
        
        //System.out.println("sentencia: "+sentencia);
        
        String vector[] = conexion.obtener_vector(sentencia);
        
        if(vector.length > 0){            
            MDUsuario.setId(Integer.parseInt(vector[0].toString()));
            MDUsuario.setNombrePropio(vector[1].toString());
            MDUsuario.setAdministrador(Boolean.parseBoolean(vector[2].toString()));
            MDUsuario.setReseteadoPassword(Boolean.parseBoolean(vector[3].toString()));
            MDUsuario.setAreas(getAreasAsignadas(Integer.parseInt(vector[0].toString())), getAreasNoAsignadas(Integer.parseInt(vector[0].toString())));
            Timestamp dateTS = Timestamp.valueOf(vector[4]);
            MDUsuario.setFechaLogin(new java.util.Date(dateTS.getTime()));
        }
        else
            MDUsuario = new ModeloDatosUsuario();
        
        conexion.cerrarConexion();
        
        return MDUsuario;
    }
    public String[][] getAreasAsignadas(int IdUsuarioSoporte){
        CONEXION conexion = CONEXION.conectar_bd();
        
        String[][] datos;
        
        datos = conexion.ObtenerArregloString("SELECT dbo.catalogo_area_soporte.Id,dbo.catalogo_area_soporte.Nombre "+
                                                "FROM dbo.relacion_usuario_areasoporte,dbo.catalogo_area_soporte "+
                                                "WHERE dbo.relacion_usuario_areasoporte.IdUsuario = '"+IdUsuarioSoporte+"' "+
                                                "AND dbo.relacion_usuario_areasoporte.IdAreaSoporte = dbo.catalogo_area_soporte.Id "+
                                                "ORDER BY dbo.relacion_usuario_areasoporte.IdAreaSoporte");
        
        conexion.cerrarConexion();
        
        return datos;
    }
    public String[][] getAreasNoAsignadas(int IdUsuarioSoporte){
        CONEXION conexion = CONEXION.conectar_bd();

        String[][] datos;

        datos = conexion.ObtenerArregloString("SELECT dbo.catalogo_area_soporte.Id, "+
                                                "dbo.catalogo_area_soporte.Nombre "+
                                                "FROM dbo.catalogo_area_soporte "+
                                                "WHERE dbo.catalogo_area_soporte.Id NOT IN "+
                                                "(SELECT IdAreaSoporte FROM dbo.relacion_usuario_areasoporte "+
                                                "WHERE IdUsuario = '"+IdUsuarioSoporte+"')");

        conexion.cerrarConexion();

        return datos;
    }
}
