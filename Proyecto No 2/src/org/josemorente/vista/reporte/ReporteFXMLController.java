/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.vista.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import org.josemorente.bean.Categoria;
import org.josemorente.controlador.CadenaBloqueControlador;
import org.josemorente.controlador.CategoriaControlador;
import org.josemorente.controlador.OrdenadorControlador;
import org.josemorente.controlador.UsuarioControlador;
import org.josemorente.vista.FXMLDocument;
import org.josemorente.vista.blockchain.BlockchainFXMLController;
import org.josemorente.vista.dashboard.DashboardFXML;

/**
 * FXML Controller class
 *
 * @author josem
 */
public class ReporteFXMLController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public ScrollPane scrollPane;
    final DoubleProperty doubleProperty = new SimpleDoubleProperty(200);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        doubleProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable) {
                imageView.setFitWidth(doubleProperty.get() * 4);
                imageView.setFitHeight(doubleProperty.get() * 3);
            }
        });

        scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    doubleProperty.set(doubleProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    doubleProperty.set(doubleProperty.get() / 1.1);
                }
            }
        });
    }    
    
    @FXML
    private void regresar(ActionEvent event) throws Exception {
        DashboardFXML.getInstance().start(FXMLDocument.stage);
    }
    
    @FXML
    private void arbolAVL(ActionEvent event) throws Exception {
        CategoriaControlador.getInstance().generarGraphviz();
        File file = new File("ArbolAVL.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
   }
    
    @FXML
    private void arbolB(ActionEvent event) throws Exception {
        List<String> choices = new ArrayList<>();
        for (Categoria object : CategoriaControlador.getInstance().getObservableListCategoria()) {
            choices.add(object.getNombre());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Reporte");
        dialog.setHeaderText("Reporte por Categoría");
        dialog.setContentText("Seleccione una categoria.");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
            CategoriaControlador.getInstance().generarGraphvizLibro(result.get());
            File file = new File("ArbolB.png");
            String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
            System.out.println(path);
            Thread.sleep(1000);
            imageView.setImage(new Image(path));
            imageView.preserveRatioProperty().set(true);
            scrollPane.setContent(imageView);
        }
    }
    
    @FXML
    private void tablaDispersion(ActionEvent event) throws Exception {
        UsuarioControlador.getInstance().generarGraphviz();
        File file = new File("TablaDeDispersion.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
    @FXML
    private void preOrder(ActionEvent event) throws Exception {
        CategoriaControlador.getInstance().generarGraphvizPreOrder();
        File file = new File("PreOrder.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
    @FXML
    private void inOrder(ActionEvent event) throws Exception {
        CategoriaControlador.getInstance().generarGraphvizInOrder();
        File file = new File("InOrder.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
    @FXML
    private void postOrder(ActionEvent event) throws Exception {
        CategoriaControlador.getInstance().generarGraphvizPosOrder();
        File file = new File("PosOrder.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
    @FXML
    private void listaSimple(ActionEvent event) throws Exception {
        OrdenadorControlador.getInstance().generarGraphviz();
        File file = new File("ListaSimple.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
    @FXML
    private void listaDoble(ActionEvent event) throws Exception {
        CadenaBloqueControlador.getInstance().generarGraphviz();
        File file = new File("ListaDoble.png");
        String path = "file:///" + file.getAbsoluteFile().toString().replace("/", File.separator);
        System.out.println(path);
        Thread.sleep(1000);
        imageView.setImage(new Image(path));
        imageView.preserveRatioProperty().set(true);
        scrollPane.setContent(imageView);
    }
    
}
