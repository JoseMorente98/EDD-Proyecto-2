/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.libro;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.josemorente.bean.Categoria;
import org.josemorente.bean.Obra;
import org.josemorente.bean.UsuarioLogin;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.NotificacionControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class LibroFXMLController implements Initializable {
    @FXML
    public TextField textFieldISBN;
    @FXML
    public TextField textFieldTitulo;
    @FXML
    public TextField textFieldAutor;
    @FXML
    public TextField textFieldEditorial;
    @FXML
    public TextField textFieldAno;
    @FXML
    public TextField textFieldEdicion;
    @FXML
    public TextField textFieldIdioma;
    @FXML
    public TextField textFieldBuscar;
    @FXML
    public TextField textFieldCategoria;
    @FXML
    public ComboBox<Categoria> comboBoxCategoria;
    @FXML
    public Pane pane;
    @FXML
    public Label label;
    @FXML
    public TableView<Obra> tableView;
    @FXML
    public TableColumn<Obra, String> columnISBN;
    @FXML
    public TableColumn<Obra, String> columnTitulo;
    @FXML
    public TableColumn<Obra, String> columnAutor;
    @FXML
    public TableColumn<Obra, String> columnAno;
    @FXML
    public TableColumn<Obra, String> columnUsuario;
    @FXML
    public TableColumn<Obra, String> columnCategoria;
    @FXML
    public TableColumn<Obra, String> columnEditorial;
    @FXML
    public TableColumn<Obra, String> columnEdicion;
    @FXML
    public TableColumn<Obra, String> columnIdioma;
    @FXML
    public Button button;
    /**
     * PROPIEDADES 
     */
    private SortedList<Obra> sortedList;
    private FilteredList<Obra> filteredList;
    private ObservableList<Categoria> observableListCategoria;

    public LibroFXMLController() {
        this.observableListCategoria = FXCollections.observableArrayList();
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane.setVisible(false);
        textFieldCategoria.setVisible(false);
        this.obtenerCategorias();
        this.obtenerDatos();
        columnISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        columnUsuario.setCellValueFactory(new PropertyValueFactory<>("carnetUsuario"));
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        columnEdicion.setCellValueFactory(new PropertyValueFactory<>("edicion"));
        columnIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        
        //BUSCAR EN TABLA
        textFieldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getTitulo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getAutor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getCategoria().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getEdicion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getEditorial().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getIdioma().toLowerCase().contains(lowerCaseFilter)) {
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
    private void agregar(ActionEvent event) throws InterruptedException {
        try {
            if(validacion()) {
                if(label.getText().equals("Agregar")) {
                    if(comboBoxCategoria.getSelectionModel().getSelectedItem() != null) {
                    Obra obra = new Obra(
                            Integer.parseInt(textFieldISBN.getText()),
                            textFieldTitulo.getText(),
                            textFieldAutor.getText(),
                            textFieldEditorial.getText(),
                            textFieldAno.getText(),
                            textFieldEdicion.getText(),
                            comboBoxCategoria.getSelectionModel().getSelectedItem().getNombre(),
                            textFieldIdioma.getText(),
                            UsuarioLogin.getCarnet());
                            CategoriaControlador.getInstance().agregarLibroServidor(comboBoxCategoria.getSelectionModel().getSelectedItem().getNombre(), obra);
                            NotificacionControlador.getInstance().informacion("Usuario Guardado", "Los cambios se han guardado exitosamente.");
                            Thread.sleep(2000);
                            this.obtenerDatos();
                            this.limpiar();
                    } else {
                        NotificacionControlador.getInstance().error("Validación de Campos", "No ha seleccionado una categoría.");
                    }
                } else if(label.getText().equals("Detalle")) {
                    this.pane.setVisible(false);
                    this.limpiar();
                }
            } else {
                NotificacionControlador.getInstance().error("Validación de Campos", "Los campos son requeridos.");
            }
        } catch (NumberFormatException e) {
            NotificacionControlador.getInstance().error("Validación de Campos", "El ISBN es un campo numérico.");
        }
    }
    
    @FXML
    private void cargarJSON(ActionEvent event) throws IOException {
        CategoriaControlador.getInstance().cargarJSON();
        NotificacionControlador.getInstance().informacion("Carga JSON", "Los cambios se han guardado exitosamente.");
        this.obtenerDatos();
    }
    
    @FXML
    private void mostrarAgregar(ActionEvent event) throws IOException {
        pane.setVisible(true);
        this.comboBoxCategoria.setVisible(true);
        this.textFieldCategoria.setVisible(false);
        label.setText("Agregar");
    }
    
    /**
     * OBTENER DATOS
     */
    private void obtenerDatos() {
        ObservableList<Obra> observableList = CategoriaControlador.getInstance().getObservableListObra();
        for (Obra object : observableList) {
            System.out.println(object);
        }
        filteredList = new FilteredList<>(observableList, p -> true);
        tableView.setItems(observableList);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    
    @FXML
    private void eliminar(ActionEvent event) throws InterruptedException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Obra obra = tableView.getSelectionModel().getSelectedItem();
            if(obra.getCarnetUsuario()==UsuarioLogin.getCarnet()) {
                CategoriaControlador.getInstance().eliminarLibroServidor(obra.getISBN(), obra.getCategoria());
                NotificacionControlador.getInstance().informacion("Libro Eliminado", "El libro se eliminó correctamente.");
                Thread.sleep(2000);
                this.obtenerDatos();
            } else {
                NotificacionControlador.getInstance().advertencia("Advetencia", "No puedes eliminar el libro si no eres el dueño.");
            }
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    @FXML
    private void actualizarMostrar(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Obra obra = tableView.getSelectionModel().getSelectedItem();
            //if(obra.getCarnetUsuario()==UsuarioLogin.getCarnet()) {
                pane.setVisible(true);
                label.setText("Detalle");
                this.comboBoxCategoria.setVisible(false);
                this.textFieldCategoria.setVisible(true);
                this.textFieldCategoria.setText(obra.getCategoria());
                this.textFieldAno.setText(obra.getAno());
                this.textFieldAutor.setText(obra.getAutor());
                this.textFieldEditorial.setText(obra.getEditorial());
                this.textFieldEdicion.setText(obra.getEdicion());
                this.textFieldISBN.setText(String.valueOf(obra.getISBN()));
                this.textFieldIdioma.setText(obra.getIdioma());
                this.textFieldTitulo.setText(obra.getTitulo());
                this.textFieldAno.setEditable(false);
                this.textFieldAutor.setEditable(false);
                this.textFieldEditorial.setEditable(false);
                this.textFieldEdicion.setEditable(false);
                this.textFieldISBN.setEditable(false);
                this.textFieldIdioma.setEditable(false);
                this.textFieldTitulo.setEditable(false);
            /*} else {
                NotificacionControlador.getInstance().advertencia("Advetencia", "No puedes editar el libro si no eres el dueño.");
            }*/
        } else {
            NotificacionControlador.getInstance().advertencia("Selección Tabla", "No ha seleccionado una fila de la tabla.");
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML 
    private void eliminarISBN() throws InterruptedException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Eliminar ISBN");
        dialog.setHeaderText("Eliminar Libro");
        dialog.setContentText("Por favor, ingrese el ISBN del libro a eliminar:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
            Obra obra = null;
            for (Obra object : CategoriaControlador.getInstance().getObservableListObra()) {
                if(object.getISBN() == Integer.parseInt(result.get())) {
                    obra = object;
                }
            }
            
            if(obra!=null) {
                if(obra.getCarnetUsuario()==UsuarioLogin.getCarnet()) {
                    CategoriaControlador.getInstance().eliminarLibroServidor(obra.getISBN(), obra.getCategoria());
                    NotificacionControlador.getInstance().informacion("Libro Eliminado", "El libro se eliminó correctamente.");
                    Thread.sleep(2000);
                    this.obtenerDatos();
                } else {
                    NotificacionControlador.getInstance().advertencia("Advetencia", "No puedes eliminar el libro si no eres el dueño.");
                }
            } else {
                NotificacionControlador.getInstance().error("Libro No Encontrado", "El ISBN introducido no corresponde a ningún libro.");
            }
        }
    }
    
    public boolean validacion() {
        if(textFieldAno.getText().length() > 0 &&
            textFieldAutor.getText().length() > 0 &&
            textFieldEditorial.getText().length() > 0 &&
            textFieldEdicion.getText().length() > 0 &&
            textFieldISBN.getText().length() > 0 &&
            textFieldIdioma.getText().length() > 0 &&
            textFieldTitulo.getText().length() > 0) {
            return true;
        }
        return false;
    }
    
    public void limpiar() {
        this.textFieldAno.setText("");
        this.textFieldAutor.setText("");
        this.textFieldEditorial.setText("");
        this.textFieldEdicion.setText("");
        this.textFieldISBN.setText("");
        this.textFieldIdioma.setText("");
        this.textFieldTitulo.setText("");
        this.comboBoxCategoria.getSelectionModel().clearSelection();
        textFieldCategoria.setVisible(false);
        pane.setVisible(false);
    }
    
    public void obtenerCategorias() {
        this.observableListCategoria.clear();
        this.observableListCategoria = CategoriaControlador.getInstance().getObservableListCategoria();
        this.comboBoxCategoria.setItems(observableListCategoria);
    }
    
}
