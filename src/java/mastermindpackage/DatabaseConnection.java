/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mastermindpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dawmi
 */
public class DatabaseConnection {

    protected Connection con;

    public DatabaseConnection() throws SQLException {
        con = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String dbName = "mastermind";
        String user = "user";
        String password = "pass";

        String Url = "jdbc:mysql://192.168.33.22:3306/" + dbName + "?"
                + "enabledTLSProtocols=TLSv1.3"
                + "&autoReconnect=true"
                + "&useSSL=false";
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(Url, user, password);
            System.out.println("bien");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mal");
        }
    }
    
    public boolean openedDatabase(){
        return con != null;
    }
    
    public boolean guardarJugador(String nombre, int intentos, int puntos){
        
        boolean jugadorGuardado = false;
        
        try {
            PreparedStatement st = this.con
                    .prepareStatement("insert into mastermind values(?, ?, ?)");
            st.setString(1, nombre);
            st.setInt(2, puntos);
            st.setInt(3, intentos);
            st.executeUpdate();
            st.close();
            jugadorGuardado = true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jugadorGuardado;
    }
    
    public ArrayList<String[]> obtenerJugador(){
        
        ArrayList<String[]> datosJugador = new ArrayList<>();
        
        String consulta = "SELECT * FROM mastermind order by intentos, puntos desc";        
        try {
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            int i = 0;
            while (rs.next() && i < 10){
                String[] datos = new String[3];
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("puntos");
                datos[2] = rs.getString("intentos");
                datosJugador.add(datos);
                i++;
            }
            return datosJugador;
        } catch (SQLException e){
            return null;
        }
    }
    
}
