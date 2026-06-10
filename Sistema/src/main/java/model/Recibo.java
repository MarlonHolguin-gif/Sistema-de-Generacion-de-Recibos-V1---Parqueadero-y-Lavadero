package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Recibo {

    private Vehiculo vehiculo;
    private Servicio servicio;
    private LocalDateTime entrada;
    private LocalDateTime salida;
    private double total;

    // NUEVO
    private long minutosReales;
    private long horasCobradas;

    public Recibo() {
        this.entrada = LocalDateTime.now();
    }

    public void calcularTotal() {

        if (vehiculo == null || servicio == null) {
            throw new IllegalStateException(
                    "Vehiculo o servicio no asignado"
            );
        }

        long horas = 0;

        // SOLO PARA HORAS Y FRACCION
        if (servicio instanceof ServicioHoras ||
                servicio instanceof ServicioFraccion) {

            if (entrada == null) {
                entrada = LocalDateTime.now();
            }

            if (salida == null) {
                salida = LocalDateTime.now();
            }

            long minutos =
                    Duration.between(entrada, salida).toMinutes();

            if (minutos <= 0) {
                minutos = 1;
            }

            // GUARDAR MINUTOS REALES
            this.minutosReales = minutos;

            // REDONDEAR HACIA ARRIBA
            horas = (long) Math.ceil(minutos / 60.0);

            // GUARDAR HORAS COBRADAS
            this.horasCobradas = horas;
        }

        this.total = servicio.calcularPrecio(horas, vehiculo);
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public double getTotal() {
        return total;
    }

    public long getMinutosReales() {
        return minutosReales;
    }

    public long getHorasCobradas() {
        return horasCobradas;
    }
}