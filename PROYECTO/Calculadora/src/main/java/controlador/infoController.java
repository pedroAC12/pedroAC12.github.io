package controlador;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class infoController {
    @javafx.fxml.FXML
    private Button b_close;

    @javafx.fxml.FXML
    public void infoClose(ActionEvent actionEvent) {
        Stage ventana = (Stage) b_close.getScene().getWindow();
        ventana.close();
    }
}
