/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.nodos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.josemorente.bean.Ordenador;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class NodoFXMLController implements Initializable {
    @FXML
    public TextField textFieldBuscar;
    @FXML
    public TableView<Ordenador> tableView;
    @FXML
    public TableColumn<Ordenador, String> columnIP;
    @FXML
    public TableColumn<Ordenador, String> columnPuerto;
     /**
     * PROPIEDADES 
     */
    private SortedList<Ordenador> sortedList;
    private FilteredList<Ordenador> filteredList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnIP.setCellValueFactory(new PropertyValueFactory<>("ip"));
        columnPuerto.setCellValueFactory(new PropertyValueFactory<>("puerto"));
        this.obtenerDatos();
        //BUSCAR EN TABLA
        textFieldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getIp().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }    
    
    /**
     * OBTENER DATOS
     */
    private void obtenerDatos() {
        ObservableList<Ordenador> observableList = OrdenadorControlador.getInstance().getObservableList();
        filteredList = new FilteredList<>(observableList, p -> true);
        tableView.setItems(observableList);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }
    
    @FXML
    private void regresar(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
    }
}
