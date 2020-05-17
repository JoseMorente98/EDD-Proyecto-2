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
public class Libro {
    public int tamano;
    public int hojas;
    public int[] llave;
    public Obra[] libro;
    public Libro[] punteros;
    private static int numeroNodo = 1;
    private ArrayList<Obra> arrayList = new ArrayList();
    
    public Libro() {
    }

    public Libro(int tamano) {
        this.tamano = tamano;
        this.hojas = 0;
        this.llave = new int[2*tamano+1];
        this.libro = new Obra[2*tamano+1];
        this.punteros = new Libro[(2*tamano)+2];
    }

    public Libro(int tamano, int llave, Obra obra) {
        this(tamano);
        this.hojas = 1;
        this.llave[0] = llave;
        this.libro[0] = obra;
    }

    /**
     * @return the tamano
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * @parrayListam tamano the tamano to set
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
     * @parrayListam hojas the hojas to set
     */
    public void setHojas(int hojas) {
        this.hojas = hojas;
    }

    /**
     * @return the llave
     */
    public int[] getLlave() {
        return llave;
    }

    /**
     * @parrayListam llave the llave to set
     */
    public void setLlave(int[] llave) {
        this.llave = llave;
    }

    /**
     * @return the libro
     */
    public Obra[] getLibro() {
        return libro;
    }

    /**
     * @parrayListam libro the libro to set
     */
    public void setLibro(Obra[] libro) {
        this.libro = libro;
    }

    /**
     * @return the punteros
     */
    public Libro[] getPunteros() {
        return punteros;
    }

    /**
     * @parrayListam punteros the punteros to set
     */
    public void setPunteros(Libro[] punteros) {
        this.punteros = punteros;
    }

    /**
     * @return the numeroNodo
     */
    public static int getNumeroNodo() {
        return numeroNodo;
    }

    /**
     * @parrayListam aNumeroNodo the numeroNodo to set
     */
    public static void setNumeroNodo(int aNumeroNodo) {
        numeroNodo = aNumeroNodo;
    }
    
    /**
     * @return the node
     */
    public String getNombreNodo() {
        return "Nodo" + this.hashCode();
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
    
    /**
     * @return the dot
     */
    public String getPunto(  )  {
        StringBuilder stringBuilder = new StringBuilder();
        //String strNodo = "";
        //strNodo += getNombreNodo();
        stringBuilder.append( getNombreNodo() );
        //strNodo += "[label=\"<P0>";
        stringBuilder.append("[label=\"<P0>");
        for( int i = 0; i < getHojas(); i++ ) {
            Obra book = (Obra)libro[i];
            System.out.println(book);
            if(book!=null) {
                stringBuilder.append( "| ISBN:" +  llave[i] + "\\lNombre:" + book.getTitulo() );
            //strNodo += "| ISBN:" +  llave[i] + "\\lNombre:" + book.getTitulo();
            //strNodo += "|<P" + (i+1) + ">";
            stringBuilder.append( "|<P" + (i+1) + ">" );       
            }
                     
        }
        
        stringBuilder.append("\"];\n");
        //strNodo += "\"];\n";
        for( int i = 0; i <= getHojas() ; i++ ) {
            if( punteros[i] != null )   {
                stringBuilder.append( punteros[i].getPunto() );
                stringBuilder.append( getNombreNodo() + ":P" + i + " -> " + punteros[i].getNombreNodo()+ ";\n" );
                //strNodo += punteros[i].getPunto();
                //strNodo += getNombreNodo() + ":P" + i + " -> " + punteros[i].getNombreNodo()+ ";\n";
            }
        }
        //System.out.println(strNodo);
        return stringBuilder.toString();
    }
    
    
    public void obtenerUsuario(int x) {  
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.llave.length; k++) {            
            if (this.llave[k] != 0) {
                j++;
            }
        }
        for (i = 0; i < j; i++) {   
            if (this.punteros[0] != null) { 
                punteros[i].obtenerUsuario(x); 
            } 
            //System.out.print(keys[i] + " "); 
            if (((Obra)libro[i]).getCarnetUsuario() == x) {
                getArrayList().add((Obra)libro[i]);
            }
        } 
  
        if (this.punteros[0] != null) { 
            punteros[i].obtenerUsuario(x); 
        }
    }
    
    public void obtenerTodo() {  
        System.out.println("OBTENER TODO");
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.llave.length; k++) {    
            System.out.println(this.llave[k]);
            if (this.llave[k] != 0) {
                System.out.println("J" + j);
                j++;
            }
        }
        System.out.println("JOTA " + j);
        for (i = 0; i < j; i++) {   
            System.out.println("THIS.PUNTEROS[0] dentro" + this.punteros[0]);
            System.out.println("I dentro: " + i);
            if (this.punteros[i] != null) { 
                this.punteros[i].obtenerTodo(); 
            }
            System.out.println("OBRA" + libro[i]);
            getArrayList().add((Obra)libro[i]);
        } 
  
        System.out.println("THIS.PUNTEROS[0]" + this.punteros[0]);
        System.out.println("I: " + i);
        if (this.punteros[0] != null) { 
            this.punteros[i].obtenerTodo(); 
        }
    }
    
    
    public boolean searchKey(int x) {   
        int i = 0; 
        int j = 0;
        for (int k = 0; k < this.llave.length; k++) {
            
            if (this.llave[k] != 0) {
                j++;
            }
        }
        for (i = 0; i < j; i++) { 
  
            if (this.punteros[0] != null) { 
                punteros[i].obtenerUsuario(x); 
            } 
            if ((int)llave[i] == x) {
                return true;
            }
        } 
        if (this.punteros[0] != null) { 
            punteros[i].obtenerUsuario(x); 
        }
        return false;
    }
    
}
