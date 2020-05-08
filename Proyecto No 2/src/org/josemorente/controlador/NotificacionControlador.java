/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

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

}
