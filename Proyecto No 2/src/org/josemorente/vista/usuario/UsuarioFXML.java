/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.usuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class UsuarioFXML extends Application {
    public static Stage stage;
    
    private UsuarioFXML() {
    }
    
    public static UsuarioFXML getInstance() {
        return UsuarioFXMLHolder.INSTANCE;
    }
    
    private static class UsuarioFXMLHolder {

        private static final UsuarioFXML INSTANCE = new UsuarioFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("UsuarioFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void close() {
        stage.hide();
    }

}
