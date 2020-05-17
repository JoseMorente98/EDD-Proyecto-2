/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.josemorente.bean.Usuario;
import org.josemorente.controlador.MD5Controlador;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 *
 * @author josem
 */
public class FXMLDocumentController implements Initializable {    
    @FXML
    private Label label;
    @FXML
    private TextField textFieldCarnet;
    @FXML
    private TextField textFieldPassword;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void sigIn(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("org/josemorente/vista/registro/RegistroFXML.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Login");
        stage2.setScene(new Scene(root));
        stage2.show();
    }
    
    @FXML
    private void configuracion(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("org/josemorente/vista/configuracion/ConfiguracionFXML.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Configuración");
        stage2.setScene(new Scene(root));
        stage2.show();
    }
    
    @FXML
    private void logIn(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
        
        /*try {
            if(validacion()) {
                Usuario usuario = UsuarioControlador.getInstance().autenticar(
                Integer.parseInt(textFieldCarnet.getText()),
                MD5Controlador.getInstance().Encriptar(textFieldPassword.getText()));
                
                if(usuario != null) {
                    NotificacionControlador.getInstance().informacion("Bienvenido", "Ha iniciado sesión.");
                } else {
                    NotificacionControlador.getInstance().error("Inicio de Sesión", "Usuario o contraseña Incorrectos.");
                }
            } else {
                NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
            }
        } catch (NumberFormatException e) {
            NotificacionControlador.getInstance().error("Validación de Campos", "El carnet es un campo numérico.");
        }*/
    }
    
    public boolean validacion() {
        if(textFieldCarnet.getText().length() > 0 &&
            textFieldPassword.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
}
