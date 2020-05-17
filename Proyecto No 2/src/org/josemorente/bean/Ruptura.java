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
public class Ruptura {
    private Escrito puntero;
    private int llave;
    private Object object;

    public Ruptura() {
    }

    public Ruptura(Escrito puntero, int llave, Object object) {
        this.puntero = puntero;
        this.llave = llave;
        this.object = object;
    }

    /**
     * @return the puntero
     */
    public Escrito getPuntero() {
        return puntero;
    }

    /**
     * @param puntero the puntero to set
     */
    public void setPuntero(Escrito puntero) {
        this.puntero = puntero;
    }

    /**
     * @return the llave
     */
    public int getLlave() {
        return llave;
    }

    /**
     * @param llave the llave to set
     */
    public void setLlave(int llave) {
        this.llave = llave;
    }

    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
    }
    
}
