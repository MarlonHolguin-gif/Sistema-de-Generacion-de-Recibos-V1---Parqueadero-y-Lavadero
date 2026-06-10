package printer;

import model.Recibo;
import model.ServicioFraccion;
import model.ServicioHoras;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class PDFGenerator {

    public void generarPDF(Recibo recibo, String tipo) {

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(new PDRectangle(220, 600));
            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.setFont(PDType1Font.COURIER, 8); float y = 580;

            DateTimeFormatter formatterVista =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            //FECHA
            String fecha = recibo.getEntrada()
                    .format(formatterVista);

            // TIEMPO
            long minutosReales = 0;
            long horasCobradas = 0;

            // SOLO calcular tiempo si existe salida
            if (recibo.getSalida() != null) {

                minutosReales = Duration.between(recibo.getEntrada(), recibo.getSalida()).toMinutes();

                if (minutosReales <= 0) {
                    minutosReales = 1;
                }
                //REDONDEAR A LA SIGUIENTE HORA
                horasCobradas = (long) Math.ceil(minutosReales / 60.0);
            }

            //INFORMACION
            escribirCentro(content, "PARQUEADERO Y LAVADERO AUTOMOTRIZ", y); y -= 12;
            escribirCentro(content, "POWER H&M", y); y -= 12;
            escribirCentro(content, "CALLE 30A #40-03", y); y -= 12;
            escribirCentro(content, "Armenia, Quindio", y); y -= 12;
            escribirCentro(content, "NIT: 4372926-5", y); y -= 15;
            escribirCentro(content, "------------------------------", y); y -= 15;

            //DATOS DEL RECIBO

            escribirIzquierda(content, "Fecha: " + fecha, y); y -= 12;
            escribirIzquierda(content, "Placa: " + recibo.getVehiculo().getPlaca(), y); y -= 12;
            escribirIzquierda(content, "Tipo Vehiculo: " + recibo.getVehiculo().getTipo(), y); y -= 12;
            escribirIzquierda(content, "Servicio: " + recibo.getServicio().getNombre(), y); y -= 15;


            // SERVICIO POR HORAS / FRACCION
            if (tipo.equals("SALIDA")) {
                if (recibo.getServicio() instanceof ServicioHoras || recibo.getServicio() instanceof ServicioFraccion) {
                    // MENOS DE 1 HORA
                    if (minutosReales < 60) {
                        escribirIzquierda(content, "Tiempo: " + minutosReales + " minutos", y); y -= 12;
                        escribirIzquierda(content, "Cobro aplicado: 1 hora", y); y -= 15;
                    }
                    // MAS DE 1 HORA
                    else {
                        escribirIzquierda(content, "Tiempo: " + minutosReales + " minutos", y); y -= 12;
                        escribirIzquierda(content, "Horas cobradas: " + horasCobradas + " hora(s)", y); y -= 15;
                    }
                }

                escribirCentro(content, "TOTAL: $" + recibo.getTotal(), y); y -= 20;
                escribirCentro(content, "------------------------------", y); y -= 15;
            }
            // PLANES

            if (tipo.equals("PLAN")) {
                escribirCentro(content, "PLAN ADQUIRIDO", y); y -= 15;
                escribirCentro(content, "TOTAL: $" + recibo.getTotal(), y); y -= 20;
                escribirCentro(content, "------------------------------", y); y -= 15;
            }

            // LAVADERO
            if (tipo.equals("LAVADO")) {
                escribirCentro(content, "SERVICIO DE LAVADO", y); y -= 15;
                escribirCentro(content, "TOTAL: $" + recibo.getTotal(), y); y -= 20;
                escribirCentro(content, "------------------------------", y); y -= 15;
            }
            // REGLAMENTO
            escribirCentro(content, "REGLAMENTO", y); y -= 12;
            escribirIzquierda(content, "1. Conserve este recibo", y); y -= 10;
            escribirIzquierda(content, "2. Deje el vehiculo cerrado", y); y -= 10;
            escribirIzquierda(content, "3. Se retiene por danos", y); y -= 10;
            escribirIzquierda(content, "4. Respete zonas asignadas", y); y -= 10;
            escribirIzquierda(content, "5. Presentar para salida", y); y -= 15;
            escribirCentro(content, "Gracias por su visita", y);
            content.close();

            // GUARDAR PDF
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String placa = recibo.getVehiculo().getPlaca();

            // USAR ENTRADA SI NO EXISTE SALIDA
            String fechaArchivo;
            if (recibo.getSalida() != null) {
                fechaArchivo = recibo.getSalida().format(formatter);
            } else {
                fechaArchivo = recibo.getEntrada().format(formatter);
            }

            String nombreArchivo = placa + "_" + tipo + "_" + fechaArchivo + ".pdf";
            String ruta = "C:\\Users\\Marlon Holguin\\Desktop\\Recibos\\" + nombreArchivo;
            document.save(ruta);
            System.out.println("Recibo generado: " + ruta);

        } catch (IOException e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
        }
    }

    // TEXTO CENTRADO
    private void escribirCentro(PDPageContentStream content, String texto, float y) throws IOException {
        float pageWidth = 220;
        float textWidth = PDType1Font.COURIER.getStringWidth(texto) / 1000 * 8;
        float x = (pageWidth - textWidth) / 2;
        content.beginText();
        content.newLineAtOffset(x, y);
        content.showText(texto);
        content.endText();
    }

    // TEXTO IZQUIERDA
    private void escribirIzquierda(PDPageContentStream content, String texto, float y) throws IOException {
        content.beginText();
        content.newLineAtOffset(10, y);
        content.showText(texto);
        content.endText();
    }
}