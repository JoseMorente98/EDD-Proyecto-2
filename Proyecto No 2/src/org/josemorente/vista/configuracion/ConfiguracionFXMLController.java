/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.configuracion;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.josemorente.bean.Cliente;
import org.josemorente.controlador.ClienteControlador;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.controlador.UsuarioControlador;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class ConfiguracionFXMLController implements Initializable {
    @FXML
    private TextField textFieldIP;
    @FXML
    private TextField textFieldIPServidor;
    @FXML
    private TextField textFieldPuerto;
    @FXML
    private TextField textFieldPuertoServidor;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String address = InetAddress.getLocalHost().getHostAddress();
            textFieldIP.setText(address);
            textFieldIPServidor.setText(address);
            textFieldPuerto.setText("3100");
            textFieldPuertoServidor.setText("3000");
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConfiguracionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void guardarCambios(ActionEvent event) {
        try {
            if(validacion()) {
                Cliente.setIp(textFieldIP.getText());
                Cliente.setIpServidor(textFieldIPServidor.getText());
                Cliente.setPuerto(textFieldPuerto.getText());
                Cliente.setPuertoServidor(textFieldPuertoServidor.getText());
                NotificacionControlador.getInstance().informacion("Configuración Guardada", "El cliente esta conectado.");
                ClienteControlador.getInstance().iniciarServidor();
                OrdenadorControlador.getInstance().agregarOrdenadorServidor(Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
            } else {
                NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
            }
        } catch (NumberFormatException e) {
            NotificacionControlador.getInstance().error("Validación de Campos", "Los puertos son campos numéricos.");
        }
    }  
    
    @FXML
    private void apagarServidor(ActionEvent event) {
        ClienteControlador.getInstance().detenerServidor();
    }  
    
    public boolean validacion() {
        if(textFieldIP.getText().length() > 0 &&
            textFieldIPServidor.getText().length() > 0 &&
            textFieldPuerto.getText().length() > 0 &&
            textFieldPuertoServidor.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
}
