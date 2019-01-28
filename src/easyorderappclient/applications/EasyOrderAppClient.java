/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.applications;


import easyorderappclient.businessLogic.PedidoLogic;
import easyorderappclient.businessLogic.PedidoLogicFactory;
import static easyorderappclient.businessLogic.PedidoLogicFactory.REST_WEB_CLIENT_TYPE;
import easyorderappclient.ui.controllers.DetalleDePedidoDesktopFxmlController;
import easyorderappclient.ui.controllers.PedidosDesktopFxmlController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *  Application class for UI example. Entry point for the JavaFX application
 * @author Leticia Garcia
 */
public class EasyOrderAppClient extends  javafx.application.Application {
    
      /**
     * Entry point for the JavaFX application. Loads, sets and shows primary window.
     * It also uses a factory to get a business logic object to be passed to views.
     * @param stage The primary window of the application
     * @throws Exception 
     */
    
    @Override
    public void start(Stage stage) throws Exception {
        //Create Bussines Logic Controller to be passed to UI controllers
        PedidoLogic bussinessLogicController=
                PedidoLogicFactory.createPedidoLogic(REST_WEB_CLIENT_TYPE);
        //Uncomment this sentence if you want fake data for testing the UI 
        //bussinessLogicController=
        //        UsersManagerFactory.createUsersManager(TEST_MOCK_TYPE);
        //Load node graph from fxml file
       FXMLLoader loader=new FXMLLoader(
                getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
      /*  FXMLLoader loader=new FXMLLoader(
                getClass().getResource("/easyorderappclient/ui/fxml/DetalleDePedidoFXMLDocument.fxml")); */
        
        Parent root = (Parent)loader.load();
        //Get controller for graph 
       PedidosDesktopFxmlController primaryStageController=
                ((PedidosDesktopFxmlController)loader.getController());
           
      /* DetalleDePedidoDesktopFxmlController primaryStageController=  ((DetalleDePedidoDesktopFxmlController)loader.getController()); */

                        
        //Set a reference in UI controller para Bussiness Logic Controllesr
        primaryStageController.setPedidoLogic(bussinessLogicController);
      
        //Set a reference for Stage
        primaryStageController.setStage(stage);
       
        //Initializes primary stage
        primaryStageController.initStage(root);
    }

    /**
     * Entry point for the Java application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}