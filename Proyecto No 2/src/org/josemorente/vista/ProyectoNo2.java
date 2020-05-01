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
        UsuarioControlador.getInstance().insertar(201800003, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800004, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800005, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800006, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800007, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800008, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800009, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800010, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800011, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800012, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800013, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800014, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800015, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800016, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800017, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800018, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800019, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800020, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800021, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800022, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800023, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800024, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800025, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800026, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800027, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800028, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800029, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800030, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800031, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800032, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800033, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800034, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800035, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800036, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800037, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800038, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800039, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800040, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800041, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800042, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800043, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800044, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800045, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800046, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800047, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800048, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201800049, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().mostrar();
        
    }
    
}
