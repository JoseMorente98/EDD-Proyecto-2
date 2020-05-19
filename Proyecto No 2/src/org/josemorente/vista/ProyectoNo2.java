/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import org.josemorente.bean.Categoria;
import org.josemorente.controlador.CadenaBloqueControlador;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.JSONControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.servidor.ServidorFXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author josem
 */
public class ProyectoNo2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //ServidorFXML.getInstance().start(stage);
        FXMLDocument.getInstance().start(stage);
        //UsuarioFXML.getInstance().start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*CadenaBloqueControlador.getInstance().agregar(new JSONArray(), "2014801237");
        CadenaBloqueControlador.getInstance().agregar(new JSONArray(), "2014801237");
        CadenaBloqueControlador.getInstance().agregar(new JSONArray(), "2014801237");
        CadenaBloqueControlador.getInstance().generarJSON();*/
        launch(args);
        /*JSONControlador.getInstance().setJsonBody(new JSONObject());        
        JSONControlador.getInstance().setJsonObject(new JSONObject());
        JSONControlador.getInstance().setjSONArray(new JSONArray());

        Categoria c = new Categoria(0, 201801237, "Miedo");
        JSONControlador.getInstance().generarJSON("Categoria", "1", c);
        Categoria c1 = new Categoria(0, 201801237, "Aventura");
        JSONControlador.getInstance().generarJSON("Categoria", "1", c1);
        Categoria c2 = new Categoria(0, 201801237, "Terror");
        JSONControlador.getInstance().generarJSON("Categoria", "1", c2);
        System.out.println(JSONControlador.getInstance().getjSONArray());
        /*OrdenadorControlador.getInstance().agregar("192.168.1.1", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.2", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.3", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.4", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.5", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.6", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.7", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.8", 8000);
        CategoriaControlador.getInstance().agregar(212, "Miedo");
        CategoriaControlador.getInstance().agregar(0, "AMOR");
        CategoriaControlador.getInstance().agregar(0, "TERROR");
        CategoriaControlador.getInstance().agregar(312312, "ABC");
        CategoriaControlador.getInstance().agregar(0, "DEF");
        CategoriaControlador.getInstance().agregar(0, "GHJ");
        CategoriaControlador.getInstance().insertarLibro(1, "Titulo", "autor", "editorial", "12313", "edicion", "Miedo", "es", 0);
        CategoriaControlador.getInstance().insertarLibro(2, "Titulo 1", "123", "1231", "12313", "1231", "Miedo", "12311", 2015);
        CategoriaControlador.getInstance().insertarLibro(3, "Titulo 2", "123", "1231", "12313", "1231", "AMOR", "12311", 2020);
        CategoriaControlador.getInstance().insertarLibro(4, "Titulo 3 ", "123", "1231", "12313", "1231", "AMOR", "12311", 2121);
        CategoriaControlador.getInstance().insertarLibro(5, "Titulo 4", "123", "1231", "12313", "1231", "GHJ", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(6, "Titulo 5", "123", "editorial", "12313", "1231", "AMOR", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(7, "Titulo 6", "123", "1231", "12313", "1231", "TERROR", "es", 0);
        CategoriaControlador.getInstance().insertarLibro(8, "Titulo 7", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(9, "Titulo 8", "autor", "1231", "12313", "1231", "TERROR", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(10, "Titulo 9", "123", "editorial", "12313", "1231", "GHJ", "es", 0);
        CategoriaControlador.getInstance().insertarLibro(11, "Titulo 10", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(12, "Titulo 11", "123", "1231", "12313", "1231", "TERROR", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(13, "Titulo 12", "autor", "editorial", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(14, "Titulo 13", "123", "1231", "12313", "1231", "TERROR", "ingle", 0);
        CategoriaControlador.getInstance().insertarLibro(15, "Titulo 14", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(16, "Titulo 15", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(17, "Titulo 16", "autor", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(18, "Titulo 17", "123", "1231", "12313", "1231", "DEF", "es", 0);
        CategoriaControlador.getInstance().insertarLibro(19, "Titulo 18", "123", "editorial", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(20, "Titulo 19", "123", "1231", "12313", "1231", "DEF", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(21, "Titulo 20", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
        CategoriaControlador.getInstance().insertarLibro(22, "Titulo 21", "123", "1231", "12313", "1231", "DEF", "12311", 0);
        UsuarioControlador.getInstance().insertar(201801237, "José", "Morente", "Ingenieria en Ciencias y Sistemas", "12345");
        launch(args);*/
        
        /*OrdenadorControlador.getInstance().agregar("192.168.1.1", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.2", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.3", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.4", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.5", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.6", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.7", 8000);
        OrdenadorControlador.getInstance().agregar("192.168.1.8", 8000);
        OrdenadorControlador.getInstance().eliminar("192.168.1.5");
        OrdenadorControlador.getInstance().generarGraphviz();
        
        /*CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().agregar();
        CadenaBloqueControlador.getInstance().generarGraphviz();
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
        /*CategoriaControlador.getInstance().insertarLibro(1, "Titulo", "123", "1231", "12313", "1231", "Miedo", "12311", 0);
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
        /*CategoriaControlador.getInstance().generarGraphvizLibro("Miedo");
        CategoriaControlador.getInstance().getObservableListObra();*/
        //CategoriaControlador.getInstance().generarGraphviz();
        
    }
    
}
