/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

import java.io.Serializable;

/**
 *
 * @author josem
 */
public class Ordenador implements Serializable{
    private String ip;    
    private int puerto;
    private Ordenador siguiente;

    public Ordenador() {
    }

    public Ordenador(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
        this.siguiente = null;
    }
    
    public Ordenador(String ip) {
        this.ip = ip;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the siguiente
     */
    public Ordenador getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Ordenador siguiente) {
        this.siguiente = siguiente;
    }
    
}
