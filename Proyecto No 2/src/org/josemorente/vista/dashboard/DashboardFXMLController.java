/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.blockchain.BlockchainFXML;
import org.josemorente.vista.categoria.CategoriaFXML;
import org.josemorente.vista.libro.LibroFXML;
import org.josemorente.vista.nodos.NodoFXML;
import org.josemorente.vista.reporte.ReporteFXML;
import org.josemorente.vista.usuario.UsuarioFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class DashboardFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void categoria(ActionEvent event) throws Exception {
        CategoriaFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void libro(ActionEvent event) throws Exception {
        LibroFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void usuario(ActionEvent event) throws Exception {
        UsuarioFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void blockchain(ActionEvent event) throws Exception {
        BlockchainFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void nodo(ActionEvent event) throws Exception {
        NodoFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void reporte(ActionEvent event) throws Exception {
        ReporteFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void logOut(ActionEvent event) throws Exception {
        FXMLDocument.getInstance().start(FXMLDocument.stage);
    }
    
    
}
