package intentobasedatos2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PersonaDAO {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static final String URL = "jdbc:mysql://localhost:3306/base_datos_personas";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    /*
    public static void crearTablaPersonas() {
        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD); Statement declaraci�n = conexi�n.createStatement()) {

            String consulta = "CREATE TABLE personas ("
                    + "id_persona INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(50), "
                    + "edad INT)";

            declaraci�n.executeUpdate(consulta);

            System.out.println("Tabla 'personas' creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     */
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

    public static void modificarPersona(int idPersona) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement consulta = conexion.prepareStatement("SELECT nombre, edad FROM personas WHERE id_persona = ?"); PreparedStatement declaraci�n = conexion.prepareStatement("UPDATE personas SET nombre = ?, edad = ? WHERE id_persona = ?")) {

            consulta.setInt(1, idPersona);
            ResultSet resultado = consulta.executeQuery();

            if (resultado.next()) {
                String nombreActual = resultado.getString("nombre");
                int edadActual = resultado.getInt("edad");

                System.out.println("Seleccione el campo que desea modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Edad");
                System.out.println("3. Nombre y Edad");
                System.out.print("Opci�n: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nuevo nombre (deje vac�o para mantener el valor actual '" + nombreActual + "'): ");
                        String nuevoNombre = scanner.next();

                        if (nuevoNombre.isEmpty()) {
                            nuevoNombre = nombreActual;
                        }

                        declaraci�n.setString(1, nuevoNombre);
                        declaraci�n.setInt(2, edadActual);
                        break;
                    case 2:
                        System.out.print("Ingrese la nueva edad (ingrese 0 para mantener el valor actual " + edadActual + "): ");
                        int nuevaEdad = scanner.nextInt();

                        if (nuevaEdad == 0) {
                            nuevaEdad = edadActual;
                        }

                        declaraci�n.setString(1, nombreActual);
                        declaraci�n.setInt(2, nuevaEdad);
                        break;
                    case 3:
                        System.out.print("Ingrese el nuevo nombre (deje vac�o para mantener el valor actual '" + nombreActual + "'): ");
                        nuevoNombre = scanner.next();
                        System.out.print("Ingrese la nueva edad (ingrese 0 para mantener el valor actual " + edadActual + "): ");
                        nuevaEdad = scanner.nextInt();

                        if (nuevoNombre.isEmpty()) {
                            nuevoNombre = nombreActual;
                        }

                        if (nuevaEdad == 0) {
                            nuevaEdad = edadActual;
                        }

                        declaraci�n.setString(1, nuevoNombre);
                        declaraci�n.setInt(2, nuevaEdad);
                        break;
                    default:
                        System.out.println("Opci�n inv�lida.");
                        return;
                }

                declaraci�n.setInt(3, idPersona);

                int filasActualizadas = declaraci�n.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Persona modificada correctamente.");
                } else {
                    System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
                }
            } else {
                System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPersona(int idPersona) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexion.prepareStatement(
                "DELETE FROM personas WHERE id_persona = ?")) {

            declaraci�n.setInt(1, idPersona);

            int filasEliminadas = declaraci�n.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Persona eliminada correctamente.");
            } else {
                System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarPersonas() {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD); Statement declaraci�n = conexion.createStatement(); ResultSet resultados = declaraci�n.executeQuery("SELECT * FROM personas")) {

            while (resultados.next()) {
                String idPersona = resultados.getString("id_persona");
                String nombre = resultados.getString("nombre");
                int edad = resultados.getInt("edad");

                System.out.println("ID: " + idPersona + ", Nombre: " + nombre + ", Edad: " + edad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarPersonaPorId(int idPersona) {
        try (Connection conexi�n = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement declaraci�n = conexi�n.prepareStatement(
                "SELECT * FROM personas WHERE id_persona = ?")) {

            declaraci�n.setInt(1, idPersona);
            ResultSet resultados = declaraci�n.executeQuery();

            if (resultados.next()) {
                String nombre = resultados.getString("nombre");
                int edad = resultados.getInt("edad");

                System.out.println("ID: " + idPersona + ", Nombre: " + nombre + ", Edad: " + edad);
            } else {
                System.out.println("No se encontr� ninguna persona con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

}
