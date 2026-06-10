package util;

import model.TipoVehiculo;
import java.util.Map;

public class Constantes {

    // PRECIO POR HORA
    public static final Map<TipoVehiculo, Double> PRECIO_HORA = Map.of(
            TipoVehiculo.MOTO, 1500.0,
            TipoVehiculo.CARRO, 2000.0,
            TipoVehiculo.CAMIONETA, 3000.0,
            TipoVehiculo.TURBO, 4000.0,
            TipoVehiculo.VOLQUETA, 4000.0
    );

    // PRECIO FRACCIÓN (12h)
    public static final Map<TipoVehiculo, Double> PRECIO_FRACCION = Map.of(
            TipoVehiculo.MOTO, 8000.0,
            TipoVehiculo.CARRO, 12000.0,
            TipoVehiculo.CAMIONETA, 15000.0,
            TipoVehiculo.TURBO, 18000.0,
            TipoVehiculo.VOLQUETA, 18000.0
    );

    // PRECIO PLAN
    public static final Map<TipoVehiculo, Double> PRECIO_PLANES = Map.of(
            TipoVehiculo.MOTO, 50000.0,
            TipoVehiculo.CARRO, 110000.0,
            TipoVehiculo.CAMIONETA, 15000.0,
            TipoVehiculo.TURBO, 18000.0,
            TipoVehiculo.VOLQUETA, 18000.0
    );
}