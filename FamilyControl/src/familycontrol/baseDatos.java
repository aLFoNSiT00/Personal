/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familycontrol;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Data
 */
public class baseDatos {
    
    public static Connection conexionBD(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conexion = DriverManager.getConnection("jdbc:mysql://root:R1pUh1uKgdoI5QwDsWtB@containers-us-west-152.railway.app:5831/railway", "root", "R1pUh1uKgdoI5QwDsWtB");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/familycontrol", "root", "");
            return conexion;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
        
        
    }
    
}
