package printer;

import javax.print.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ThermalPrinterService {

    public void imprimir(String texto) {
        try {
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            if (service == null) {
                System.out.println("No hay impresora configurada");
                return;
            }

            DocPrintJob job = service.createPrintJob();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Inicializar impresora
            baos.write(ESCPosCommands.INIT);

            // Centrar título
            baos.write(ESCPosCommands.CENTRAR);
            baos.write(ESCPosCommands.NEGRITA_ON);

            baos.write(texto.getBytes());

            baos.write(ESCPosCommands.NEGRITA_OFF);
            baos.write(ESCPosCommands.IZQUIERDA);

            // Corte de papel
            baos.write(ESCPosCommands.CORTE);

            byte[] bytes = baos.toByteArray();

            Doc doc = new SimpleDoc(bytes, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);

            job.print(doc, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
