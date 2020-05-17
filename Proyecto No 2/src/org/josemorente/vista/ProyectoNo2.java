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
        CategoriaControlador.getInstance().insertarLibro(1, "Titulo", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(2, "Titulo 1", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(3, "Titulo 2", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(4, "Titulo 3 ", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(5, "Titulo 4", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(6, "Titulo 5", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(7, "Titulo 6", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(8, "Titulo 7", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(9, "Titulo 8", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(10, "Titulo 9", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(11, "Titulo 10", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(12, "Titulo 11", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(13, "Titulo 12", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(14, "Titulo 13", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(15, "Titulo 14", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(16, "Titulo 15", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(17, "Titulo 16", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(18, "Titulo 17", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(19, "Titulo 18", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(20, "Titulo 19", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(21, "Titulo 20", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(22, "Titulo 21", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        
        
        
        CategoriaControlador.getInstance().inOrder();
        /*CategoriaControlador.getInstance().insertarLibro(0, "Titulo 2", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 3 ", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 4", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 5", "123", "1231", "12313", "1231", "123132", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(0, "Titulo 6", "123", "1231", "12313", "1231", "123132", "12311", 0);*/
        CategoriaControlador.getInstance().generarGraphvizLibro("Miedo");
        CategoriaControlador.getInstance().getObservableListObra();
        //CategoriaControlador.getInstance().generarGraphviz();
        
    }
    
}
