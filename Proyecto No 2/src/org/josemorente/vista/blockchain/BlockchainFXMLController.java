/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.blockchain;

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
import org.josemorente.bean.CadenaBloque;
import org.josemorente.bean.Ordenador;
import org.josemorente.controlador.CadenaBloqueControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.dashboard.DashboardFXML;
import org.json.simple.JSONArray;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class BlockchainFXMLController implements Initializable {
    @FXML
    public TextField textFieldBuscar;
    @FXML
    public TableView<CadenaBloque> tableView;
    @FXML
    public TableColumn<CadenaBloque, String> columnIndex;
    @FXML
    public TableColumn<CadenaBloque, String> columnFecha;
    @FXML
    public TableColumn<CadenaBloque, String> columnNonce;
    @FXML
    public TableColumn<CadenaBloque, String> columnPreviousHash;
    @FXML
    public TableColumn<CadenaBloque, String> columnHash;
    
     /**
     * PROPIEDADES 
     */
    private SortedList<CadenaBloque> sortedList;
    private FilteredList<CadenaBloque> filteredList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        columnNonce.setCellValueFactory(new PropertyValueFactory<>("nonce"));
        columnPreviousHash.setCellValueFactory(new PropertyValueFactory<>("previousHash"));
        columnHash.setCellValueFactory(new PropertyValueFactory<>("hash"));
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
        ObservableList<CadenaBloque> observableList = CadenaBloqueControlador.getInstance().getObservableList();
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
    
    @FXML
    private void agregar(ActionEvent event) throws Exception {
        CadenaBloqueControlador.getInstance().agregar(new JSONArray());
        this.obtenerDatos();
    }
    
}
