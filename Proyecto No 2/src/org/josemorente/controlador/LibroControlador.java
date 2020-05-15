/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

/**
 *
 * @author josem
 */
public class LibroControlador {
    
    private LibroControlador() {
    }
    
    public static LibroControlador getInstance() {
        return LibroControladorHolder.INSTANCE;
    }
    
    private static class LibroControladorHolder {

        private static final LibroControlador INSTANCE = new LibroControlador();
    }
}
