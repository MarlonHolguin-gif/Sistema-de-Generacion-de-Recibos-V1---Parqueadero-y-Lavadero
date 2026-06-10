package controller;

import model.*;
import printer.PDFGenerator;

public class AppController {

    // ================= ENTRADA =================
    public void registrarEntrada(String placa, String tipoServicio, TipoVehiculo tipoVehiculo) {

        Vehiculo v = new Vehiculo(placa, tipoVehiculo);

        Servicio servicio;

        switch (tipoServicio) {
            case "Horas":
                servicio = new ServicioHoras();
                break;
            case "Fraccion":
                servicio = new ServicioFraccion();
                break;
            default:
                servicio = new ServicioHoras();
        }

        Recibo r = new Recibo();
        r.setEntrada(java.time.LocalDateTime.now());
        r.setVehiculo(v);
        r.setServicio(servicio);

        Registro.registrarEntrada(placa, r);

        new PDFGenerator().generarPDF(r, "ENTRADA");
    }

    // ================= SALIDA =================
    public Recibo calcularPago(String placa) {

        Recibo r = Registro.obtener(placa);

        if (r == null) return null;

        r.setSalida(java.time.LocalDateTime.now());
        r.calcularTotal();

        Registro.eliminar(placa);

        // ✅ GENERAR PDF EN SALIDA
        new PDFGenerator().generarPDF(r, "SALIDA");

        return r;
    }

    // ================= LAVADO =================
    public Recibo generarLavado(String placa, String tipoLavado, TipoVehiculo tipoVehiculo) {

        Vehiculo v = new Vehiculo(placa, tipoVehiculo);

        Servicio servicio = new ServicioLavado(tipoLavado);

        Recibo r = new Recibo();
        r.setVehiculo(v);
        r.setServicio(servicio);

        r.calcularTotal();

        new PDFGenerator().generarPDF(r, "LAVADO");

        return r;
    }

    // ================= PLANES =================
    public Recibo generarPlan(String placa, String tipoPlan,TipoVehiculo tipoVehiculo) {

        Vehiculo v = new Vehiculo(placa, tipoVehiculo);

        Servicio servicio = new ServicioTiempoFijo(tipoPlan);

        Recibo r = new Recibo();
        r.setVehiculo(v);
        r.setServicio(servicio);

        r.calcularTotal();

        new PDFGenerator().generarPDF(r, "PLAN");

        return r;
    }
}