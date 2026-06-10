package model;

public class ServicioHoras extends Servicio {

    public ServicioHoras() {
        this.nombre = "Servicio por Horas";
    }

    @Override
    public double calcularPrecio(long horas, Vehiculo vehiculo) {

        double precioHora = 0;

        switch (vehiculo.getTipo()) {
            case CARRO: precioHora = 2000; break;
            case MOTO: precioHora = 1000; break;
            case CAMIONETA: precioHora = 3000; break;
            case VOLQUETA: precioHora = 4000; break;
            case TURBO: precioHora = 4000; break;
        }

        return horas * precioHora;
    }
}
