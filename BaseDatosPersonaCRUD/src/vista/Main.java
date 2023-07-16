package vista;

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
    }
}
