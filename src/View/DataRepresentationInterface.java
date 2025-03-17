

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Use los comentarios para explicar el objetivo de este programa CoffeMachineView .
 *
 * @author Ponga aqui su nombre y correo
 * @version Ponga aquí la versión o fecha
 */
public class DataRepresentationInterface extends Application{
    
    public static void main(String[] args) {
        Application.launch(DataRepresentationInterface.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnidadesAlmacenamientoDig.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }   
    
}

