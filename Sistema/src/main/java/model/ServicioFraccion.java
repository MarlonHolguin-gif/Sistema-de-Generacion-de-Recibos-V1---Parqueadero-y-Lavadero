package model;

public class ServicioFraccion extends Servicio {

    public ServicioFraccion() {
        this.nombre = "Fracción (12 horas)";
    }

    @Override
    public double calcularPrecio(long horas, Vehiculo vehiculo) {

        double precioBase = 0;
        double precioExtra = 0;

        switch (vehiculo.getTipo()) {
            case CARRO:
                precioBase = 15000;
                precioExtra = 2000;
                break;
            case MOTO:
                precioBase = 8000;
                precioExtra = 1500;
                break;
            case CAMIONETA:
                precioBase = 20000;
                precioExtra = 3000;
                break;
            case VOLQUETA:
                precioBase = 30000;
                precioExtra = 4000;
                break;
            case TURBO:
                precioBase = 25000;
                precioExtra = 4000;
                break;
        }

        if (horas <= 12) {
            return precioBase;
        } else {
            long extra = horas - 12;
            return precioBase + (extra * precioExtra);
        }
    }
}