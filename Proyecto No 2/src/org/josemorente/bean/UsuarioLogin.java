/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

/**
 *
 * @author josem
 */
public class UsuarioLogin {
    private static int carnet;
    private static String nombre;
    private static String apellido;

    /**
     * @return the carnet
     */
    public static int getCarnet() {
        return carnet;
    }

    /**
     * @param aCarnet the carnet to set
     */
    public static void setCarnet(int aCarnet) {
        carnet = aCarnet;
    }

    /**
     * @return the nombre
     */
    public static String getNombre() {
        return nombre;
    }

    /**
     * @param aNombre the nombre to set
     */
    public static void setNombre(String aNombre) {
        nombre = aNombre;
    }

    /**
     * @return the apellido
     */
    public static String getApellido() {
        return apellido;
    }

    /**
     * @param aApellido the apellido to set
     */
    public static void setApellido(String aApellido) {
        apellido = aApellido;
    }
    
}
