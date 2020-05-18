/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.reporte;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class ReporteFXML extends Application {
    
    private ReporteFXML() {
    }
    
    public static ReporteFXML getInstance() {
        return ReporteFXMLHolder.INSTANCE;
    }
    
    private static class ReporteFXMLHolder {

        private static final ReporteFXML INSTANCE = new ReporteFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ReporteFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
