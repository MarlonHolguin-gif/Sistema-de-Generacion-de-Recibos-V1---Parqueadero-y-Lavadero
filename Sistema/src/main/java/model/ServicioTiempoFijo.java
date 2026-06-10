package model;

public class ServicioTiempoFijo extends Servicio {

    private String tipo;

    public ServicioTiempoFijo(String tipo) {
        this.tipo = tipo;
        this.nombre = tipo;
    }

    @Override
    public double calcularPrecio(long horas, Vehiculo vehiculo) {

        double precio = 0;

        switch (tipo) {
            case "Semana":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 60000; break;
                    case MOTO: precio = 30000; break;
                    case CAMIONETA: precio = 80000; break;
                    case TURBO: precio = 140000; break;
                    case VOLQUETA: precio = 150000; break;
                }
                break;

            case "Quincena":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 100000; break;
                    case MOTO: precio = 50000; break;
                    case CAMIONETA: precio = 130000; break;
                    case TURBO: precio = 140000; break;
                    case VOLQUETA: precio = 150000; break;
                }
                break;

            case "Mensualidad":
                switch (vehiculo.getTipo()) {
                    case CARRO: precio = 180000; break;
                    case MOTO: precio = 90000; break;
                    case CAMIONETA: precio = 220000; break;
                    case TURBO: precio = 140000; break;
                    case VOLQUETA: precio = 150000; break;
                }
                break;
        }

        return precio;
    }
}