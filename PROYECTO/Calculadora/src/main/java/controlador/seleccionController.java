package controlador;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import modelo.modeloDatos;

import java.util.ArrayList;

public class seleccionController {
    @javafx.fxml.FXML
    private Button b_volver;
    @javafx.fxml.FXML
    private CheckBox check_long;
    @javafx.fxml.FXML
    private CheckBox check_area;
    @javafx.fxml.FXML
    private Button b_aceptar;
    @javafx.fxml.FXML
    private CheckBox check_coin;
    @javafx.fxml.FXML
    private CheckBox check_vol;
    @javafx.fxml.FXML
    private CheckBox check_masa;
    private modeloDatos datos;
    private ArrayList<String> seleccion;

    @javafx.fxml.FXML
    public void Aceptar(ActionEvent actionEvent) {
        llenarDatos();
        Stage ventana = (Stage) b_aceptar.getScene().getWindow();
        ventana.close();
    }

    @javafx.fxml.FXML
    public void Volver(ActionEvent actionEvent) {
        Stage ventana = (Stage) b_aceptar.getScene().getWindow();
        ventana.close();
    }

    private void llenarDatos(){
        seleccion = new ArrayList<>();
        if(check_long.isSelected()){
            seleccion.add("Longitud");
        }
        if (check_masa.isSelected()){
            seleccion.add("Masa");

        }
        if (check_coin.isSelected()){
            seleccion.add("Moneda");
        }
        if (check_area.isSelected()){
            seleccion.add("Area");
        }
        if (check_vol.isSelected()){
            seleccion.add("Volumen");
        }
        datos = new modeloDatos(seleccion);
    }
    public void setModelo (modeloDatos modelo) {
        this.datos = modelo;

    }
    public modeloDatos getModelo(){
        return this.datos;
    }
}
