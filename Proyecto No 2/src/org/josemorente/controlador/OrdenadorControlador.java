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
import org.josemorente.bean.Ordenador;

/**
 *
 * @author josem
 */
public class OrdenadorControlador {
    private Ordenador inicio;
    private ObservableList<Ordenador> observableList;
    
    private OrdenadorControlador() {
        observableList = FXCollections.observableArrayList();
        inicio = null;
    }
    
    public static OrdenadorControlador getInstance() {
        return OrdenadorControladorHolder.INSTANCE;
    }
    
    private static class OrdenadorControladorHolder {
        private static final OrdenadorControlador INSTANCE = new OrdenadorControlador();
    }
        
    /**
     * @param ip
     * @param puerto
     */
    public void agregar(String ip, int puerto){
        Ordenador ordenador = new Ordenador(ip, puerto);
        
        if (inicio == null) {
            inicio = ordenador;
        } else{
            Ordenador aux = inicio;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(ordenador);
        }
    }
    
    /**
     * @param ip
     */
    public void eliminar(String ip){
        if (buscar(ip)) {
            if (inicio.getIp().equals(ip)) {
                inicio = inicio.getSiguiente();
            } else{
                Ordenador aux = inicio;
                while(!aux.getSiguiente().getIp().equals(ip)){
                    aux = aux.getSiguiente();
                }
                Ordenador siguiente = aux.getSiguiente().getSiguiente();
                aux.setSiguiente(siguiente);  
            }
        }
    }
    
    /**
     * @param ip
     */
    public boolean buscar(String ip){
        Ordenador aux = inicio;
        boolean encontrado = false;
        while(aux != null && encontrado != true){
            if (ip.equals(aux.getIp())){
                encontrado = true;
            }
            else{
                aux = aux.getSiguiente();
            }
        }
        return encontrado;
    }

    /**
     * @return the observableList
     */
    public ObservableList<Ordenador> getObservableList() {
        observableList.clear();
        Ordenador aux = inicio;
        while(aux != null) {
            observableList.add(aux);
            aux = aux.getSiguiente();
        }        
        return observableList;
    }
    
    /**
     * @return graphviz 
     */
    public void generarGraphviz()  {
        String strGraphviz = "";
        int contador = 0;
        strGraphviz += "digraph grafica{\n" + "graph[label=\"Lista de nodos en red\", labelloc=t, fontsize=20, compound=true];";
        strGraphviz += "\nrankdir = LR;\nnode [shape=record];\nsplines=false; ";
        
        Ordenador aux = inicio;
        while(aux != null) {
            strGraphviz += "Nodo" + contador + " [label =\"" + "IP:  " + aux.getIp() + "\\nPUERTO: "+aux.getPuerto()+" \"]\n";
            aux = aux.getSiguiente();
            contador++;
        }
        
        for (int i = 0; i < contador - 1; i++)
	{
            strGraphviz += "Nodo" + i + "->Nodo" + (i+1) + "\n";
	}         
        strGraphviz += "}";
        
        File file = new File("ListaSimple.dot");
        if(file.exists() && !file.isDirectory()) {
            file.delete();
        }
        
        try {
            FileWriter fileWriter = new FileWriter("ListaSimple.dot");
            fileWriter.write(strGraphviz);
            fileWriter.close();
            Runtime.getRuntime().exec("dot -Tjpg -o ListaSimple.png ListaSimple.dot");
        } catch (IOException ex) {
            Logger.getLogger(OrdenadorControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
