package intentobasedatos2.model;

public class Validador {

    public static boolean validarEdadReal(int edad) {
        return edad > 0 && edad < 120;
    }

    public static boolean validarNombre(String usuario) {
        return usuario.matches("[a-zA-Z ]+");
    }

    public static boolean validarID(int id) {
        String idString = Integer.toString(id);
        return idString.matches("[0-9]+");
    }

}
