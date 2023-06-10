package intentobasedatos2.model;

public class Validador {

    public static boolean validarEdadReal(int edad) {
        return edad > 0 && edad < 120;
    }



}
