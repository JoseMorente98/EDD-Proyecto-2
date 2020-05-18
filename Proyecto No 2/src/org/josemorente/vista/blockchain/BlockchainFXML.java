/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.blockchain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author josem
 */
public class BlockchainFXML extends Application {
    
    private BlockchainFXML() {
    }
    
    public static BlockchainFXML getInstance() {
        return BlockchainFXMLHolder.INSTANCE;
    }
    
    private static class BlockchainFXMLHolder {

        private static final BlockchainFXML INSTANCE = new BlockchainFXML();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BlockchainFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
