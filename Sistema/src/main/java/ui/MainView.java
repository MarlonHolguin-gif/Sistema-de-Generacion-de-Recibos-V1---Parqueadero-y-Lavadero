package ui;

import controller.AppController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Recibo;
import model.TipoVehiculo;
import util.FechaUtil;
import util.NumeroUtil;

public class MainView extends Application {

    private AppController controller = new AppController();

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();


        //PESTAÑA 1: ENTRADA DEL VEHICULO

        //Creacion de campo para ingreso de placa
        TextField placaEntrada = new TextField();
        placaEntrada.setPromptText("Placa");

        //Creacion de casilla para elegir vehiculo
        ComboBox<TipoVehiculo> tipoVehiculoEntrada = new ComboBox<>();
        tipoVehiculoEntrada.getItems().addAll(TipoVehiculo.values());

        ComboBox<String> tipoServicio = new ComboBox<>();
        tipoServicio.getItems().addAll("Horas", "Fraccion");

        Label resultadoEntrada = new Label();

        Button btnEntrada = new Button("IMPRIMIR RECIBO");

        btnEntrada.setOnAction(e -> {

            if (placaEntrada.getText().isEmpty()
                    || tipoServicio.getValue() == null
                    || tipoVehiculoEntrada.getValue() == null) {

                resultadoEntrada.setText("Completa todos los campos");
                return;
            }

            controller.registrarEntrada(
                    placaEntrada.getText(),
                    tipoServicio.getValue(),
                    tipoVehiculoEntrada.getValue()
            );

            resultadoEntrada.setText("Entrada registrada");
            placaEntrada.clear();
            tipoServicio.setValue(null);
            tipoVehiculoEntrada.setValue(null);
        });

        VBox entradaBox = new VBox(10,
                new Label("Registro de Entrada"),
                placaEntrada,
                tipoVehiculoEntrada,
                tipoServicio,
                btnEntrada,
                resultadoEntrada
        );
        entradaBox.setPadding(new Insets(15));
        Tab tabEntrada = new Tab("RECIBO DIARIO", entradaBox);
        tabEntrada.setClosable(false);


        //PESTAÑA 2: SALIDA / PAGO DEL VEHICULO

        TextField placaSalida = new TextField();
        placaSalida.setPromptText("Placa");
        Label resultadoSalida = new Label();
        Button btnSalida = new Button("CALCULAR PAGO");
        btnSalida.setOnAction(e -> {

            if (placaSalida.getText().isEmpty()) {
                resultadoSalida.setText("Ingrese la placa");
                return;
            }

            Recibo r = controller.calcularPago(placaSalida.getText());

            if (r == null) {
                resultadoSalida.setText("Vehiculo no encontrado, revise la placa o verifique si el carro ya salió");
                return;
            }

            String texto = ""
                    + "Fecha de Salida " + FechaUtil.formatear(r.getEntrada()) + "\n"
                    + "Placa del Vehiculo " + r.getVehiculo().getPlaca() + "\n"
                    + "Tipo de Servicio " + r.getServicio().getNombre() + "\n"
                    + "Valor Total " + NumeroUtil.formatearMoneda(r.getTotal());

            resultadoSalida.setText(texto);
        });

        VBox salidaBox = new VBox(10,
                new Label("Salida / Pago"),
                placaSalida,
                btnSalida,
                resultadoSalida
        );
        salidaBox.setPadding(new Insets(15));

        Tab tabSalida = new Tab("SALIDA / PAGO", salidaBox);
        tabSalida.setClosable(false);

        //PESTAÑA 3: LAVADERO AUTOMOTRIZ
        TextField placaLavado = new TextField();
        placaLavado.setPromptText("Placa");

        ComboBox<TipoVehiculo> tipoVehiculoLavado = new ComboBox<>();
        tipoVehiculoLavado.getItems().addAll(TipoVehiculo.values());

        ComboBox<String> tipoLavado = new ComboBox<>();
        tipoLavado.getItems().addAll("Paquete Sencillo", "Paquete General", "Paquete Premium");

        Label resultadoLavado = new Label();

        Button btnLavado = new Button("GENERAR RECIBO");

        btnLavado.setOnAction(e -> {

            if (placaLavado.getText().isEmpty()
                    || tipoLavado.getValue() == null
                    || tipoVehiculoLavado.getValue() == null) {

                resultadoLavado.setText("Completa los datos");
                return;
            }

            Recibo r = controller.generarLavado(
                    placaLavado.getText(),
                    tipoLavado.getValue(),
                    tipoVehiculoLavado.getValue()
            );

            String texto = ""
                    + "Lavado\n"
                    + "Placa del Vehiculo " + r.getVehiculo().getPlaca() + "\n"
                    + "Tipo de Servicio " + r.getServicio().getNombre() + "\n"
                    + "Total del Servicio" + NumeroUtil.formatearMoneda(r.getTotal());

            resultadoLavado.setText(texto);
        });

        VBox lavadoBox = new VBox(10,
                new Label("Lavado de Vehículo"),
                placaLavado,
                tipoVehiculoLavado,
                tipoLavado,
                btnLavado,
                resultadoLavado
        );
        lavadoBox.setPadding(new Insets(15));

        Tab tabLavado = new Tab("LAVADERO", lavadoBox);
        tabLavado.setClosable(false);

        // PESTAÑA 4: TARIFAS
        TextField placaPlan = new TextField();
        placaPlan.setPromptText("Placa");

        ComboBox<TipoVehiculo> tipoVehiculoPlan = new ComboBox<>();
        tipoVehiculoPlan.getItems().addAll(TipoVehiculo.values());

        ComboBox<String> tipoPlan = new ComboBox<>();
        tipoPlan.getItems().addAll("Semana", "Quincena", "Mensualidad");

        Label resultadoPlan = new Label();

        Button btnPlan = new Button("GENERAR RECIBO");

        btnPlan.setOnAction(e -> {

            if (placaPlan.getText().isEmpty()
                    || tipoPlan.getValue() == null
                    || tipoVehiculoPlan.getValue() == null) {

                resultadoPlan.setText("Completa los datos");
                return;
            }

            Recibo r = controller.generarPlan(
                    placaPlan.getText(),
                    tipoPlan.getValue(),
                    tipoVehiculoPlan.getValue()
            );

            String texto = ""
                    + "PLAN: " + tipoPlan.getValue() + "\n"
                    + "PLACA: " + r.getVehiculo().getPlaca() + "\n"
                    + "VEHÍCULO: " + r.getVehiculo().getTipo() + "\n"
                    + "VALOR: " + NumeroUtil.formatearMoneda(r.getTotal());

            resultadoPlan.setText(texto);
        });

        VBox planBox = new VBox(10,
                new Label("Tarifas"),
                placaPlan,
                tipoVehiculoPlan,
                tipoPlan,
                btnPlan,
                resultadoPlan
        );
        planBox.setPadding(new Insets(15));

        Tab tabPlan = new Tab("TARIFAS", planBox);
        tabPlan.setClosable(false);

        // =========================
        tabPane.getTabs().addAll(tabEntrada, tabSalida, tabLavado, tabPlan);

        Scene scene = new Scene(tabPane, 450, 420);

        stage.setTitle("SISTEMA PARQUEADERO Y LAVADERO AUTOMOTRIZ POWER H&M");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}