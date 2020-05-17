/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.bean;

import java.io.Serializable;
import org.josemorente.controlador.EscritoControlador;
import org.josemorente.controlador.LibroControlador;

/**
 *
 * @author josem
 */
public class Categoria implements Serializable{
    private int id;
    private int carnetUsuario;
    private String nombre;
    private int factorEquilibrio;
    private Categoria izquierda;
    private Categoria derecha;
    private EscritoControlador escritoControlador;

    public Categoria() {
    }

    public Categoria(int id, int carnetUsuario, String nombre) {
        this.id = id;
        this.carnetUsuario = carnetUsuario;
        this.nombre = nombre;
        this.factorEquilibrio = 0;
        this.izquierda = null;
        this.derecha = null;
        this.escritoControlador = new EscritoControlador();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the carnetUsuario
     */
    public int getCarnetUsuario() {
        return carnetUsuario;
    }

    /**
     * @param carnetUsuario the carnetUsuario to set
     */
    public void setCarnetUsuario(int carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param name the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the factorEquilibrio
     */
    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    /**
     * @param factorEquilibrio the factorEquilibrio to set
     */
    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }

    /**
     * @return the izquierda
     */
    public Categoria getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(Categoria izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the derecha
     */
    public Categoria getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(Categoria derecha) {
        this.derecha = derecha;
    }
        
    /**
     * @return the escritoControlador
     */
    public EscritoControlador getEscritoControlador() {
        return escritoControlador;
    }

    /**
     * @param escritoControlador the escritoControlador to set
     */
    public void setEscritoControlador(EscritoControlador escritoControlador) {
        this.escritoControlador = escritoControlador;
    }
    
    /**
     * GRAFICO 
     */
    public String getArbol()
    {
	String bodyGraphiz;
	if (izquierda == null && derecha == null) {
            bodyGraphiz = "\tObject" + id + " [ label=\""+
                            "\\"+ nombre +
                            "\\n"+ carnetUsuario + "\"];\n";
	}
        else {
            bodyGraphiz = "\tObject" + id + " [ label=\""+
                            "\\"+ nombre +
                            "\\n"+ carnetUsuario + "\"];\n";
	}
	if (izquierda != null) {
            bodyGraphiz += izquierda.getArbol() +
            "Object" + id + "->Object" + izquierda.getId() + "\n";
	}
	if (derecha != null) {
            bodyGraphiz += derecha.getArbol() +
                "Object" + id + "->Object" + derecha.getId() + "\n";
	}
	return bodyGraphiz;
    }
    
    @Override
    public String toString() {
        return "CATEGORIA {\n"+
            "\tid: " + id + "\n"+
            "\tusuario: " + carnetUsuario + "\n"+
            "\tnombre: " + nombre + "\n"+
            "}";
    }    

}
