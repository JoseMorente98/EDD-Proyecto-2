/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.servidor;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.josemorente.controlador.CategoriaControlador;

/**
 *
 * @author josem
 */
public class Servidor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServidorFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //launch(args);
        CategoriaControlador.getInstance().insertar(201801237, "Infantil");
        CategoriaControlador.getInstance().insertar(201801237, "Miedo");
        CategoriaControlador.getInstance().insertar(201801237, "Aventuras");
        CategoriaControlador.getInstance().insertar(201801237, "Misterio");
        CategoriaControlador.getInstance().insertar(201801237, "Terror");
        CategoriaControlador.getInstance().insertar(201801237, "Amor");
        CategoriaControlador.getInstance().insertar(201801237, "Diario");
        CategoriaControlador.getInstance().generarGraphviz();
        /*System.out.println("IN ORDER");
        CategoriaControlador.getInstance().inOrder();
        System.out.println("PRE ORDER");
        CategoriaControlador.getInstance().preOrder();
        System.out.println("POS ORDER");
        CategoriaControlador.getInstance().posOrder();*/
        
        CategoriaControlador.getInstance().eliminar("Amor");
        System.out.println("IN ORDER");
        CategoriaControlador.getInstance().inOrder();
        
        //CategoriaControlador.getInstance().eliminar("Miedo");
        //System.out.println("IN ORDER");
        //CategoriaControlador.getInstance().inOrder();
        
    }
}
