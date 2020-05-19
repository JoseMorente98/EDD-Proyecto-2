/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.josemorente.bean.Cliente;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jSONObjectsem
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
    
    /**
     * AGREGAR SERVIDOR 
     */
    public void agregarUsuarioServidor(int carnet, String nombre, String apellido, String carrera, String password) {
        try {
            Usuario usuario = new Usuario(carnet, nombre, apellido, carrera, password);
            JSONControlador.getInstance().generarJSON("Usuario", "1", usuario);
            Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
            ServidorEDD servidorEDD = new ServidorEDD(usuario, 1, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(servidorEDD);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ACTUALIZAR SERVIDOR 
     */
    public void actualizarUsuarioServidor(int carnet, String nombre, String apellido, String carrera, String password) {
        try {
            Usuario usuario = new Usuario(carnet, nombre, apellido, carrera, password);
            JSONControlador.getInstance().generarJSON("Usuario", "2", usuario);
            Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
            ServidorEDD servidorEDD = new ServidorEDD(usuario, 2, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
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
    public void eliminarUsuarioServidor(int carnet) {
        try {
            Usuario usuario = new Usuario(carnet);
            Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
            ServidorEDD servidorEDD = new ServidorEDD(usuario, 0, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(servidorEDD);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Usuario> getObservableList() {
        observableList.clear();
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
    
    public Usuario autenticar(int codigo, String password) {
        Usuario aux = null;
        for (int i = 0; i < M; i++) {
            if (tabla[i] != null) {
                aux = tabla[i];
                if(aux.getCarnet()==codigo && aux.getPassword().equals(password)) {
                    return aux;
                }
                while (aux.getSiguiente() != null) {
                    if(aux.getCarnet()==codigo && aux.getPassword().equals(password)) {
                        return aux;
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        return null;
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
    
    /**
     * CARGAR JSON 
     */
    public void cargarJSON() {
        ArrayList<Usuario> arrayListUsuario = new ArrayList<>();
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Archivos");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivo JSON", "*.json"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                Object object = new org.json.simple.parser.JSONParser().parse(new FileReader(file));
                JSONObject jSONObject = (JSONObject) object; 
                
                Iterator<Map.Entry> iteratorMap = null;
                JSONArray jSONArray = (JSONArray) jSONObject.get("Usuarios");
                Iterator iterator = jSONArray.iterator();
                
                while (iterator.hasNext())
                {
                    String nombre = "", apellido = "", carrera = "", password = "";
                    int carnet = 0;
                    iteratorMap = ((Map) iterator.next()).entrySet().iterator();
                    
                    while (iteratorMap.hasNext()) {
                        Map.Entry pair = iteratorMap.next();
                        if (pair.getKey().equals("Carnet")) {
                            carnet = Integer.parseInt(pair.getValue().toString());
                        }
                        if (pair.getKey().equals("Nombre")) {
                            nombre = (String)pair.getValue();
                        }
                        if (pair.getKey().equals("Apellido")) {
                            apellido = (String)pair.getValue();
                        }
                        if (pair.getKey().equals("Carrera")) {
                            carrera = (String)pair.getValue();
                        }
                        if (pair.getKey().equals("Password")) {
                            password = (String)pair.getValue();
                        }
                    }
                    Usuario usuario = new Usuario(carnet, nombre, apellido, carrera, password);
                    arrayListUsuario.add(usuario);
                }
                
                Socket socket = new Socket(Cliente.getIpServidor(), Integer.parseInt(Cliente.getPuertoServidor()));
                ServidorEDD servidorEDD = new ServidorEDD(1, Cliente.getIp(), Integer.parseInt(Cliente.getPuerto()));
                servidorEDD.setArrayListUsuario(arrayListUsuario);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(servidorEDD);
                socket.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);            
            } catch (IOException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
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
