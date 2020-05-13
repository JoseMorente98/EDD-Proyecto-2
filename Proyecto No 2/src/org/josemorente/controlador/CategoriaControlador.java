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
    
    public void insertar (int carnetUsuario, String nombre)throws Exception
    {
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
        }
        return null;
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
