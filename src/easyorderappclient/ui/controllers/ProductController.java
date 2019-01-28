/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.transferObject.ProductBean;
import java.util.Optional;
import java.util.logging.Level;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Igor
 */
public class ProductController extends GenericController{
    /**
    * Product´s code(id) UI text field.
    */
    @FXML
    private TextField textCodigo;
    /**
    * Product´s unit price UI text field.
    */
    @FXML
    private TextField textPrecioUnidad;
    /**
    * Product´s name UI text field.
    */
    @FXML
    private TextField textNombre;
    /**
    * Product´s stock UI text field.
    */
    @FXML
    private TextField textStock;
    /**
    * Product´s filter UI text field.
    */
    @FXML
    private TextField textFiltrar;
    
    /**
     * Save product data button.
     */
    @FXML
    private Button btnGuardar;
    /**
     * Delete product data button.
     */
    @FXML
    private Button btnBorrar;
    /**
     * Cancel edit product data button.
     */
    @FXML
    private Button btnCancelar;
    /**
     * Filter product data button.
     */
    @FXML
    private Button btnFiltrar;
    
    /**
     * Filter product data button.
     */
    @FXML
    private Button btnActualizar;
    
    /**
    * Product's combo box.
    */
    @FXML
    private ComboBox cbFiltrar;
    
    /**
    * Product's data table view.
    */
    @FXML
    private TableView tbProductos;
    
    /**
    * Product's code table column.
    */
    @FXML
    private TableColumn colCodigo;
    /**
    * Product's name table column.
    */
    @FXML
    private TableColumn colNombre;
    /**
    * Product's unit price table column.
    */
    @FXML
    private TableColumn colPrecioUnidad;
    /**
    * Product's stock table column.
    */
    @FXML
    private TableColumn colStock;
    /**
     * Product's table data model.
     */
    @FXML
    private ObservableList<ProductBean> productsData;
    
    
    private TableRowSorter trsFiltro;
    
    
    
    
    /**
     * Method for initializing GestionUsuarios Stage. 
     * @param root The Parent object representing root node of view graph.
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Productos");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }
    
    private void handleWindowShowing(WindowEvent event){
        LOGGER.info("Beginning ProductController::handleWindowShowing");
        
        btnGuardar.setDisable(true);
        btnActualizar.setDisable(true);
        btnBorrar.setDisable(true);
        btnCancelar.setDisable(true);
        //btnFiltrar.setDisable(true);
       
        cbFiltrar.getSelectionModel().selectFirst();
        
        textCodigo.setPromptText("Introduzca id del producto");
        textPrecioUnidad.setPromptText("Introduzca precio del producto");
        textNombre.setPromptText("Introduzca nombre del producto");
        textStock.setPromptText("Introduzca stock del producto");
        textFiltrar.setPromptText("Introduzca nombre o id del producto que busca");
        
       
        textCodigo.requestFocus();
        
    }
    

     
}
