/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista;

import javafx.application.Application;
import javafx.stage.Stage;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.usuario.UsuarioFXML;

/**
 *
 * @author josem
 */
public class ProyectoNo2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        UsuarioFXML.getInstance().start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*launch(args);
        UsuarioControlador.getInstance().insertar(201801237, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201801237, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201801238, "Jose", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201801234, "Angel", "Lux", "Electronica", "12345");
        UsuarioControlador.getInstance().insertar(201801248, "Rafael", "Morente", "Sistemas", "12345");
        UsuarioControlador.getInstance().insertar(201801254, "Antonio", "Lux", "Electronica", "12345");
        System.out.println("BUSCAR");
        System.out.println(UsuarioControlador.getInstance().buscar(201801237));
        System.out.println("ELIMINAR: 201801237");
        UsuarioControlador.getInstance().eliminar(201801237);
        System.out.println("BUSCAR");
        System.out.println(UsuarioControlador.getInstance().buscar(201801237));*/
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().inOrder();
        /*CategoriaControlador.getInstance().insertarLibro(0, "Titulo 2", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 3 ", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 4", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 5", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 6", "123", "1231", "12313", "1231", "123132", "12311", 0);*/
        CategoriaControlador.getInstance().getObservableListObra();
    }
    
}
