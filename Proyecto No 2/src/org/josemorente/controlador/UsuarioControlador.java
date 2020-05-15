/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.josemorente.bean.Usuario;

/**
 *
 * @author josem
 */
public class UsuarioControlador {
    static final int M = 45;
    private Usuario [] tabla;
    private int noElementos;
    private ObservableList<Usuario> observableList;
    
    private UsuarioControlador() {
        observableList = FXCollections.observableArrayList();
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

    public ObservableList<Usuario> getObservableList() {
        Usuario aux = null;
        for (int i = 0; i < M; i++) {
            System.out.println(i);
            if (tabla[i] != null) {
                aux = tabla[i];
                observableList.add(aux);
                while (aux.getSiguiente() != null) {
                    observableList.add(aux);
                    aux = aux.getSiguiente();
                }
            }
        }
        return observableList;
    }
    
    private int dispersion(int x)
    {
        int v = x%M;
        return v;
    }
    
    public void insertar(int carnet, String nombre, String apellido, String carrera, String password)
    {
        int posicion = dispersion(carnet);
        Usuario nuevo = new Usuario(carnet, nombre, apellido, carrera, MD5Controlador.getInstance().Encriptar(password));
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
            else  //se retira el nodo
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
    
    public void actualizar(int carnet, String nombre, String apellido, String carrera, String password) {
        Usuario usuario = buscar(carnet);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCarrera(carrera);
        usuario.setPassword(MD5Controlador.getInstance().Encriptar(password));
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
    
    public void generarGraphviz() {
        String cuerpoGraphiz;
        
        cuerpoGraphiz = "digraph TablaHash {\n" +
        "\trankdir = LR; \n" + 
        "\tnode[shape = record, fontcolor = black, style = filled, color = honeydew2];\n" +
        "\tgraph[label = \"Tabla de Dispersión\", labelloc = t, fontsize = 20];\n" +
        "\tnodesep=0; \n"+
        "\tsplines=false; \n";

        String cuerpoHash = "\tnodoHash [label=\"";
        String listaSimpleHash = "\n";
        
        Usuario aux = null;
        for (int i = 0; i < M; i++) {
            if (tabla[i] != null) {
                aux = tabla[i];
                if(i==44) {
                    cuerpoHash += "<f" + i + ">"+
                        "\\lID: "+ (i+1) +
                        "\\lCarnet: "+ tabla[i].getCarnet() +
                        "\\lNombre: "+ tabla[i].getNombre() +
                        "\\lApellido: "+ tabla[i].getApellido() +
                        "\\lCarrera: "+ tabla[i].getCarrera() +    
                        "\\lContraseña: "+ tabla[i].getPassword();
                } else {
                    cuerpoHash += "<f" + i + ">"+
                        "\\lID: "+ (i+1) +
                        "\\lCarnet: "+ tabla[i].getCarnet() +
                        "\\lNombre: "+ tabla[i].getNombre() +
                        "\\lApellido: "+ tabla[i].getApellido() +
                        "\\lCarrera: "+ tabla[i].getCarrera() +    
                        "\\lContraseña: "+ tabla[i].getPassword() + "|";
                }
                Usuario temp = aux.getSiguiente();
                if(temp!=null) {
                    int contador = 1;
                    listaSimpleHash += "\tnodo_"+ i + "_" + contador+" [label=\""+
                            
                        "\\lCarnet: "+ temp.getCarnet() +
                        "\\lNombre: "+ temp.getNombre() +
                        "\\lApellido: "+ temp.getApellido() +
                        "\\lCarrera: "+ temp.getCarrera() +    
                        "\\lContraseña: "+ temp.getPassword() + "\"];\n";
                    //listaSimpleHash = listaSimpleHash + "node_" + i +"_" + temp.getCarnet() + " [label=\"<f0> Indice: " + i + "\\lNombre: " +  temp.getNombre() +"\\lApellido: " + temp.getApellido() + "\\lCarnet: " +temp.getCarnet() + "\\lCarrera: " + temp.getCarrera() + "\\lPassword: " + temp.getPassword() + "\" ];\n";
                    listaSimpleHash += "\tnodoHash:f"+ i + " -> nodo_" + i + "_" + contador +";\n";
                    temp = temp.getSiguiente();
                    while (temp != null) {
                        contador++;
                        
                        listaSimpleHash += "\t\tnodo_"+ i + "_" + contador+" [label=\""+
                            "\\lCarnet: "+ temp.getCarnet() +
                            "\\lNombre: "+ temp.getNombre() +
                            "\\lApellido: "+ temp.getApellido() +
                            "\\lCarrera: "+ temp.getCarrera() +    
                            "\\lContraseña: "+ temp.getPassword() + "\"];\n";
                        if (temp != null) {
                            listaSimpleHash += "\tnodo_" + i + "_" + (contador-1) + ":alpha -> nodo_" + i + "_" + contador + ";\n";
                        }
                        temp = temp.getSiguiente();
                    }
                }
                
            } else {
                if(i==44) {
                    cuerpoHash = cuerpoHash + "<f" + i + "> null";
                } else {
                    cuerpoHash = cuerpoHash + "<f" + i + "> null |";
                }
            }
        }
        
        cuerpoHash = cuerpoHash + "\"];" + listaSimpleHash + "\n";
        cuerpoGraphiz = cuerpoGraphiz + cuerpoHash + "}";
        
        try {
            File file = new File("TablaDeDispersion.dot");
            if(file.exists() && !file.isDirectory()) { 
                file.delete();
            }
            FileWriter fileWriter;
            fileWriter = new FileWriter("TablaDeDispersion.dot");
            fileWriter.write(cuerpoGraphiz);
            fileWriter.close();
            Runtime.getRuntime().exec("dot -Tjpg -o TablaDeDispersion.png TablaDeDispersion.dot");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
