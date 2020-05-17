/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

import java.util.ArrayList;

/**
 *
 * @author josem
 */
public class CadenaBloque {
    private int index;
    private String timeStamp;
    private ArrayList data;
    private String nonce;
    private String previousHash;
    private String hash;
    private String ip;
    private CadenaBloque siguiente;    
    private CadenaBloque anterior;

    public CadenaBloque() {
    }

    public CadenaBloque(int index, String timeStamp, ArrayList data, String nonce, String previousHash, String hash, String ip) {
        this.index = index;
        this.timeStamp = timeStamp;
        this.data = data;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.hash = hash;
        this.ip = ip;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public CadenaBloque(String timeStamp, ArrayList data, String ip) {
        this.timeStamp = timeStamp;
        this.data = data;
        this.ip = ip;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the data
     */
    public ArrayList getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList data) {
        this.data = data;
    }

    /**
     * @return the nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * @param nonce the nonce to set
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * @return the previousHash
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * @param previousHash the previousHash to set
     */
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
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
     * @return the siguiente
     */
    public CadenaBloque getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(CadenaBloque siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public CadenaBloque getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(CadenaBloque anterior) {
        this.anterior = anterior;
    }
    
}
