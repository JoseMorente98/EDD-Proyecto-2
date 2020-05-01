/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import org.josemorente.bean.Usuario;

/**
 *
 * @author josem
 */
public class UsuarioControlador {
    static final int M = 45;
    private Usuario [] tabla;
    private int noElementos;
    
    private UsuarioControlador() {
        tabla = new Usuario[M];
        for (int k = 0; k < M; k++)
            tabla[k] = null;
        noElementos = 0;
    }
    
    public static UsuarioControlador getInstance() {
        return UsuarioControladorHolder.INSTANCE;
    }
    
    private static class UsuarioControladorHolder {
        private static final UsuarioControlador INSTANCE = new UsuarioControlador();
    }
    
    
    private int dispersion(int x)
    {
        int v = x%M;
        return v;
    }
    
    public void insertar(int carnet, String nombre, String apellido, String carrera, String password)
    {
        int posicion = dispersion(carnet);
        Usuario nuevo = new Usuario(carnet, nombre, apellido, carrera, password);
        nuevo.setSiguiente(tabla[posicion]);
        tabla[posicion] = nuevo;
        noElementos++;
    }
    
    boolean conforme(Usuario a) {
        return a==null;
    }
    
    public void eliminar(int codigo)
    {
        int posicion = dispersion(codigo);
        if (tabla[posicion] != null) // no está vacía
        {
            Usuario anterior, actual;
            anterior = null;
            actual = tabla[posicion];
            while ((actual.getSiguiente() != null) &&
            actual.getCarnet() != codigo)
            {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            if (actual.getCarnet() != codigo) {
                System.out.println("No se encuentra en la tabla el socio " + codigo);
            }
            else if (conforme(actual))  //se retira el nodo
            {
                if (anterior == null) {
                    tabla[posicion] = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                actual = null;
                noElementos--;
            }
        }
    }
    
    
    public Usuario buscar(int codigo)
    {
        Usuario aux = null;
        int posicion = dispersion(codigo);
        if (tabla[posicion] != null) {
            aux = tabla[posicion];
            while ((aux.getSiguiente() != null) && aux.getCarnet() != codigo)
                aux = aux.getSiguiente();

            if (aux.getCarnet() != codigo)
                aux = null;
        }
        return aux;
    }
    
    public void mostrar()
    {
        Usuario aux = null;
        for (int i = 0; i < M; i++) {
            System.out.println(i);
            if (tabla[i] != null) {
                aux = tabla[i];
                System.out.println(aux);
                while (aux.getSiguiente() != null) {
                    System.out.println(aux.getSiguiente());
                    aux = aux.getSiguiente();
                }
            }
        }
    }
}
