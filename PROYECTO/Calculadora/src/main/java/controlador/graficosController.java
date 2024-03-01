package controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class graficosController implements Initializable {
    private Stage stageAbout = new Stage();
    @javafx.fxml.FXML
    private TextField funcionTextField;

    @javafx.fxml.FXML

    private LineChart<Number, Number> graficoFuncion;
    @javafx.fxml.FXML
    private AnchorPane Fondo;
    private ArrayList<String> funciones;
    @javafx.fxml.FXML
    private Button b_limpiar;
    @javafx.fxml.FXML
    private Button b_dibujar;

    @javafx.fxml.FXML
    void dibujarFuncion(ActionEvent event) {
        String funcion = funcionTextField.getText();
        if (!funcion.isEmpty()) {
            graficarFuncion(funcion);
        }
    }

    @javafx.fxml.FXML

    void limpiarPantalla(ActionEvent event) {
        graficoFuncion.getData().clear();
    }
    private void graficarFuncion(String funcion) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(funcion);
        for (double x = 0.1; x <= 15; x += 0.1) {
                double y = calcularValores(funcion, x);
                series.getData().add(new XYChart.Data<>(x, y));
        }

        graficoFuncion.getData().add(series);
    }

    private double calcularValores(String funcion,double x){
        switch (funcion){
            case "sin(x)":
                return Math.sin(x);
            case "cos(x)":
                return Math.cos(x);

            case "x":
                return x;

            case "x^2":
                return Math.pow(x,2);

            case "x^3":
                return Math.pow(x,3);

            case "log(x)":
                return Math.log(x);


        }
        return 0;

    }
    private void llenarFunciones(){
        funciones = new ArrayList<>();
        funciones.add("sin(x)");
        funciones.add("cos(x)");
        funciones.add("x");
        funciones.add("x^2");
        funciones.add("x^3");
        funciones.add("log(x)");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarFunciones();
    }

    @javafx.fxml.FXML
    public void conversor(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/conversor.fxml"));
        Fondo.getChildren().setAll(panel);
        Fondo.getScene().getWindow().setHeight(630);
        Fondo.getScene().getWindow().setWidth(425);
    }

    @javafx.fxml.FXML
    public void calcNormal(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/calculadora.fxml"));
        Fondo.getChildren().setAll(panel);
        Fondo.getScene().getWindow().setHeight(680);
        Fondo.getScene().getWindow().setWidth(340);
    }

    @javafx.fxml.FXML
    public void about(ActionEvent actionEvent) throws IOException {
        if(!stageAbout.isShowing()){
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
        Stage ventana = (Stage) b_dibujar.getScene().getWindow();
        ventana.close();
    }

    @javafx.fxml.FXML
    public void resetAll(ActionEvent actionEvent) {
        graficoFuncion.getData().clear();
        funcionTextField.setText("");
    }

    @javafx.fxml.FXML
    public void calcCientifica(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/conversor.fxml"));
        Fondo.getChildren().setAll(panel);
        Fondo.getScene().getWindow().setHeight(630);
        Fondo.getScene().getWindow().setWidth(425);
    }
}
