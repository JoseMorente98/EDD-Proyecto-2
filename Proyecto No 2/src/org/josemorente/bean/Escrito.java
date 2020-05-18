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
public class Escrito implements Serializable {
    public int tamano;
    public int hojas;
    public int[] llaves;
    public Object[] obras;
    public Escrito[] punteros;
    static ArrayList arrayList = new ArrayList();
    
    

    public Escrito() {
    }
    
    public Escrito(int tamano) {
        this.tamano = tamano;
        this.hojas = 0;
        this.llaves = new int[2 * tamano + 1];
        this.obras = new Object[2 * tamano + 1];
        this.punteros = new Escrito[(2 * tamano) + 2];
    }

    public Escrito(int tamano, int llaves, Object obras) {
        this(tamano);
        this.hojas = 1;
        this.llaves[0] = llaves;
        this.obras[0] = obras;
    }
    
    public String toDot(  )  {
        String strGrapvhiz = "\t";
        strGrapvhiz += getNombreNodo();
        strGrapvhiz += "[label=\"<P0>";
        for( int i = 0; i < getHojas(); i++ ) {
            Obra book = (Obra)getObras()[i];          
            strGrapvhiz += "| ISBN:" +  getLlaves()[i] + "\\nNombre:" + book.getTitulo();
            strGrapvhiz += "|<P" + (i+1) + ">";
        }
        
        strGrapvhiz += "\"];\n";
        
        for( int i = 0; i <= getHojas() ; i++ ) {
            if( getPunteros()[i] != null )   {
                strGrapvhiz += getPunteros()[i].toDot();
                strGrapvhiz += getNombreNodo() + ":P" + i + " -> " + getPunteros()[i].getNombreNodo() + ";\n";
            }
        }
        
        return strGrapvhiz;
    }
    
    
    public void obtenerPorCarnet(int carnet) { 
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.getLlaves().length; k++) {            
            if (this.getLlaves()[k] != 0) {
                j++;
            }
        }
        for (i = 0; i < j; i++) { 
            if (this.getPunteros()[0] != null) { 
                getPunteros()[i].obtenerPorCarnet(carnet); 
            }
            
            if (((Obra)getObras()[i]).getCarnetUsuario() == carnet) {
                arrayList.add((Obra)getObras()[i]);
            }
        } 
  
        if (this.getPunteros()[0] != null) { 
            getPunteros()[i].obtenerPorCarnet(carnet); 
        }
    }
    
    public void obtenerTodo() { 
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.getLlaves().length; k++) {
            
            if (this.getLlaves()[k] != 0) {
                j++;
            }
        }
        for (i = 0; i < j; i++) { 
            if (this.getPunteros()[0] != null) { 
                getPunteros()[i].obtenerTodo(); 
            }
            arrayList.add((Obra)getObras()[i]);
        } 
  
        if (this.getPunteros()[0] != null) { 
            getPunteros()[i].obtenerTodo(); 
        }
    }
    
    public boolean searchKey(int x) { 
  
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.getLlaves().length; k++) {
            
            if (this.getLlaves()[k] != 0) {
                j++;
            }
        }
        for (i = 0; i < j; i++) { 
  
            if (this.getPunteros()[0] != null) { 
                getPunteros()[i].obtenerPorCarnet(x); 
            } 
            if ((int)getLlaves()[i] == x) {
                return true;
            }
        } 
        if (this.getPunteros()[0] != null) { 
            getPunteros()[i].obtenerPorCarnet(x); 
        }
        return false;
    }
        
    /**
     * @return the tamano
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * @param tamano the tamano to set
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     * @return the hojas
     */
    public int getHojas() {
        return hojas;
    }

    /**
     * @param hojas the hojas to set
     */
    public void setHojas(int hojas) {
        this.hojas = hojas;
    }
    
    /**
     * @return the nombre
     */
    public String getNombreNodo() {
        return "Nodo" + this.hashCode();
    }
    
    /**
     * @return the llaves
     */
    public int[] getLlaves() {
        return llaves;
    }

    /**
     * @param llaves the llaves to set
     */
    public void setLlaves(int[] llaves) {
        this.llaves = llaves;
    }
    
    /**
     * @return the obras
     */
    public Object[] getObras() {
        return obras;
    }

    /**
     * @param obras the obras to set
     */
    public void setObras(Object[] obras) {
        this.obras = obras;
    }
    
    /**
     * @return the punteros
     */
    public Escrito[] getPunteros() {
        return punteros;
    }

    /**
     * @param punteros the punteros to set
     */
    public void setPunteros(Escrito[] punteros) {
        this.punteros = punteros;
    }

    /**
     * @return the arrayList
     */
    public ArrayList getArrayList() {
        return arrayList;
    }

    /**
     * @param aArrayList the arrayList to set
     */
    public void setArrayList(ArrayList aArrayList) {
        arrayList = aArrayList;
    }
}
