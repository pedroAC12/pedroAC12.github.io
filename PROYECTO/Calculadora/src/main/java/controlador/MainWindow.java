package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/vista/calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 680);
        scene.getStylesheets().add(getClass().getResource("/vista/style.css").toExternalForm());
        //stage.setResizable(false);
        Image icono = new Image(getClass().getResourceAsStream("/etc/calculadora.png"));
        stage.setTitle("Calculadora");
        stage.getIcons().add(icono);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}