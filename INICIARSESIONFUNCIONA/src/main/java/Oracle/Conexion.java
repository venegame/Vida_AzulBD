/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Oracle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
   
    private static final String url="jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER="vidaazul";
    private static final String PASSWORD ="vidaazul";
    private static final String DRIVER ="oracle.jdbc.OracleDriver";

    public static Connection getConnection(){
         Connection conexion = null;
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
        }catch (ClassNotFoundException e){
            System.out.println("Error al cargar el driver");
        }catch(SQLException e){
            System.out.println("Error de conexion");
        }
        return conexion;  
    }
    
}
