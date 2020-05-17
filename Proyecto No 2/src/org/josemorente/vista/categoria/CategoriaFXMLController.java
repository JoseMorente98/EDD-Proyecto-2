/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.categoria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.josemorente.bean.Categoria;
import org.josemorente.bean.Cliente;
import org.josemorente.bean.Usuario;
import org.josemorente.bean.UsuarioLogin;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class CategoriaFXMLController implements Initializable {
    @FXML
    public TextField textFieldNombre;
    @FXML
    public TextField textFieldBuscar;
    @FXML
    public Pane pane;
    @FXML
    public Label label;
    @FXML
    public TableView<Categoria> tableView;
    @FXML
    public TableColumn<Categoria, String> columnNombre;
    @FXML
    public TableColumn<Categoria, String> columnUsuario;
    private SortedList<Categoria> sortedList;
    private FilteredList<Categoria> filteredList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TRAER TODO
        pane.setVisible(false);
        this.obtenerDatos();
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnUsuario.setCellValueFactory(new PropertyValueFactory<>("carnetUsuario"));
        
        //BUSCAR EN TABLA
        textFieldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getNombre().toLowerCase().contains(lowerCaseFilter)) {
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
    private void guardarCambios(ActionEvent event) throws Exception {
        if(validacion()) {
            if(label.getText().equals("Agregar")) {
                CategoriaControlador.getInstance().agregar(UsuarioLogin.getCarnet(), textFieldNombre.getText());                
            } else if(label.getText().equals("Actualizar")) {
                Categoria categoria = tableView.getSelectionModel().getSelectedItem();
                CategoriaControlador.getInstance().actualizar(categoria.getNombre(), UsuarioLogin.getCarnet(), textFieldNombre.getText());
                //CategoriaControlador.getInstance().(0, textFieldNombre.getText());
                
            }
            NotificacionControlador.getInstance().informacion("Categoría Guardada", "Los cambios se han guardado exitosamente.");
            this.obtenerDatos();
            this.limpiar();
        } else {
            NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
        }
    }    
    
    public boolean validacion() {
        if(textFieldNombre.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
    public void limpiar() {
        textFieldNombre.setText("");
        pane.setVisible(false);
    }
    
    @FXML
    private void mostrarAgregar(ActionEvent event) {
        pane.setVisible(true);
        label.setText("Agregar");
    }
    
    @FXML
    private void mostrarEditar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            pane.setVisible(true);
            label.setText("Actualizar");
            Categoria categoria = tableView.getSelectionModel().getSelectedItem();
            this.textFieldNombre.setText(categoria.getNombre());
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
    }
        
    @FXML
    private void eliminar(ActionEvent event) throws Exception {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Categoria categoria = tableView.getSelectionModel().getSelectedItem();
            CategoriaControlador.getInstance().eliminar(categoria.getNombre());
            NotificacionControlador.getInstance().informacion("Categoría Eliminada", "La categoría se elimino correctamente.");
            this.obtenerDatos();
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    /**
     * OBTENER DATOS
     */
    public void obtenerDatos() {
        ObservableList<Categoria> observableList = CategoriaControlador.getInstance().getObservableListCategoria();
        System.out.println("======================CATEGORIAS=============================");
        for (Categoria object : observableList) {
            System.out.println(object);
        }
        filteredList = new FilteredList<>(observableList, p -> true);
        tableView.setItems(observableList);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    
    
}
