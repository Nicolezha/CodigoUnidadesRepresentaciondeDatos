/**
 * Sample Skeleton for 'UnidadesAlmacenamientoDig.fxml' Controller Class
 */
package Control;

import Model.DataRepresentation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DataRepresentationController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClean"
    private Button btnClean; // Value injected by FXMLLoader

    @FXML // fx:id="btnConvert"
    private Button btnConvert; // Value injected by FXMLLoader

    @FXML // fx:id="cboFromUnit"
    private ComboBox<DataRepresentation> cboFromUnit; // Value injected by FXMLLoader

    @FXML // fx:id="cboToUnit"
    private ComboBox<DataRepresentation> cboToUnit; // Value injected by FXMLLoader

    @FXML // fx:id="txtFromUnit"
    private TextField txtFromUnit; // Value injected by FXMLLoader

    @FXML // fx:id="txtToUnit"
    private TextField txtToUnit; // Value injected by FXMLLoader

    private DataRepresentation dataRepresentation;
    private ObservableList<DataRepresentation> fromUnit = FXCollections.observableArrayList();
    private ObservableList<DataRepresentation> toUnit = FXCollections.observableArrayList();

    @FXML
    void cleanSpaces(ActionEvent event) {
        txtFromUnit.clear();
        txtToUnit.clear();
        this.resetCboFromUnit();
        this.resetCboToUnit();
    }

    @FXML
    void convertUnits(ActionEvent event) {
        String fromUnitString = this.getFromUnit();
        String toUnitString = this.getToUnit();
        
        if (txtFromUnit.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha ingresado el valor a convertir", ButtonType.CLOSE);
            alert.showAndWait();
            return;
        }
        if (!dataRepresentation.isDecimal(txtFromUnit.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se permiten letras o espacios en blanco.", ButtonType.CLOSE);
            alert.showAndWait();
            return;
        }

        if (txtFromUnit.getText() != null) {
            if (cboFromUnit.getValue() != null) {
                if (txtFromUnit.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese la cantidad a convertir", ButtonType.CLOSE);
                    alert.showAndWait();
                    this.resetCboToUnit();
                    txtToUnit.clear();
                } else {
                    if (toUnitString.equals("Seleccione la unidad")) {
                        txtToUnit.setText("");
                    } else if (fromUnitString.equals(("Seleccione la unidad"))) {
                        txtFromUnit.setText("");
                    } else {
                        double amount = Double.parseDouble(txtFromUnit.getText());
                        txtToUnit.setText("" + dataRepresentation.convert(amount, fromUnitString, toUnitString));
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Seleccione la unidad de la cantidad", ButtonType.CLOSE);
                alert.showAndWait();
                this.resetCboToUnit();
            }
        }
    }

    private void resetCboToUnit() {
        cboToUnit.setOnAction(null);
        cboToUnit.getSelectionModel().select(0);
        cboToUnit.setOnAction(this::convertUnits);
    }

    private void resetCboFromUnit() {
        cboFromUnit.setOnAction(null);
        cboFromUnit.getSelectionModel().select(0);
        cboFromUnit.setOnAction(this::convertUnits);
    }

    private void units() {
        String[] units = {"Seleccione la unidad", "bit", "Byte", "Kb", "KB", "KiB", "Mb", "MB", "MiB", "Gb", "GB", "GiB",
            "Tb", "TB", "TiB", "Pb", "PB", "PiB", "Eb", "EB", "EiB", "Zb", "ZB", "ZiB",
            "Yb", "YB", "YiB"};

        DataRepresentation dr = null;

        for (int i = 0; i < units.length; i++) {
            dr = new DataRepresentation(units[i]);
            toUnit.add(dr);
            fromUnit.add(dr);
        }

        cboFromUnit.setItems(fromUnit);
        cboToUnit.setItems(toUnit);
    }

    private String getFromUnit() {
        DataRepresentation selectedSystem = cboFromUnit.getSelectionModel().getSelectedItem();

        if (selectedSystem != null) {
            return selectedSystem.getUnit();
        }

        return null;
    }

    private String getToUnit() {
        DataRepresentation selectedSystem = cboToUnit.getSelectionModel().getSelectedItem();

        if (selectedSystem != null) {
            return selectedSystem.getUnit();
        }

        return null;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClean != null : "fx:id=\"btnClean\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";
        assert btnConvert != null : "fx:id=\"btnConvert\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";
        assert cboFromUnit != null : "fx:id=\"cboFromUnit\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";
        assert cboToUnit != null : "fx:id=\"cboToUnit\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";
        assert txtFromUnit != null : "fx:id=\"txtFromUnit\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";
        assert txtToUnit != null : "fx:id=\"txtToUnit\" was not injected: check your FXML file 'UnidadesAlmacenamientoDig.fxml'.";

        this.units();

        dataRepresentation = new DataRepresentation();
    }

}
