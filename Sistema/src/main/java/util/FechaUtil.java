package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtil {

    public static String formatear(LocalDateTime fecha) {
        if (fecha == null) {
            return "Sin fecha";
        }
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fecha.format(f);
    }
}
