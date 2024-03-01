package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conversiones;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import modelo.modeloDatos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class conversorController implements Initializable {
    @javafx.fxml.FXML
    private Button b_c;
    @javafx.fxml.FXML
    private ComboBox combo_tipo2;
    @javafx.fxml.FXML
    private ComboBox combo_tipo1;
    @javafx.fxml.FXML
    private Button b_0;
    @javafx.fxml.FXML
    private Button b_igual;
    @javafx.fxml.FXML
    private Label l_resultado;
    @javafx.fxml.FXML
    private ComboBox combo_datos;
    @javafx.fxml.FXML
    private Button b_2;
    @javafx.fxml.FXML
    private Button b_1;
    @javafx.fxml.FXML
    private Button b_4;
    @javafx.fxml.FXML
    private Button b_3;
    @javafx.fxml.FXML
    private Button b_6;
    @javafx.fxml.FXML
    private Button b_5;
    @javafx.fxml.FXML
    private Button b_coma;
    @javafx.fxml.FXML
    private Button b_8;
    @javafx.fxml.FXML
    private Button b_7;
    @javafx.fxml.FXML
    private Button b_9;
    @javafx.fxml.FXML
    private VBox Principal;
    @javafx.fxml.FXML
    private Button b_del;
    private ObservableList<String> datos_1;
    private ObservableList<String> datos_Longitud1;
    private ObservableList<String> datos_2;
    private ObservableList<String> datos_Longitud2;
    private Button[] num_pad = new Button[10];
    private Button[] operations = new Button[4];
    private String valor_num_pad = "";
    private Stage stageAbout = new Stage();
    private modeloDatos datos;
    private ArrayList<String> data;

    private ArrayList<String> monedas;



    @FXML
    public void normal(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/calculadora.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(680);
        Principal.getScene().getWindow().setWidth(340);
    }


    @FXML
    public void keyPressed(KeyEvent event) {
        String key = event.getText();


        if (key.matches("[0-9]")) {
            valor_num_pad += key;
            l_resultado.setText(valor_num_pad);
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            borrar();

        }
        if (event.getCode() == KeyCode.COMMA || event.getCode() == KeyCode.DECIMAL) {
            if (!valor_num_pad.contains("."))
                valor_num_pad += ".";
            l_resultado.setText(valor_num_pad);
        }
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("OPERACION");
        }

    }

    @javafx.fxml.FXML
    public void doDel(ActionEvent actionEvent) {
        borrar();
    }

    @javafx.fxml.FXML
    public void doOperation(ActionEvent actionEvent) {
        if (!valor_num_pad.isEmpty() && combo_tipo2.getValue() != null && combo_tipo1.getValue() != null) {
        if (combo_datos.getValue().equals("Moneda")){
            String coin1 = (String) combo_tipo1.getValue();
            String coin2 = (String) combo_tipo2.getValue();
            double sol = Conversiones.cambiarMonedas(Double.valueOf(valor_num_pad),coin1,coin2);
            String unidad = (String) combo_tipo2.getValue();
            valor_num_pad = "";
            l_resultado.setText(String.valueOf(sol) + " " + unidad);

        }else{
            double sol = 0;

                String seleccionado_1 = (String) combo_tipo1.getValue();
                System.out.println(seleccionado_1);
                String seleccionado_2 = (String) combo_tipo2.getValue();
                if (!seleccionado_1.endsWith("2") && !seleccionado_1.endsWith("3")){
                    String u1 = seleccionado_1.substring(0, seleccionado_1.length() - 1);
                    String u2 = seleccionado_2.substring(0, seleccionado_2.length() - 1);
                    sol = Conversiones.hacerConversion(Double.valueOf(valor_num_pad), u1, u2);
                }
                if (seleccionado_1.endsWith("2")){
                    String u1 = seleccionado_1.substring(0, seleccionado_1.length() - 3);
                    String u2 = seleccionado_2.substring(0, seleccionado_2.length() - 3);
                    sol = Conversiones.hacerConversion2(Double.valueOf(valor_num_pad), u1, u2,2);
                }
                if (seleccionado_1.endsWith("3")){
                    String u1 = seleccionado_1.substring(0, seleccionado_1.length() - 3);
                    String u2 = seleccionado_2.substring(0, seleccionado_2.length() - 3);
                    sol = Conversiones.hacerConversion2(Double.valueOf(valor_num_pad), u1, u2,3);
                }

                String unidad = (String) combo_tipo2.getValue();
                valor_num_pad = "";
                l_resultado.setText(String.valueOf(sol) + " " + unidad);
           }



        }

    }

    @javafx.fxml.FXML
    public void about(ActionEvent actionEvent) throws IOException {

        if (!stageAbout.isShowing()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/about.fxml"));
            Pane root = (Pane) loader.load();
            stageAbout = new Stage();
            stageAbout.setResizable(false);
            stageAbout.setTitle("About");
            stageAbout.setScene(new Scene(root));
            stageAbout.show();
        }
    }

    @javafx.fxml.FXML
    public void salirCalc(ActionEvent actionEvent) {
        Stage ventana = (Stage) b_0.getScene().getWindow();
        ventana.close();
    }

    @javafx.fxml.FXML
    public void borrarTodo(ActionEvent actionEvent) {
        valor_num_pad = "";
        l_resultado.setText("0");
    }

    @javafx.fxml.FXML
    public void resetAll(ActionEvent actionEvent) {
        valor_num_pad = "";
        l_resultado.setText("0");
        combo_datos.setValue(null);
        combo_tipo1.setValue(null);
        combo_tipo2.setValue(null);

    }

    @javafx.fxml.FXML
    public void calcCientifica(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/calcientifica.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(680);
        Principal.getScene().getWindow().setWidth(435);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        llenarNumPad();
        activarNumPad();
        defaultDatos();
        llenarComboDatos();
        Conversiones.llenarUnidades();
    }

    private void llenarComboDatos() {
        combo_datos.getItems().clear();
        ObservableList<String> conversiones =
                FXCollections.observableArrayList();
        for (String dat:data) {
            conversiones.add(dat);
        }
        combo_datos.setItems(conversiones);
    }

    @javafx.fxml.FXML
    public void datoElegido(ActionEvent actionEvent) {
        if (combo_datos.getValue() != null) {
            String seleccionado = (String) combo_datos.getValue();
            if (seleccionado.equals("Masa")) {
                dataElegida('m');
            }
            if (seleccionado.equals("Longitud")) {
                dataElegida('l');
            }
            if (seleccionado.equals("Area")) {
                dataElegida('a');
            }
            if (seleccionado.equals("Volumen")) {
                dataElegida('v');
            }
            if(seleccionado.equals("Moneda")){
                dataElegida('c');
            }
        }

    }

    private void dataElegida(char a) {
        datos_1 = FXCollections.observableArrayList();
        datos_2 = FXCollections.observableArrayList();
        if (a == 'c'){
            for (String dat: monedas) {
                datos_1.add(dat);
                datos_2.add(dat);
            }
        }else{
            HashMap masa = Conversiones.obtenerHash(a);

            for (int i = 1; i <= masa.size(); i++) {
                String dato = (String) masa.get(i);
                datos_1.add(dato);
                datos_2.add(dato);
        }

        }
        combo_tipo1.setItems(datos_1);
        combo_tipo2.setItems(datos_2);

    }

    private void llenarNumPad() {
        num_pad[0] = b_0;
        num_pad[1] = b_1;
        num_pad[2] = b_2;
        num_pad[3] = b_3;
        num_pad[4] = b_4;
        num_pad[5] = b_5;
        num_pad[6] = b_6;
        num_pad[7] = b_7;
        num_pad[8] = b_8;
        num_pad[9] = b_9;
    }

    private void activarNumPad() {
        for (int i = 0; i < num_pad.length; i++) {
            final int valor = i;
            num_pad[i].setOnAction(e -> {
                valor_num_pad += num_pad[valor].getText();
                l_resultado.setText(valor_num_pad);
            });
        }
    }

    private void borrar() {
        if (valor_num_pad.length() > 1) {
            valor_num_pad = valor_num_pad.substring(0, valor_num_pad.length() - 1);
            l_resultado.setText(valor_num_pad);
        }
        if (valor_num_pad.length() == 1) {
            valor_num_pad = "";
            l_resultado.setText("0");
        }
    }

    @FXML
    public void launchSeleccion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/seleccion.fxml"));
        Pane root = (Pane) loader.load();
        //los datos se pasan a la ventana a través de la clase Modelo obteniendo el controlador
        seleccionController controlador = loader.getController();
        controlador.setModelo(datos);
        Scene scene = new Scene(root);
        //Se crea un nuevo Stage
        Stage stageWindow = new Stage();
        stageWindow.setTitle("Seleccion de datos");
        stageWindow.initModality(Modality.APPLICATION_MODAL);
        stageWindow.setScene(scene);
        stageWindow.showAndWait();
        datos = controlador.getModelo();
        data = datos.getDatos();
         llenarComboDatos();

    }
    private void defaultDatos(){
        data = new ArrayList<>();
        data.add("Masa");
        data.add("Longitud");
        data.add("Moneda");
        data.add("Area");
        data.add("Volumen");
        datos = new modeloDatos(data);

        monedas = new ArrayList<>();
        monedas.add("USD");
        monedas.add("EUR");
        monedas.add("GBP");
        monedas.add("JPY");
        monedas.add("MXN");
        monedas.add("CHF");
        monedas.add("SEK");
        monedas.add("BRL");
    }


    @FXML
    public void Graficos(ActionEvent actionEvent) throws IOException {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("/vista/graficos.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(675);
        Principal.getScene().getWindow().setWidth(950);
    }
}
