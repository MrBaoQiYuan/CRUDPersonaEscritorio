
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

            // Paso 2: Establecer la conexi�n
            Connection conexion = DriverManager.getConnection(url, usuario, pass);

            // Paso 3: Crear una declaraci�n
            Statement declaracion = conexion.createStatement();

            // Paso 4: Crear la tabla
            //PersonaDAO.crearTablaPersonas();
            //PersonaDAO.insertarPersona("34637", "Luisooooo    ", 12);
            //PersonaDAO.consultarPersonas();
            //PersonaDAO.eliminarPersona("34637");
            //PersonaDAO.editarPersona("34637", url, 0);
            //String consulta = "CREATE TABLE NuevaTabla (id INT AUTO_INCREMENT, nombre VARCHAR(50), PRIMARY KEY (id))";
            //declaraci�n.executeUpdate(consulta);
            //System.out.println("Tabla creada correctamente");

            // Paso 5: Cerrar la declaraci�n y la conexi�n
            //declaracion.close();
            //conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
