/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author josem
 */
public class ServidorEDD implements Serializable {
    private Usuario usuario;
    private Obra obra;
    private Categoria categoria;
    private CadenaBloque cadenaBloque;
    private Ordenador ordenador;
    private int estado;
    private String ip;
    private int puerto;
    private ArrayList<Obra> arrayListObra = new ArrayList<>();
    private ArrayList<Usuario> arrayListUsuario = new ArrayList<>();

    /**
     * 0 = Eliminar
     * 1 = Agregar
     * 2 = Actualizar
     */
    public ServidorEDD() {
    }

    public ServidorEDD(Usuario usuario, int estado, String ip, int puerto) {
        this.usuario = usuario;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public ServidorEDD(Obra obra, int estado, String ip, int puerto) {
        this.obra = obra;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public ServidorEDD(Categoria categoria, int estado, String ip, int puerto) {
        this.categoria = categoria;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public ServidorEDD(CadenaBloque cadenaBloque, int estado, String ip, int puerto) {
        this.cadenaBloque = cadenaBloque;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public ServidorEDD(Ordenador ordenador, int estado, String ip, int puerto) {
        this.ordenador = ordenador;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public ServidorEDD(int estado, String ip, int puerto) {
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
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
     * @return the obra
     */
    public Obra getObra() {
        return obra;
    }

    /**
     * @param obra the obra to set
     */
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the cadenaBloque
     */
    public CadenaBloque getCadenaBloque() {
        return cadenaBloque;
    }

    /**
     * @param cadenaBloque the cadenaBloque to set
     */
    public void setCadenaBloque(CadenaBloque cadenaBloque) {
        this.cadenaBloque = cadenaBloque;
    }

    /**
     * @return the ordenador
     */
    public Ordenador getOrdenador() {
        return ordenador;
    }

    /**
     * @param ordenador the ordenador to set
     */
    public void setOrdenador(Ordenador ordenador) {
        this.ordenador = ordenador;
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
     * @return the arrayListObra
     */
    public ArrayList<Obra> getArrayListObra() {
        return arrayListObra;
    }

    /**
     * @param arrayListObra the arrayListObra to set
     */
    public void setArrayListObra(ArrayList<Obra> arrayListObra) {
        this.arrayListObra = arrayListObra;
    }

    /**
     * @return the arrayListUsuario
     */
    public ArrayList<Usuario> getArrayListUsuario() {
        return arrayListUsuario;
    }

    /**
     * @param arrayListUsuario the arrayListUsuario to set
     */
    public void setArrayListUsuario(ArrayList<Usuario> arrayListUsuario) {
        this.arrayListUsuario = arrayListUsuario;
    }

}
