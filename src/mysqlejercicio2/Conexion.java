/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DAW
 */
public class Conexion {
    public Connection conectar(){
        final String ROJO = "\033[31;1m";
        final String VERDE = "\033[32m";
        final String RESET = "\033[0m";
        Connection con=null;
        
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/tienda";
            String user="root";
            String pass="";
            con = DriverManager.getConnection(url, user, pass);
            System.out.println(VERDE+"\tConexión establecida\n\n"+RESET);
        }
        catch(Exception ex){            
            System.out.println(ROJO+ex.getMessage()+RESET);
        }
        
       return con; 
    }
    
    public void desconectar(Connection con){
        
        try{
            if (con!=null) con.close();
            
        } catch(Exception ex){
            
            System.out.println("Ha sido imposible cerrar la conexion");
        }
        
    }
}
