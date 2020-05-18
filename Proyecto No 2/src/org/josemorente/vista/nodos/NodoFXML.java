/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.nodos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class NodoFXML extends Application{
    
    private NodoFXML() {
    }
    
    public static NodoFXML getInstance() {
        return NodoFXMLHolder.INSTANCE;
    }
    
    private static class NodoFXMLHolder {

        private static final NodoFXML INSTANCE = new NodoFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NodoFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
