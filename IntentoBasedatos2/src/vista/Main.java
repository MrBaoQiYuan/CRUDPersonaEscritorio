package vista;

import intentobasedatos2.controller.PersonaDAO;
import intentobasedatos2.controller.BaseDatos;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BaseDatos bbdd = new BaseDatos();
        bbdd.conectarBaseDatos();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaCargarPersona ventana = new VentanaCargarPersona();
                ventana.setVisible(true);
            }
        });

        /*
        boolean programaOn = true;

        while (programaOn) {

            System.out.println("¿Que operacion desea realizar?"
                    + "\n1.- Crear persona"
                    + "\n2.- Actualizar persona" //Se puede modificar uno o varios datos
                    + "\n3.- Eliminar persona"
                    + "\n4.- Consultar persona" //Se puede consultar uno o varios 
                    + "\n5.- Salir del programa");

            int opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    //System.out.println("Ingrese el id de la persona");
                    //int idCrear = input.nextInt();
                    System.out.println("Ingresa el nombre de la persona");
                    String nombre = input.next();
                    System.out.println("Ingresa la edad de la persona");
                    int edad = input.nextInt();
                    PersonaDAO.insertarPersona(nombre, edad);
                    System.out.println("Persona creada correctamente");
                    break;
                
                case 2:
                    System.out.println("¿Que persona desea modificar?");
                    int idModificar = input.nextInt();
                    PersonaDAO.modificarPersona(idModificar);
                    break; 
                 
                case 3:
                    System.out.println("Ingrese el id de la persona a eliminar");
                    int idEliminar = input.nextInt();
                    PersonaDAO.eliminarPersona(idEliminar);
                    System.out.println("persona eliminada correctamente");
                    break;

                case 4:
                    boolean programaConsultaOn = true;
                    while (programaConsultaOn) {
                        System.out.println("¿Que desea consultar?"
                                + "\n1.- Todas las personas."
                                + "\n2.- Una sola persona."
                                + "\n3.- Salir de las consultas.");
                        int cantPersonas = input.nextInt();

                        switch (cantPersonas) {
                            case 1:
                                PersonaDAO.consultarPersonas();
                                break;

                            case 2:
                                System.out.println("Ingrese el id de la persona a consultar");
                                int idConsulta = input.nextInt();
                                PersonaDAO.consultarPersonaPorId(idConsulta);
                                break;
                            case 3:
                                programaConsultaOn=false;
                                System.out.println("Salimos de las consultas");
                                break;
                        }
                    }

                    break;

                case 5:
                    System.out.println("Ha salido del programa");
                    programaOn = false;

            }
        }*/
    }
}
