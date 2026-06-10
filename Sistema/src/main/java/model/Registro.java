package model;

import java.util.HashMap;
import java.util.Map;

public class Registro {

    private static Map<String, Recibo> activos = new HashMap<>();

    public static void registrarEntrada(String placa, Recibo recibo) {
        activos.put(placa, recibo);
    }

    public static Recibo obtener(String placa) {
        return activos.get(placa);
    }

    public static void eliminar(String placa) {
        activos.remove(placa);
    }
}