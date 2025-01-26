package Framework;

public class PoliSaludException extends Exception {

    public PoliSaludException(String e, String clase, String metodo) {
        System.out.println("[ERROR EN PoliSalud para el LOG] " + clase + "." + metodo + " : " + e);
    }

    @Override
    public String getMessage() {
        return "Error en PoliSalud";
    }
}
