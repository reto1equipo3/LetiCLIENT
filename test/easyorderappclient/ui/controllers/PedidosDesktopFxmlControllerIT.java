/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.ui.controllers;

import easyorderappclient.applications.EasyOrderAppClient;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;

import javafx.stage.Stage;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import org.testfx.matcher.control.ComboBoxMatchers;

/**
 * Testing class for Pedido view and controller
 *
 * @author Leticia Garcia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PedidosDesktopFxmlControllerIT extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new EasyOrderAppClient().start(stage);

    }

   @Test
   public void testA_filtrarPorEstadoTramitado(){   
       //Combobox para TRAMITADO
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
   }
   @Test
   public void testB_filtrarPorEstadoPreparado(){       
       //Combobox para PREPARADO
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
   }

   
     @Test
   public void testC_filtrarPorEstadoEnviado(){
       //Combobox para ENVIADO
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
   }

   @Test
   public void testD_filtrarPorEstadoTodos(){
       //Combobox para TODOS
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN); 
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
   } 
     
  
    @Test
   public void testE_Cancel_Cambiar_Estado_DeTramitado_APreparado(){
         //Combobox para TRAMITADO
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
        
        //check that table view has rows
        TableView table=lookup("#tablaGestionPedidos").queryTableView();
        int rowCount=table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                       rowCount,0); 
         //look for 1st row in table view and click it
         Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);          
        verifyThat("#btnVer", isEnabled());
        clickOn("#btnVer");
         verifyThat("#gpDetallePedido", isVisible());
         
         clickOn("#cmbEstado"); 
         type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER); 
        clickOn("#btnAtras");
        
        verifyThat("Esta seguro que quiere ese estado?\n" + "Esta operación no se puede deshacer.",
                    isVisible());  
        sleep(1000);
        clickOn("Cancelar");
         
         
        
   }
     
 
   @Test
   public void testF_Cambiar_Estado_DeTramitado_APreparado(){
         //Combobox para TRAMITADO
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
        
        //check that table view has rows
        TableView table=lookup("#tablaGestionPedidos").queryTableView();
      //get row count
        int rowCount=table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                        rowCount,0); 
        //look for 1st row in table view and click it
        Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
          
        verifyThat("#btnVer", isEnabled());
         clickOn("#btnVer");
         verifyThat("#gpDetallePedido", isVisible());
         
         clickOn("#cmbEstado"); 
         type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER); 
        clickOn("#btnAtras");
        
        verifyThat("Esta seguro que quiere ese estado?\n" + "Esta operación no se puede deshacer.",
                    isVisible());  
        sleep(1000);
        clickOn("Aceptar");
         
        sleep(2000);
        verifyThat("#gpPedidos", isVisible());       
        sleep(1000);
     
   
        
        
        
        
   } 
   
    
  @Test
   public void testG_Cancelar_Cambiar_Estado_DePreparado_AEnviado(){
        
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
        
        //check that table view has rows
        TableView table=lookup("#tablaGestionPedidos").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0); 
         Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
          
        verifyThat("#btnVer", isEnabled());
         clickOn("#btnVer");
         verifyThat("#gpDetallePedido", isVisible());
         
         clickOn("#cmbEstado"); 
         type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER); 
        clickOn("#btnAtras");
        
        verifyThat("Esta seguro que quiere ese estado?\n" + "Esta operación no se puede deshacer.",
                    isVisible
    ());  
        sleep(1000);
        clickOn("Cancelar");

   } 

  @Test
   public void testH_Cambiar_Estado_DePreparado_AEnviado(){
        
       clickOn("#ComboEstado");    
     
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);        
        clickOn("#btnBuscar");
        
        //check that table view has rows
        TableView table=lookup("#tablaGestionPedidos").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0); 
         Node row=lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
          
        verifyThat("#btnVer", isEnabled());
         clickOn("#btnVer");
         verifyThat("#gpDetallePedido", isVisible());
         
         clickOn("#cmbEstado"); 
         type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER); 
        clickOn("#btnAtras");
        
        verifyThat("Esta seguro que quiere ese estado?\n" + "Esta operación no se puede deshacer.",
                    isVisible());  
        sleep(1000);
        clickOn("Aceptar");
    
         sleep(2000);
        verifyThat("#gpPedidos", isVisible());
       
         sleep(1000);   
      
    
   } 
    @Test
    public void testI_VerDetallesDePedidoEnviado() {

        clickOn("#ComboEstado");

        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        sleep(1000);
        type(KeyCode.ENTER);
        clickOn("#btnBuscar");

        //check that table view has rows
        TableView table = lookup("#tablaGestionPedidos").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                table.getItems().size(), 0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);

        verifyThat("#btnVer", isEnabled());
        clickOn("#btnVer");
        verifyThat("#gpDetallePedido", isVisible());
        sleep(1000);
        clickOn("#btnAtras");
        verifyThat("#gpPedidos", isVisible());

    }

}
