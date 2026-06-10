package model;

public class ServicioLavado extends Servicio {

    private String tipo;

    public ServicioLavado(String tipo) {
        this.tipo = tipo;
        this.nombre = "Lavado " + tipo;
    }

    @Override
    public double calcularPrecio(long horas, Vehiculo vehiculo) {

        double precio = 0;

        switch (tipo) {
            case "Paquete Sencillo":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 10000; break;
                    case MOTO: precio = 5000; break;
                    case CAMIONETA: precio = 15000; break;
                    case VOLQUETA: precio = 25000; break;
                    case TURBO: precio = 20000; break;
                }
                break;

            case "Paquete General":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 20000; break;
                    case MOTO: precio = 10000; break;
                    case CAMIONETA: precio = 25000; break;
                    case VOLQUETA: precio = 40000; break;
                    case TURBO: precio = 35000; break;
                }
                break;

            case "Paquete Premium":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 30000; break;
                    case MOTO: precio = 15000; break;
                    case CAMIONETA: precio = 35000; break;
                    case VOLQUETA: precio = 60000; break;
                    case TURBO: precio = 50000; break;
                }
                break;
        }

        return precio;
    }
}