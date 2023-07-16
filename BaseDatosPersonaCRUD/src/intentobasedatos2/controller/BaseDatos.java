
package intentobasedatos2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
    String url = "jdbc:mysql://localhost:3306/base_datos_personas";
    String usuario = "root";
    String pass = "";
    
    public void conectarBaseDatos() {
        try {
            // Paso 1: Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Paso 2: Establecer la conexión
            Connection conexion = DriverManager.getConnection(url, usuario, pass);

            // Paso 3: Crear una declaración
            Statement declaracion = conexion.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
