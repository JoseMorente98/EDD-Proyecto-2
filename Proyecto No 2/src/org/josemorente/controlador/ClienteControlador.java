/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.josemorente.bean.CadenaBloque;
import org.josemorente.bean.Categoria;
import org.josemorente.bean.Cliente;
import org.josemorente.bean.Obra;
import org.josemorente.bean.Ordenador;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;
import org.josemorente.bean.UsuarioLogin;
import org.json.simple.JSONArray;

/**
 *
 * @author josem
 */
public class ClienteControlador implements Runnable {
    /**
     * PROPIEDADES 
     */
    public boolean exit;
    public ServerSocket serverSocket;
    public Thread thread;

    private ClienteControlador() {
    }
    
    public static ClienteControlador getInstance() {
        return ClienteControladorHolder.INSTANCE;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(Cliente.getPuerto()));
            ServidorEDD servidorEDD = null;
            while(exit) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                servidorEDD = (ServidorEDD) objectInputStream.readObject();
                /**
                 * USUARIO 
                 */
                if(servidorEDD.getUsuario() != null) {
                    Usuario usuario = servidorEDD.getUsuario();
                    if(servidorEDD.getEstado() == 0) {
                        UsuarioControlador.getInstance().eliminar(usuario.getCarnet());
                    } else if(servidorEDD.getEstado() == 1) {
                        UsuarioControlador.getInstance().insertar(
                            usuario.getCarnet(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getCarrera(),
                            usuario.getPassword());
                    } else if(servidorEDD.getEstado() == 2) {
                        UsuarioControlador.getInstance().actualizar(
                        usuario.getCarnet(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getCarrera(),
                        usuario.getPassword());
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
                        } catch (Exception ex) {
                            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if(servidorEDD.getEstado() == 1) {
                        CategoriaControlador.getInstance().agregar(categoria.getCarnetUsuario(), categoria.getNombre());
                    } else if(servidorEDD.getEstado() == 2) {
                        try {
                            CategoriaControlador.getInstance().actualizar(categoria.getNombre(), categoria.getCarnetUsuario(), categoria.getNombreActualizado());
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
                        CategoriaControlador.getInstance().eliminarLibro(obra.getISBN(), obra.getCategoria());
                    } else if(servidorEDD.getEstado() == 1) {
                        CategoriaControlador.getInstance().agregarLibro(obra.getCategoria(), obra);
                    }
                }
                
                /**
                 * NODO 
                 */
                if(servidorEDD.getOrdenador()!= null) {
                    Ordenador ordenador = servidorEDD.getOrdenador();
                    if(servidorEDD.getEstado() == 0) {
                        OrdenadorControlador.getInstance().eliminar(ordenador.getIp());
                    } else if(servidorEDD.getEstado() == 1) {
                        OrdenadorControlador.getInstance().agregar(ordenador.getIp(), ordenador.getPuerto());
                    }
                }
                
                /**
                 * BLOCKCHAIN 
                 */
                if(servidorEDD.getCadenaBloque()!= null) {
                    CadenaBloque cadenaBloque = servidorEDD.getCadenaBloque();
                    if(servidorEDD.getEstado() == 0) {
                        //PENDIENTE
                    } else if(servidorEDD.getEstado() == 1) {
                        CadenaBloqueControlador.getInstance().agregar(new JSONArray());
                    }
                }
                
                /**
                 * ARREGLO USUARIO 
                 */
                if(servidorEDD.getArrayListUsuario().size() > 0) {
                    ArrayList<Usuario> arrayList = servidorEDD.getArrayListUsuario();
                    if(servidorEDD.getEstado() == 1) {
                        for (Usuario usuario : arrayList) {
                            UsuarioControlador.getInstance().insertar(
                            usuario.getCarnet(),
                            usuario.getNombre(),
                            usuario.getApellido(),
                            usuario.getCarrera(),
                            usuario.getPassword());
                        }
                    }
                }
                
                /**
                 * ARREGLO LIBRO 
                 */
                if(servidorEDD.getArrayListObra().size() > 0) {
                    ArrayList<Obra> arrayList = servidorEDD.getArrayListObra();
                    if(servidorEDD.getEstado() == 1) {
                        for (Obra obra : arrayList) {
                            CategoriaControlador.getInstance().agregarLibro(obra.getCategoria(), obra);
                        }
                    }
                }
                
                socket.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static class ClienteControladorHolder {
        private static final ClienteControlador INSTANCE = new ClienteControlador();
    }
    
    public void iniciarServidor() {
        exit = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public void detenerServidor() {        
        try {
            exit = false;
            thread.stop();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
