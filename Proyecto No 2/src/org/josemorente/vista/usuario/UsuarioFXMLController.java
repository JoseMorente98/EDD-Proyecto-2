/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.josemorente.bean.ServidorEDD;
import org.josemorente.bean.Usuario;
import org.josemorente.controlador.MD5Controlador;
import org.josemorente.controlador.UsuarioControlador;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class UsuarioFXMLController implements Initializable, Runnable {
    @FXML
    public TextField carrera;
    @FXML
    public TextField nombre;
    @FXML
    public TextField apellido;
    @FXML
    public TextField carnet;
    @FXML
    public PasswordField password;
    @FXML
    public Pane pane;
    @FXML
    public Label label;
    @FXML
    public TableView<Usuario> tableView;
    @FXML
    public TableColumn<Usuario, String> columnCarnet;
    @FXML
    public TableColumn<Usuario, String> columnNombre;
    @FXML
    public TableColumn<Usuario, String> columnApellido;
    @FXML
    public TableColumn<Usuario, String> columnCarrera;
    @FXML
    public TableColumn<Usuario, String> columnPassword;
    @FXML
    public Button button;
    /**
     * PROPIEDADES GENERALES
     */
    private volatile boolean exit = false;
    public ObservableList observableList;
    public Thread thread;

    public UsuarioFXMLController() {
        observableList = FXCollections.observableArrayList();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        thread = new Thread(this);
        thread.start();
        pane.setVisible(false);
        columnCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.obtenerDatos();
    }

    @Override
    public void run() {
        try {
            //SOCKET CLIENTE
            ServerSocket serverSocket = new ServerSocket(8200);
            ServidorEDD paqueteRecibido = null;
            while(!exit) {
                Socket cliente = serverSocket.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                
                paqueteRecibido = (ServidorEDD) flujoEntrada.readObject();
                /**
                 * VALIDAR TDA
                 */
                if(paqueteRecibido.getUsuario() != null) {
                    Usuario usuario = paqueteRecibido.getUsuario();

                    if(paqueteRecibido.getEstado() == 0) {
                        UsuarioControlador.getInstance().eliminar(usuario.getCarnet());
                        this.obtenerDatos();
                    } else if(paqueteRecibido.getEstado() == 1) {
                        UsuarioControlador.getInstance().insertar(usuario.getCarnet(), usuario.getNombre(), usuario.getApellido(), usuario.getCarrera(), usuario.getPassword());
                        this.obtenerDatos();
                    } else if(paqueteRecibido.getEstado() == 2) {
                        UsuarioControlador.getInstance().actualizar(usuario.getCarnet(), usuario.getNombre(), usuario.getApellido(), usuario.getCarrera(), usuario.getPassword());
                        this.obtenerDatos();
                    }
                    
                    
                    //textArea.setText(fecha +  " - USUARIO AGREGADO "+usuario.getCarnet()+" - IP: " + servidorEDD.getIpEntrada());
                }
                //campoChat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje() + " para: "+ paqueteRecibido.getIp());
                cliente.close();
                System.out.println("ando escuchando");
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void stop(){
        exit = true;
    }
    
    @FXML
    public void exitApplication(ActionEvent event) {
        stop();
    }
    
    @FXML
    private void agregar(ActionEvent event) {
        Usuario usuario = new Usuario(
            Integer.parseInt(carnet.getText()),
            nombre.getText(), 
            apellido.getText(), 
            carrera.getText(), 
            password.getText());
        if(label.getText().equals("Agregar")) {
            try {
                Socket socket = new Socket("192.168.1.106", 8000);
                ServidorEDD servidorEDD = new ServidorEDD();
                servidorEDD.setEstado(1);
                servidorEDD.setIpEntrada("192.168.1.106");
                servidorEDD.setIpSalida("192.168.1.106");
                servidorEDD.setUsuario(usuario);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(servidorEDD);
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else if(label.getText().equals("Actualizar")){
            try {
                Socket socket = new Socket("192.168.1.106", 8000);
                ServidorEDD servidorEDD = new ServidorEDD();
                servidorEDD.setEstado(2);
                servidorEDD.setIpEntrada("192.168.1.106");
                servidorEDD.setIpSalida("192.168.1.106");
                servidorEDD.setUsuario(usuario);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(servidorEDD);
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        pane.setVisible(false);
    }
    
    @FXML
    private void mostrarAgregar(ActionEvent event) throws IOException {
        /*System.out.println("MOSTRAR PANE");
        pane.setVisible(true);
        label.setText("Agregar");
        this.carnet.setDisable(false);*/
        thread.currentThread().interrupt();
        exit = true;
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("org/josemorente/vista/categoria/CategoriaFXML.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Login");
        stage2.setScene(new Scene(root));
        stage2.show();
        // Hide this current window (if this is what you want)
        //((Stage)(event.getSource())).getScene().getWindow().hide();
        //Stage stage = (Stage) button.getScene().getWindow();
        // do what you have to do
        //stage.hide();
        UsuarioFXML.getInstance().close();
        
    }
    
    /**
     * OBTENER DATOS
     */
    private void obtenerDatos() {
        observableList.clear();
        observableList = UsuarioControlador.getInstance().getObservableList();
        tableView.setItems(observableList);
    }
    
    @FXML
    private void eliminar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try { 
                Usuario usuario = tableView.getSelectionModel().getSelectedItem();
                Socket socket = new Socket("192.168.1.106", 8000);
                ServidorEDD servidorEDD = new ServidorEDD();
                servidorEDD.setEstado(0);
                servidorEDD.setIpEntrada("192.168.1.106");
                servidorEDD.setIpSalida("192.168.1.106");
                servidorEDD.setUsuario(usuario);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(servidorEDD);
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("No ha seleccionado un elemento.");
        }
    }
    
    @FXML
    private void actualizarMostrar(ActionEvent event) {
        pane.setVisible(true);
        label.setText("Actualizar");
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Usuario usuario = tableView.getSelectionModel().getSelectedItem();
            this.carnet.setText(String.valueOf(usuario.getCarnet()));
            this.carnet.setDisable(true);
            this.nombre.setText(usuario.getNombre());
            this.apellido.setText(usuario.getApellido());
            this.carrera.setText(usuario.getCarrera());
            this.password.setText(usuario.getPassword());
        } else {
            System.out.println("No ha seleccionado un elemento.");
        }
    }
    
}
