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
public class Separador {
    private Libro puntero;
    private int llave;
    private Obra obra;

    public Separador() {
    }

    public Separador(Libro puntero, int llave, Obra obra) {
        this.puntero = puntero;
        this.llave = llave;
        this.obra = obra;
    }

    /**
     * @return the puntero
     */
    public Libro getPuntero() {
        return puntero;
    }

    /**
     * @param puntero the puntero to set
     */
    public void setPuntero(Libro puntero) {
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
    
}
