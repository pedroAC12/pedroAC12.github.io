package controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Operaciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static modelo.Operaciones.operacionesBasicas;

public class cientificaController implements Initializable {
    private Stage stageInfo = new Stage();
    private Stage stageAbout = new Stage();
    private Button[] num_pad= new Button[10];
    private Button[] operations= new Button[4];
    private String valor_num_pad="";
    private char signo;
    @javafx.fxml.FXML
    private Label L_Memory;
    @javafx.fxml.FXML
    private Button b_mult;
    @javafx.fxml.FXML
    private Pane P_Memory;
    @javafx.fxml.FXML
    private Button b_c;
    @javafx.fxml.FXML
    private Button b_MR;
    @javafx.fxml.FXML
    private Button b_0;
    @javafx.fxml.FXML
    private Button b_igual;
    @javafx.fxml.FXML
    private Label l_resultado;
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
    private Button b_div;
    @javafx.fxml.FXML
    private Button b_sig;
    @javafx.fxml.FXML
    private Button b_M;
    @javafx.fxml.FXML
    private Button b_ce;
    @javafx.fxml.FXML
    private Button b_Mminus;
    @javafx.fxml.FXML
    private Button b_Mplus;
    @javafx.fxml.FXML
    private Button b_rest;
    @javafx.fxml.FXML
    private Label l_memoria;
    @javafx.fxml.FXML
    private Button b_del;
    @javafx.fxml.FXML
    private Button b_sum;
    @javafx.fxml.FXML
    private Button b_tan;
    @javafx.fxml.FXML
    private Button b_pot;
    @javafx.fxml.FXML
    private Button b_sin;
    @javafx.fxml.FXML
    private Button b_cos;
    @javafx.fxml.FXML
    private VBox Principal;
    @FXML
    private Button b_info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarNumPad();
        activarNumPad();
        llenarOperations();
        activarOperations();
        P_Memory.setVisible(false);
        L_Memory.setText("0");

    }


    private void llenarNumPad(){
        num_pad[0]=b_0;
        num_pad[1]=b_1;
        num_pad[2]=b_2;
        num_pad[3]=b_3;
        num_pad[4]=b_4;
        num_pad[5]=b_5;
        num_pad[6]=b_6;
        num_pad[7]=b_7;
        num_pad[8]=b_8;
        num_pad[9]=b_9;
    }

    private void activarNumPad(){
        for (int i = 0; i < num_pad.length; i++) {
            final int valor = i;
            num_pad[i].setOnAction(e -> {
                valor_num_pad += num_pad[valor].getText();
                l_resultado.setText(valor_num_pad);
            });
        }
    }
    private void llenarOperations(){
        operations[0] = b_sum;
        operations[1] = b_rest;
        operations[2] = b_mult;
        operations[3] = b_div;

    }
    private void activarOperations(){
        for (int i = 0; i < operations.length; i++) {
            final int valor = i;
            operations[i].setOnAction(e -> {
                signo = operations[valor].getText().charAt(0);
                if (signo == 'x'){
                    signo = '*';
                }
                ponerSigno();

            });
        }
    }

    @FXML
    public void doSign(ActionEvent actionEvent) {
        if (!valor_num_pad.isEmpty()){
            double a = Double.valueOf(valor_num_pad);
            a = -a;
            valor_num_pad = String.valueOf(a);
            l_resultado.setText(valor_num_pad);
        }

    }

    @FXML
    public void doDel(ActionEvent actionEvent) {
        borrar();
    }

    @FXML
    public void doOperation(ActionEvent actionEvent) {
        if(!l_memoria.getText().isEmpty() && !valor_num_pad.isEmpty())
            hacerOperacion();

    }
    @FXML
    public void keyPressed(KeyEvent event) {
        String key = event.getText();
        System.out.println(event.getCode());

        if (key.matches("[0-9]")) {
            valor_num_pad += key;
            l_resultado.setText(valor_num_pad);
        }
        if (key.matches("[-+/*]")) {
            signo = key.charAt(0);
            ponerSigno();
        }
        if (event.getCode() == KeyCode.BACK_SPACE) {
            borrar();

        }

        if (event.getCode() == KeyCode.COMMA ||event.getCode() == KeyCode.DECIMAL ){
            if (!valor_num_pad.contains("."))
                valor_num_pad +=".";
            l_resultado.setText(valor_num_pad);
        }
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("OPERACION");
        }
        if(key.matches("s")){
            operacioTrigo('s');
        }
        if(key.matches("c")){
            operacioTrigo('c');
        }
        if(key.matches("t")){
            operacioTrigo('t');
        }
    }
    private void hacerOperacion(){
        try{
            double sol = operacionesBasicas(l_memoria.getText(),valor_num_pad);
            System.out.println(sol);
            sol = redondear(sol);
            String sol_t =String.valueOf(sol);
            if (sol_t.endsWith(".0")){
                sol_t = sol_t.substring(0,sol_t.length()-2);
            }
            l_resultado.setText(String.valueOf(sol_t));
            valor_num_pad = String.valueOf(sol_t);
        }catch (ArithmeticException e){
            l_resultado.setText("Error Matemático");
            valor_num_pad = "";

        }
        l_memoria.setText("");

    }

    private double redondear(double e){
        double a = e*100;
        a = Math.round(a);
        return a/100;
    }

    private void borrar(){
        if (valor_num_pad.length() > 1) {
            valor_num_pad = valor_num_pad.substring(0, valor_num_pad.length() - 1);
            l_resultado.setText(valor_num_pad);
        }
        if (valor_num_pad.length() == 1) {
            valor_num_pad = "";
            l_resultado.setText("0");
        }
    }
    private void ponerSigno(){
        if (!valor_num_pad.isEmpty()){
            if(l_memoria.getText().isEmpty()){
                l_memoria.setText(valor_num_pad +signo);
                valor_num_pad = "";
                l_resultado.setText("0");
            }else{
                System.out.println(l_memoria.getText());
                hacerOperacion();
                l_memoria.setText(l_resultado.getText()+signo);
                l_resultado.setText("0");
                valor_num_pad="";
            }
        }
    }

    @FXML
    public void borrarPantalla(ActionEvent actionEvent) {
        valor_num_pad = "";
        l_resultado.setText("0");
    }

    @FXML
    public void borrarTodo(ActionEvent actionEvent) {
        l_memoria.setText("");
        valor_num_pad = "";
        l_resultado.setText("0");

    }

    @FXML
    public void doMminus(ActionEvent actionEvent) {
        double sol = operacionesBasicas(L_Memory.getText()+"-",l_resultado.getText());
        L_Memory.setText(String.valueOf(sol));
    }

    @FXML
    public void showM(ActionEvent actionEvent) {
        if (!P_Memory.isVisible()){
            P_Memory.setVisible(true);
        }else{
            P_Memory.setVisible(false);
        }
    }

    @FXML
    public void Mplus(ActionEvent actionEvent) {
        double sol = operacionesBasicas(L_Memory.getText()+"+",l_resultado.getText());
        L_Memory.setText(String.valueOf(sol));
    }

    @FXML
    public void doMR(ActionEvent actionEvent) {
        L_Memory.setText("0");
    }


    @javafx.fxml.FXML
    public void calcNormal(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/calculadora.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(680);
        Principal.getScene().getWindow().setWidth(340);

    }

    @javafx.fxml.FXML
    public void salirCalc(ActionEvent actionEvent) {
        Stage ventana = (Stage) b_0.getScene().getWindow();
        ventana.close();
    }

    private void operacioTrigo(char sign){
        valor_num_pad = l_resultado.getText();
        double sol = 0;
        switch (sign){
            case 's':
                 sol = Operaciones.operacionesCientifica(valor_num_pad,'s');
                l_memoria.setText("sen("+valor_num_pad+")");
                break;
            case 'c':
                 sol = Operaciones.operacionesCientifica(valor_num_pad,'c');
                l_memoria.setText("cos("+valor_num_pad+")");
                break;
            case 't':
                 sol = Operaciones.operacionesCientifica(valor_num_pad,'t');
                l_memoria.setText("tan("+valor_num_pad+")");
                break;
        }
        String sol_t =String.valueOf(sol);
        if (sol_t.endsWith(".0")){
            sol_t = sol_t.substring(0,sol_t.length()-2);
        }
        l_resultado.setText(sol_t);
        valor_num_pad = l_resultado.getText();

    }
    @FXML
    public void tan(ActionEvent actionEvent) {
        operacioTrigo('t');

    }

    @FXML
    public void cos(ActionEvent actionEvent) {
        operacioTrigo('c');
    }

    @FXML
    public void potencia(ActionEvent actionEvent) {
        signo = '^';
        ponerSigno();
       // hacerOperacion();
    }

    @FXML
    public void sin(ActionEvent actionEvent) {
        operacioTrigo('s');
    }

    @FXML
    public void Info(ActionEvent actionEvent) throws IOException {

        if(!stageInfo.isShowing()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/informacion.fxml"));
            Pane root = (Pane) loader.load();
            stageInfo = new Stage();
            stageInfo.setResizable(false);
            stageInfo.setTitle("Información");
            stageInfo.setScene(new Scene(root));
            stageInfo.show();
        }

    }

    @FXML
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

    @FXML
    public void resetAll(ActionEvent actionEvent) {
        L_Memory.setText("0");
        P_Memory.setVisible(false);
        l_memoria.setText("");
        valor_num_pad = "";
        l_resultado.setText("0");

    }

    @FXML
    public void Graficos(ActionEvent actionEvent) throws IOException {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("/vista/graficos.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(675);
        Principal.getScene().getWindow().setWidth(950);
    }

    @FXML
    public void conversor(ActionEvent actionEvent) throws IOException {
        VBox panel = FXMLLoader.load(getClass().getResource("/vista/conversor.fxml"));
        Principal.getChildren().setAll(panel);
        Principal.getScene().getWindow().setHeight(630);
        Principal.getScene().getWindow().setWidth(425);
    }
}
