/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.helpdesk.client.ModeloDatosNuevo;

/**
 *
 * @author Administrador
 */
public class BMMDGridBusqueda extends BaseModelBasico {
    public BMMDGridBusqueda() {
        super();
        setInt("Folio",0);
        
        setCadena("Unidad","");        
        setCadena("Area","");
        
        setCadena("UsuarioReporta","");        
        setCadena("ReferenciaDocumental","");
        
        setCadena("TipoIncidencia","");        
        setCadena("TipoBien","");
        
        setCadena("Marca","");        
        setCadena("Modelo","");        
        setCadena("NumeroSerie","");        
        setCadena("NumeroInventario","");
        
        setCadena("Estado","");
    }    
    public BMMDGridBusqueda(int Folio,
            
                            String Unidad,String Area,
                            
                            String UsuarioReporta,String ReferenciaDocumental,
                            
                            String TipoIncidencia,String TipoBien,
                            
                            String Marca,
                            String Modelo,
                            String NumeroSerie,
                            String NumeroInventario,
                            
                            String Estado
    ){
        setInt("Folio",Folio);
        
        setCadena("Unidad",Unidad);        
        setCadena("Area",Area);
        
        setCadena("UsuarioReporta",UsuarioReporta);
        setCadena("ReferenciaDocumental",ReferenciaDocumental);
        
        setCadena("TipoIncidencia",TipoIncidencia);        
        setCadena("TipoBien",TipoBien);
        
        setCadena("Marca",Marca);
        setCadena("Modelo",Modelo);
        setCadena("NumeroSerie",NumeroSerie);
        setCadena("NumeroInventario",NumeroInventario);
        
        setCadena("Estado",Estado);
    }    
}
