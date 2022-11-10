/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermindpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dawmi
 */
public class DatabaseConnection {

    protected Connection c;

    public DatabaseConnection() throws SQLException {
        String user = "user";
        String password = "pass";
        String driver = "com.mysql.cj.jdbc.Driver";
        String nombre = "nombre";
        String puntos = "puntos";
        String intentos = "intentos";
        String dbName = "mastermind";
        c = null;
        String Url = "jbdc:mysql://192.168.33.23:3306/" + dbName + "?"
                + "enabledTLSPprotocols=TLSv1.3"
                + "&autoReconnect=true"
                + "&useSSL=false";
        
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(Url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
