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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;
import org.josemorente.controlador.UsuarioControlador;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class ServidorFXMLController implements Initializable, Runnable {
    @FXML
    private TextArea textArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Thread thread = new Thread(this);
        thread.start();
    }    

    @Override
    public void run() {
        //System.out.println("ESTOY ESCUCHANDO :D");
        try {
            textArea.setText("ESTOY ESCUCHANDO :D");
            
            //NUMERO DE PUERTO ENTRADA
            ServerSocket serverSocket = new ServerSocket(8000);
            ServidorEDD servidorEDD = null;
            while(true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    
                servidorEDD = (ServidorEDD) objectInputStream.readObject();
                Date fecha = new Date();
                System.out.println (fecha);
                
                /**
                 * VALIDAR TDA
                 */
                if(servidorEDD.getUsuario() != null) {
                    Usuario usuario = servidorEDD.getUsuario();
                    if(servidorEDD.getEstado() == 0) {
                        UsuarioControlador.getInstance().eliminar(usuario.getCarnet());
                        textArea.setText(fecha +  " - USUARIO ELIMINADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIpEntrada());
                    } else if(servidorEDD.getEstado() == 1) {
                        UsuarioControlador.getInstance().insertar(usuario.getCarnet(), usuario.getNombre(), usuario.getApellido(), usuario.getCarrera(), usuario.getPassword());
                        textArea.setText(fecha +  " - USUARIO AGREGADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIpEntrada());
                    } else if(servidorEDD.getEstado() == 2) {
                        UsuarioControlador.getInstance().actualizar(usuario.getCarnet(), usuario.getNombre(), usuario.getApellido(), usuario.getCarrera(), usuario.getPassword());
                        textArea.setText(fecha +  " - USUARIO ACTUALIZADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIpEntrada());
                    }
                     
                    
                }
                
                //NO. PUERTO SALIDA
                Socket socketEnviar = new Socket(servidorEDD.getIpSalida(), 8200);
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
                    
                socket.close();
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
