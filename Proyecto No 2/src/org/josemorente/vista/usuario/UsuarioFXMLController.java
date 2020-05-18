/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.josemorente.bean.Usuario;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class UsuarioFXMLController implements Initializable {
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
    public TextField textFieldBuscar;
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
     * PROPIEDADES 
     */
    private SortedList<Usuario> sortedList;
    private FilteredList<Usuario> filteredList;
    

    public UsuarioFXMLController() {
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane.setVisible(false);
        this.obtenerDatos();
        columnCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        //BUSCAR EN TABLA
        textFieldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getApellido().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getCarrera().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
        
    @FXML
    private void agregar(ActionEvent event) {
        try {
            if(validacion()) {
                if(label.getText().equals("Agregar")) {
                    UsuarioControlador.getInstance().insertar(
                    Integer.parseInt(carnet.getText()),
                    nombre.getText(),
                    apellido.getText(),
                    carrera.getText(),
                    password.getText());
                } else if(label.getText().equals("Actualizar")) {
                    UsuarioControlador.getInstance().actualizar(
                    Integer.parseInt(carnet.getText()),
                    nombre.getText(),
                    apellido.getText(),
                    carrera.getText(),
                    password.getText());
                }
                NotificacionControlador.getInstance().informacion("Usuario Guardado", "Los cambios se han guardado exitosamente.");
                this.obtenerDatos();
                this.limpiar();
            } else {
                NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
            }
        } catch (NumberFormatException e) {
            NotificacionControlador.getInstance().error("Validación de Campos", "El carnet es un campo numérico.");
        }
    }
    
    @FXML
    private void mostrarAgregar(ActionEvent event) throws IOException {
        pane.setVisible(true);
        label.setText("Agregar");
    }
    
    /**
     * OBTENER DATOS
     */
    private void obtenerDatos() {
        ObservableList<Usuario> observableList = UsuarioControlador.getInstance().getObservableList();
        filteredList = new FilteredList<>(observableList, p -> true);
        tableView.setItems(observableList);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    
    @FXML
    private void eliminar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Usuario usuario = tableView.getSelectionModel().getSelectedItem();
            UsuarioControlador.getInstance().eliminar(usuario.getCarnet());
            this.obtenerDatos();
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    @FXML
    private void actualizarMostrar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            pane.setVisible(true);
            label.setText("Actualizar");
            Usuario usuario = tableView.getSelectionModel().getSelectedItem();
            this.carnet.setText(String.valueOf(usuario.getCarnet()));
            this.carnet.setDisable(true);
            this.nombre.setText(usuario.getNombre());
            this.apellido.setText(usuario.getApellido());
            this.carrera.setText(usuario.getCarrera());
            this.password.setText("");
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
    }
    
    public boolean validacion() {
        if(nombre.getText().length() > 0 &&
            apellido.getText().length() > 0 &&
            carnet.getText().length() > 0 &&
            carrera.getText().length() > 0 &&
            password.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
    public void limpiar() {
        this.nombre.setText("");
        this.apellido.setText("");
        this.carrera.setText("");
        this.carnet.setText("");
        this.password.setText("");
        pane.setVisible(false);
    }
    
}
