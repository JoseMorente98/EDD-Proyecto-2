/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import org.josemorente.bean.Categoria;
import org.josemorente.interfaces.Comparador;

/**
 *
 * @author josem
 */
public class CategoriaControlador {
    private Categoria raiz;
    private int idCategoria;
    
    private CategoriaControlador() {
        raiz = null;
        idCategoria = 0;
    }
    
    public static CategoriaControlador getInstance() {
        return CategoriaControladorHolder.INSTANCE;
    }
    
    private static class CategoriaControladorHolder {
        private static final CategoriaControlador INSTANCE = new CategoriaControlador();
    }

    public Categoria getRaiz() {
        return raiz;
    }

    private Categoria rotacionII(Categoria n, Categoria n1) {
        n.setIzquierda(n1.getDerecha());
        n1.setDerecha(n);
        // actualización de los factores de equilibrio
        if (n1.getFactorEquilibrio() == -1) {// se cumple en la inserción
            n.setFactorEquilibrio(0);
            n1.setFactorEquilibrio(0);
        }
        else {
            n.setFactorEquilibrio(-1);
            n1.setFactorEquilibrio(1);
        }
        return n1;
    }
    
    private Categoria rotacionDD(Categoria n, Categoria n1) {
        n.setDerecha(n1.getIzquierda());
        n1.setIzquierda(n);
        // actualización de los factores de equilibrio
        if (n1.getFactorEquilibrio() == +1) {// se cumple en la inserción
            n.setFactorEquilibrio(0);
            n1.setFactorEquilibrio(0);
        }
        else {
            n.setFactorEquilibrio(+1);
            n1.setFactorEquilibrio(-1);
        }
        return n1;
    }
    
    private Categoria rotacionID(Categoria n, Categoria n1) {
        Categoria n2;
        n2 = (Categoria) n1.getDerecha();
        n.setIzquierda(n2.getDerecha());
        n2.setDerecha(n);
        n1.setDerecha(n2.getIzquierda());
        n2.setIzquierda(n1);
        // actualización de los factores de equilibrio
        if (n2.getFactorEquilibrio() == +1) {
            n1.setFactorEquilibrio(-1);
        } else {
            n1.setFactorEquilibrio(0);
        }
            
        if (n2.getFactorEquilibrio() == -1) {
            n.setFactorEquilibrio(1);
        } else {
            n.setFactorEquilibrio(0);
        }            
        n2.setFactorEquilibrio(0);
        return n2;
    }
    
    private Categoria rotacionDI(Categoria n, Categoria n1) {
        Categoria n2;
        n2 = (Categoria)n1.getIzquierda();
        n.setDerecha(n2.getIzquierda());
        n2.setIzquierda(n);
        n1.setIzquierda(n2.getDerecha());
        n2.setDerecha(n1);
        // actualización de los factores de equilibrio
        if (n2.getFactorEquilibrio() == +1) {
            n.setFactorEquilibrio(-1);
        } else {
            n.setFactorEquilibrio(0);
        }
        if (n2.getFactorEquilibrio() == -1) {
            n1.setFactorEquilibrio(1);
        } else {
            n1.setFactorEquilibrio(0);
        }
        n2.setFactorEquilibrio(0);
        return n2;
    }
    
    public void insertar(int carnetUsuario, String nombre) {
        idCategoria++;
        Logical h = new Logical(false);
        raiz = insertarAvl(raiz, carnetUsuario, nombre, h);
    }
    
    private Categoria insertarAvl(Categoria raiz, int carnetUsuario, String nombre, Logical h)
    {
        Categoria n1;
        if (raiz == null) {
            raiz = new Categoria(idCategoria, carnetUsuario, nombre);
            h.setLogical(true);
        } else if (nombre.compareTo(raiz.getNombre()) < 0) {
            Categoria iz;
            iz = insertarAvl((Categoria) raiz.getIzquierda(), carnetUsuario, nombre, h);
            raiz.setIzquierda(iz);
            // regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
            // decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.getFactorEquilibrio())
                {
                    case 1:
                        raiz.setFactorEquilibrio(0);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.setFactorEquilibrio(-1);
                        break;
                    case -1: // aplicar rotación a la izquierda
                        n1 = (Categoria)raiz.getIzquierda();
                        if (n1.getFactorEquilibrio() == -1)
                            raiz = rotacionII(raiz, n1);
                        else
                            raiz = rotacionID(raiz, n1);
                        h.setLogical(false);
                }
            }
        } else if (nombre.compareTo(raiz.getNombre()) > 0) {
            Categoria dr;
            dr = insertarAvl((Categoria) raiz.getDerecha(), carnetUsuario, nombre, h);
            raiz.setDerecha(dr);
            // regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
            // decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.getFactorEquilibrio())
                {
                    case 1:
                        n1 = (Categoria)raiz.getDerecha();
                        if (n1.getFactorEquilibrio() == +1)
                            raiz = rotacionDD(raiz, n1);
                        else
                            raiz = rotacionDI(raiz, n1);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.setFactorEquilibrio(+1);
                        break;
                    case -1: // aplicar rotación a la izquierda
                        raiz.setFactorEquilibrio(0);
                        h.setLogical(false);
                }
            }
        } else {
            System.out.println("No se puede ingresar repetidos.");
        }
        return raiz;
    }
    
    
    
}

class Logical {
    boolean v;
    
    public Logical (boolean f)
    {
        v = f;
    }
    
    public void setLogical(boolean f)
    {
        v = f;
    }
    
    public boolean booleanValue()
    {
        return v;
    }
}
