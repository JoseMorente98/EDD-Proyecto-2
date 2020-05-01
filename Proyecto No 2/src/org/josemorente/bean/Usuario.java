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
public class Usuario {
    private int carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;
    private Usuario siguiente;

    public Usuario() {
    }

    public Usuario(int carnet, String nombre, String apellido, String carrera, String password) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        this.siguiente = null;
    }

    /**
     * @return the carnet
     */
    public int getCarnet() {
        return carnet;
    }

    /**
     * @param carnet the carnet to set
     */
    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the siguiente
     */
    public Usuario getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Usuario siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "USUARIO {\n"+
            "\tcarnet: " + carnet + "\n"+
            "\tnombre: " + nombre + "\n"+
            "\tapellido: " + apellido + "\n"+
            "\tcarrera: " + carrera + "\n"
        + "}";
    }
    
}
