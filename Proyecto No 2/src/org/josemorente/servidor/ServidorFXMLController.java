/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.josemorente.bean.Categoria;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Ordenador;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.ClienteControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class ServidorFXMLController implements Initializable, Runnable {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    /**
     *  VARIABLES
     */
    private Thread thread;
    private boolean exit;
    private ServerSocket serverSocket;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textField.setText("3000");
    }    

    @Override
    public void run() {
        try {
            textArea.setText("ESTOY ESCUCHANDO :D");
            serverSocket = new ServerSocket(Integer.parseInt(textField.getText()));
            ServidorEDD servidorEDD = null;
            while(!exit) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    
                servidorEDD = (ServidorEDD) objectInputStream.readObject();
                Date fecha = new Date();
                System.out.println (fecha);
                
                /**
                 * USUARIOS
                 */
                if(servidorEDD.getUsuario() != null) {
                    Usuario usuario = servidorEDD.getUsuario();
                    if(servidorEDD.getEstado() == 0) {
                        UsuarioControlador.getInstance().eliminar(usuario.getCarnet());
                        textArea.setText(fecha +  " - USUARIO ELIMINADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIp());
                    } else if(servidorEDD.getEstado() == 1) {
                        UsuarioControlador.getInstance().insertar(usuario.getCarnet(), 
                                usuario.getNombre(), 
                                usuario.getApellido(), 
                                usuario.getCarrera(), 
                                usuario.getPassword());
                        textArea.setText(fecha +  " - USUARIO AGREGADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIp());
                    } else if(servidorEDD.getEstado() == 2) {
                        UsuarioControlador.getInstance().actualizar(usuario.getCarnet(), 
                                usuario.getNombre(), 
                                usuario.getApellido(), 
                                usuario.getCarrera(), 
                                usuario.getPassword());
                        textArea.setText(fecha +  " - USUARIO ACTUALIZADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIp());
                    }
                }
                
                /**
                 * CATEGORIA 
                 */
                if(servidorEDD.getCategoria()!= null) {
                    Categoria categoria = servidorEDD.getCategoria();
                    if(servidorEDD.getEstado() == 0) {
                        try {
                            CategoriaControlador.getInstance().eliminar(categoria.getNombre());
                            textArea.setText(fecha +  " - CATEGORIA ELIMINADA "+categoria.getNombre()+" - IP: " + servidorEDD.getIp());
                        } catch (Exception ex) {
                            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if(servidorEDD.getEstado() == 1) {
                        CategoriaControlador.getInstance().agregar(categoria.getCarnetUsuario(), 
                                categoria.getNombre());
                        textArea.setText(fecha +  " - CATEGORIA AGREGADA "+categoria.getNombre()+" - IP: " + servidorEDD.getIp());
                    } else if(servidorEDD.getEstado() == 2) {
                        try {
                            CategoriaControlador.getInstance().actualizar(categoria.getNombre(), 
                                    categoria.getCarnetUsuario(), 
                                    categoria.getNombreActualizado());
                            textArea.setText(fecha +  " - CATEGORIA ACTUALIZADA "+categoria.getNombre()+" - IP: " + servidorEDD.getIp());
                        } catch (Exception ex) {
                            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                /**
                 * LIBRO
                 */
                if(servidorEDD.getObra()!= null) {
                    Obra obra = servidorEDD.getObra();
                    if(servidorEDD.getEstado() == 0) {
                        CategoriaControlador.getInstance().eliminarLibro(obra.getISBN(), 
                                obra.getCategoria());
                        textArea.setText(fecha +  " - LIBRO ELIMINADO "+obra.getISBN()+" - IP: " + servidorEDD.getIp());
                    } else if(servidorEDD.getEstado() == 1) {
                        CategoriaControlador.getInstance().agregarLibro(obra.getCategoria(), 
                                obra);
                        textArea.setText(fecha +  " - LIBRO AGREGADO "+obra.getISBN()+" - IP: " + servidorEDD.getIp());
                    }
                }
                
                /**
                 * ORDENADORES
                 */
                if(servidorEDD.getOrdenador()!= null) {
                    Ordenador ordenador = servidorEDD.getOrdenador();
                    if(servidorEDD.getEstado() == 0) {
                        OrdenadorControlador.getInstance().eliminar(ordenador.getIp());
                        textArea.setText(fecha +  " - HOST ELIMINADO "+ordenador.getIp()+" - IP: " + servidorEDD.getIp());
                    } else if(servidorEDD.getEstado() == 1) {
                        OrdenadorControlador.getInstance().agregar(ordenador.getIp(), ordenador.getPuerto());
                        textArea.setText(fecha +  " - HOST AGREGADO "+ordenador.getIp()+" - IP: " + servidorEDD.getIp());
                    }
                }
                
                //NO. PUERTO SALIDA
                /*Socket socketEnviar = new Socket(servidorEDD.getIp(), 8200);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketEnviar.getOutputStream());
                objectOutputStream.writeObject(servidorEDD);
                objectOutputStream.close();
                socketEnviar.close();
                
                /*Socket socketEnviar2 = new Socket("192.168.1.111", 8200);
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socketEnviar.getOutputStream());
                objectOutputStream2.writeObject(servidorEDD);
                objectOutputStream2.close();
                socketEnviar2.close();
                    */
                
                Ordenador ordenador  = OrdenadorControlador.getInstance().getInicio();
                while(ordenador != null) {
                    Socket socketEnviar = new Socket(ordenador.getIp(), ordenador.getPuerto());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketEnviar.getOutputStream());
                    objectOutputStream.writeObject(servidorEDD);
                    objectOutputStream.close();
                    socketEnviar.close();
                    ordenador = ordenador.getSiguiente();
                }
                    
                socket.close();
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void encender(ActionEvent event) {
        thread = new Thread(this);
        thread.start();
        exit = false;
    }
    
    @FXML
    private void apagar(ActionEvent event) throws IOException {
        thread.stop();
        exit = true;
        serverSocket.close();
    }
     
    
}
