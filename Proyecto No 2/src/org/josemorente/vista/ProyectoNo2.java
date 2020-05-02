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
import org.josemorente.controlador.UsuarioControlador;

/**
 *
 * @author josem
 */
public class ProyectoNo2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        UsuarioControlador.getInstance().insertar(201800001, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800002, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800032, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800033, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800034, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().mostrar();
        UsuarioControlador.getInstance().generarGraphviz();
    }
    
}
