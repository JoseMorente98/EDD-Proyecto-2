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
public class ServidorEDD implements Serializable {
    private Usuario usuario;
    private int estado;
    private String ipEntrada;
    private String ipSalida;

    /**
     * 0 = Eliminar
     * 1 = Agregar
     * 2 = Actualizar
     */
    public ServidorEDD() {
    }

    public ServidorEDD(Usuario usuario, int estado, String ipEntrada, String ipSalida) {
        this.usuario = usuario;
        this.estado = estado;
        this.ipEntrada = ipEntrada;
        this.ipSalida = ipSalida;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the ipEntrada
     */
    public String getIpEntrada() {
        return ipEntrada;
    }

    /**
     * @param ipEntrada the ipEntrada to set
     */
    public void setIpEntrada(String ipEntrada) {
        this.ipEntrada = ipEntrada;
    }

    /**
     * @return the ipSalida
     */
    public String getIpSalida() {
        return ipSalida;
    }

    /**
     * @param ipSalida the ipSalida to set
     */
    public void setIpSalida(String ipSalida) {
        this.ipSalida = ipSalida;
    }

}
