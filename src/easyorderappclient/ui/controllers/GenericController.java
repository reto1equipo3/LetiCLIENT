/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.PedidoLogic;
import easyorderappclient.businessLogic.ProductsManager;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * This is the base class for UI controllers in this application. It contains 
 * common methods and references for objects used by UI controllers
 * @author Leto
 */
public class GenericController {
 /**
     * Logger object used to log messages for application.
     */
    protected static final Logger LOGGER=Logger.getLogger("javafxapplicationud3example.ui.controller");
    /**
     * Maximum text fields length.
     */
    protected final int MAX_LENGTH=255;
    /**
     * The business logic object containing all business methods.
     */
    protected PedidoLogic pedidoLogic;
    protected ProductsManager productsManager;
    /**
     * Sets the business logic object to be used by this UI controller. 
     * @param pedidoLogic An object implementing {@link UsersManager} interface.
     */
    public void setPedidoLogic(PedidoLogic pedidoLogic){
        this.pedidoLogic=pedidoLogic;
    }
    
        /**
     * Sets the business logic object to be used by this UI controller. 
     * @param productManager An object implementing {@link UsersManager} interface.
     */
    public void setProductManager(ProductsManager productsManager){
        this.productsManager=productsManager;
    }
    
    
    
    
    
    
    /**
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the 
     * controller to the Stage object in order to make its initialization. Note 
     * that this makes Application, Controller and Stage being tightly coupled.
     */
    protected Stage stage;
    /**
     * Gets the Stage object related to this controller.
     * @return The Stage object initialized by this controller.
     */
    public Stage getStage(){
        return stage;
    }
    /**
     * Sets the Stage object related to this controller. 
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
  
    protected void showErrorAlert(String errorMsg){
        //Shows error dialog.
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              errorMsg,
                              ButtonType.OK); 
        alert.showAndWait();
        
    }
    
    
    
}
