/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.josemorente.vista.usuario.UsuarioFXML;

/**
 *
 * @author josem
 */
public class FXMLDocument extends Application {
    public static Stage stage;

    private FXMLDocument() {
    }
    
    public static FXMLDocument getInstance() {
        return FXMLDocumentHolder.INSTANCE;
    }
    
    private static class FXMLDocumentHolder {

        private static final FXMLDocument INSTANCE = new FXMLDocument();
    }
        
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void close() {
        stage.hide();
    }
}
