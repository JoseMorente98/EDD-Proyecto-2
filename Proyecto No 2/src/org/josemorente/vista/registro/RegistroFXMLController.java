/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.registro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.controlador.UsuarioControlador;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class RegistroFXMLController implements Initializable {
    @FXML
    private TextField textFieldCarnet;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellido;
    @FXML
    private TextField textFieldCarrera;
    @FXML
    private TextField textFieldPassword;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        try {
            if(validacion()) {
                UsuarioControlador.getInstance().agregarUsuarioServidor(
                Integer.parseInt(textFieldCarnet.getText()),
                textFieldNombre.getText(),
                textFieldApellido.getText(),
                textFieldCarrera.getText(),
                textFieldPassword.getText());
                NotificacionControlador.getInstance().informacion("Usuario Guardado", "Los cambios se han guardado exitosamente.");
            } else {
                NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
            }
        } catch (NumberFormatException e) {
            NotificacionControlador.getInstance().error("Validación de Campos", "El carnet es un campo numérico.");
        }
    }    
    
    public boolean validacion() {
        if(textFieldCarnet.getText().length() > 0 &&
            textFieldNombre.getText().length() > 0 &&
            textFieldApellido.getText().length() > 0 &&
            textFieldCarrera.getText().length() > 0 &&
            textFieldPassword.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
}
