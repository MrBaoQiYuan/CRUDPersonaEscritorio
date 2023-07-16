package intentobasedatos2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import intentobasedatos2.model.Validador;
import javax.swing.JOptionPane;

public class PersonaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/base_datos_personas";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static void insertarPersona(String nombre, int edad) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaracion = conexion.prepareStatement(
                "INSERT INTO personas (id_persona, nombre, edad) VALUES (?, ?, ?)")) {
            int siguienteId = obtenerSiguienteIdPersona();
            declaracion.setInt(1, siguienteId);
            declaracion.setString(2, nombre);
            declaracion.setInt(3, edad);

            declaracion.executeUpdate();

            System.out.println("Persona insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean modificarPersona(int idPersona, String nuevoNombrePersona, int nuevaEdadPersona) {
        boolean personaIdActualizar = false;
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexion.prepareStatement("UPDATE personas SET nombre = ?, edad = ? WHERE id_persona = ?")) {

            declaraci�n.setString(1, nuevoNombrePersona);
            declaraci�n.setInt(2, nuevaEdadPersona);
            declaraci�n.setInt(3, idPersona);

            int filasActualizadas = declaraci�n.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            personaIdActualizar = false;
        }
        return personaIdActualizar;
    }

    public static boolean eliminarPersona(int idPersona) {
        boolean personaEliminada = false;
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexion.prepareStatement("DELETE FROM personas WHERE id_persona = ?")) {
            declaraci�n.setInt(1, idPersona);
            int filasEliminadas = declaraci�n.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Persona eliminada correctamente.");
                personaEliminada = true;
            } else {
                System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personaEliminada;
    }

    public static boolean eliminarPersonaPorNombre(String nombrePersona) {
        boolean eliminacionExitosa = false;
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexion.prepareStatement(
                "DELETE FROM personas WHERE nombre = ?")) {

            declaraci�n.setString(1, nombrePersona);

            int filasEliminadas = declaraci�n.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Persona eliminada correctamente.");
                eliminacionExitosa = true;

            } else {
                System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminacionExitosa;
    }

    public static boolean eliminarTodasLasPersonas() {
        boolean personasEliminadas = false;
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); Statement declaraci�n = conexion.createStatement()) {
            int filasEliminadas = declaraci�n.executeUpdate("DELETE FROM personas");
            if (filasEliminadas > 0) {
                personasEliminadas = true;
            } else {
                personasEliminadas = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personasEliminadas;
    }

    public static List<String> consultarPersonas() {
        List<String> listaPersonas = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); Statement declaraci�n = conexion.createStatement(); ResultSet resultados = declaraci�n.executeQuery("SELECT * FROM personas")) {

            while (resultados.next()) {
                String idPersona = resultados.getString("id_persona");
                String nombre = resultados.getString("nombre");
                int edad = resultados.getInt("edad");

                String persona = "ID: " + idPersona + ", Nombre: " + nombre + ", Edad: " + edad;
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPersonas;
    }

    public static String consultarPersonaPorId(int idPersona) {
        String informacionPersona = null;
        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexi�n.prepareStatement(
                "SELECT * FROM personas WHERE id_persona = ?")) {

            declaraci�n.setInt(1, idPersona);
            ResultSet resultados = declaraci�n.executeQuery();

            if (resultados.next()) {
                String nombre = resultados.getString("nombre");
                int edad = resultados.getInt("edad");
                informacionPersona = "ID: " + idPersona + ", Nombre: " + nombre + ", Edad: " + edad;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Introduzca un id correcto.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return informacionPersona;
    }

    public static int obtenerSiguienteIdPersona() {
        int siguienteId = 0;

        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD); Statement declaraci�n = conexi�n.createStatement()) {

            String consulta = "SELECT MAX(id_persona) FROM personas";
            ResultSet resultado = declaraci�n.executeQuery(consulta);

            if (resultado.next()) {
                siguienteId = resultado.getInt(1) + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siguienteId;
    }

    public static List<String> consultarPersonaPorNombre(String nombrePersonaConsultar) {
        List<String> informacionPersonas = new ArrayList<>();
        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexi�n.prepareStatement(
                "SELECT * FROM personas WHERE nombre = ?")) {

            declaraci�n.setString(1, nombrePersonaConsultar);
            ResultSet resultados = declaraci�n.executeQuery();

            while (resultados.next()) {
                String nombre = resultados.getString("nombre");
                int edad = resultados.getInt("edad");
                String informacionPersona = "Nombre: " + nombre + ", Edad: " + edad;
                informacionPersonas.add(informacionPersona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informacionPersonas;
    }
    
    
        public static List<String> consultarSoloNombres() {
        List<String> nombres = new ArrayList<>();
        
        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement statement = conexi�n.prepareStatement("SELECT nombre FROM personas");
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                nombres.add(nombre);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al consultar los nombres de las personas: " + e.getMessage());
        }
        
        return nombres;
    }


}
