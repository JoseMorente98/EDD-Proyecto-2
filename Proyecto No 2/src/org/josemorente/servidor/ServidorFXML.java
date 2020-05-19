/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.servidor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class ServidorFXML extends Application {
    
    private ServidorFXML() {
    }
    
    public static ServidorFXML getInstance() {
        return ServidorFXMLHolder.INSTANCE;
    }
    
    private static class ServidorFXMLHolder {
        private static final ServidorFXML INSTANCE = new ServidorFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServidorFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
