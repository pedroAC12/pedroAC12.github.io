package controlador;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class aboutController {
    @javafx.fxml.FXML
    private AnchorPane principal_about;
    @javafx.fxml.FXML
    private Button b_salir;

    @javafx.fxml.FXML
    public void salir(ActionEvent actionEvent) {
        Stage ventana = (Stage) b_salir.getScene().getWindow();
        ventana.close();
    }
}
