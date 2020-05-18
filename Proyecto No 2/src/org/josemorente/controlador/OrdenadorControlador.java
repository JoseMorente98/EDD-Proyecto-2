/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.josemorente.bean.Cliente;
import org.josemorente.bean.Ordenador;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;

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
     * AGREGAR SERVIDOR 
     */
    public void agregarOrdenadorServidor(String ip, int puerto) {
        try {
            Ordenador ordenador = new Ordenador(ip, puerto);
            Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
            ServidorEDD servidorEDD = new ServidorEDD(ordenador, 1, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(servidorEDD);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ELIMINAR SERVIDOR 
     */
    public void eliminarOrdenadorServidor(String ip) {
        try {
            Ordenador ordenador = new Ordenador(ip);
            Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
            ServidorEDD servidorEDD = new ServidorEDD(ordenador, 0, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(servidorEDD);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * @param ip
     * @param puerto
     */
    public void agregar(String ip, int puerto){
        Ordenador ordenador = new Ordenador(ip, puerto);
        
        if (getInicio() == null) {
            setInicio(ordenador);
        } else{
            Ordenador aux = getInicio();
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
            if (getInicio().getIp().equals(ip)) {
                setInicio(getInicio().getSiguiente());
            } else{
                Ordenador aux = getInicio();
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
        Ordenador aux = getInicio();
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
        Ordenador aux = getInicio();
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
        strGraphviz += "digraph grafica{\n" + "graph[label=\"Lista Simple\", labelloc=t, fontsize=20, compound=true];";
        strGraphviz += "\nrankdir = LR;\nnode [shape=component, fontcolor = black, style = filled, color = skyblue1];\nsplines=false; ";
        
        Ordenador aux = getInicio();
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

    /**
     * @return the inicio
     */
    public Ordenador getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Ordenador inicio) {
        this.inicio = inicio;
    }
    
}
