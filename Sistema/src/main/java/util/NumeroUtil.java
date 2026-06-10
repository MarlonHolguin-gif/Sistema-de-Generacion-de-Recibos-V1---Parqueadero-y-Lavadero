package util;

public class NumeroUtil {

    public static String formatearMoneda(double valor) {
        return String.format("$%,.0f", valor);
    }
}
