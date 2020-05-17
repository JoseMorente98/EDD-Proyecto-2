/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.categoria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class CategoriaFXML extends Application {
     
    private CategoriaFXML() {
    }
    
    public static CategoriaFXML getInstance() {
        return CategoriaFXMLHolder.INSTANCE;
    }
    
    private static class CategoriaFXMLHolder {

        private static final CategoriaFXML INSTANCE = new CategoriaFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CategoriaFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
