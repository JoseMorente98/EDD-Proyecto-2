/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import javafx.scene.control.Alert;

/**
 *
 * @author josem
 */
public class NotificacionControlador {
    
    private NotificacionControlador() {
    }
    
    public static NotificacionControlador getInstance() {
        return NotificacionControladorHolder.INSTANCE;
    }
    
    private static class NotificacionControladorHolder {

        private static final NotificacionControlador INSTANCE = new NotificacionControlador();
    }
    
    
    /*public void getAlert(String content) {
        JFXDialogLayout c = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, c, JFXDialog.DialogTransition.CENTER);
        c.setHeading(new Text("Error!"));
        c.setBody(new Text(content));
        JFXButton button = new JFXButton("Close");
        button.setOnAction((ActionEvent event) -> {
            dialog.close();
        });;
        c.setActions(button);
        dialog.show();
    }*/
    
    public void advertencia(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }
    
    public void informacion(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }
    
    public void error(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

}
