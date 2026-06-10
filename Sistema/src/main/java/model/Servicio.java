package model;

public abstract class Servicio {

    protected String nombre;

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularPrecio(long horas, Vehiculo vehiculo);
}