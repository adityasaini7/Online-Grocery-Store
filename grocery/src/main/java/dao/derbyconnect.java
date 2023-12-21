package dao;
import java.sql.*;


public class derbyconnect {
    public static Connection connection;
    
    private static final String jdbcUrl="jdbc:derby:C:\\Users\\HP\\MyDB;create=true";
    
    public static Connection getConnection() {
    
        try {
            //Registering with derby Driver
        	
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (Exception e) {

            e.printStackTrace();
        }
        
        return connection;
        
    }
    
   

}
