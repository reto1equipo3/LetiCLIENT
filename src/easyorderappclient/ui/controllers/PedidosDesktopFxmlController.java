/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.transferObjects.Cliente;
import easyorderappclient.transferObjects.EstadoPedido;
import easyorderappclient.transferObjects.Pedido;
import static easyorderappclient.ui.controllers.GenericController.LOGGER;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class for pedidos view.
 *
 * @author Leticia Garcia
 */
public class PedidosDesktopFxmlController extends GenericController {

    private static final Logger logger = Logger.getLogger("easyorderappclient.controllers");

    private Pedido pedido;
    private Cliente cliente;

    @FXML
    private DatePicker DateFechaPreparadoDesde;

    @FXML
    private DatePicker DateFechaPreparadoHasta;

    @FXML
    private ComboBox ComboEstado;
    @FXML
    private AnchorPane gpPedidos;
    @FXML
    private TableView tablaGestionPedidos;
    @FXML
    private TableColumn tbCodigo;
    @FXML
    private TableColumn tbCliente;
    @FXML
    private TableColumn tbFechaTramitado;
    @FXML
    private TableColumn tbFechaPreparado;
    @FXML
    private TableColumn tbFechaEntregado;
    @FXML
    private TableColumn tbEstado;

    @FXML
    private Button btnVer;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnBuscar;

    //MENUBAR
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuMenu;
    @FXML
    private Menu menuPedidos;
    @FXML
    private Menu menuProductos;
    @FXML
    private Menu menuEmpleados;
    @FXML
    private MenuItem itemProductos;

    @FXML
    private MenuItem itemCerrarSesion;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnFiltrar;

    /**
     * Pedido's table data model.
     */
    private ObservableList<Pedido> pedidosData;

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Method for initializing PedidosDesktop Stage.
     *
     * @param root The Parent object representing root node of view graph.
     */
    public void initStage(Parent root) throws BusinessLogicException {

        LOGGER.info("Initializing Gestion Pedidos stage");
        try {
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            //Asociamos la escena a la ventana
            stage.setScene(scene);
            //Le vamos a dar un titulo a la ventana.       
            stage.setTitle("Gestion de Pedidos");
            //Para que la ventana no sea redimensionable
            stage.setResizable(false);
            //Menu Pedidos
            // menuPedidos.setOnAction(this::pedidosWindow);
            //Menu Productos
            itemProductos.setOnAction(this::productosWindow);
            //Menu Empleados
            //menuEmpleados.setOnAction(this::empleadosWindow);
            //Menu item cerrar sesion
            itemCerrarSesion.setOnAction(this::cerrarSesion);

            //Lo que hacen los botones
            btnBuscar.setOnAction(this::buscarBoton);

            btnVer.setOnAction(this::handleVerAction);

            btnEliminar.setOnAction(this::handleEliminarAction);
            btnFiltrar.setOnAction(this::handleFiltrarAction);
            //Datepicker
            datePicker.setOnAction(this::handleDatePickerAction);
            // Configurar los manejadores de eventos de la ventana
            stage.setOnShowing(this::handleWindowShowing);

            //Controles de ventana sus gestores de eventos
            tablaGestionPedidos.getSelectionModel().selectedItemProperty().addListener(this::handleGestionPedidosSelectionChange);

            //Set estado combo data model
            ObservableList<String> estadoNames = FXCollections.observableArrayList(" ", "TODOS", "TRAMITADO", "PREPARADO", "ENVIADO");
            ComboEstado.setItems(estadoNames);
            //Seleccionar un estado por defecto
            ComboEstado.getSelectionModel().select("  ");

            //Set factories for cell values in pedido table columns.
            tbCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tbCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tbFechaTramitado.setCellValueFactory(new PropertyValueFactory<>("fechaTramitado"));
            tbFechaPreparado.setCellValueFactory(new PropertyValueFactory<>("fechaPreparado"));
            tbFechaEntregado.setCellValueFactory(new PropertyValueFactory<>("fechaEntregado"));
            tbEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            //Create an observable list for pedido table       
            pedidosData = FXCollections.observableArrayList(pedidoLogic.buscarTodosLosPedidos());

            //EJEMPLO PARA VER SI VA LA VISTA
            //  ObservableList addAll = tablaGestionPedidos.getColumns().addAll(codigo,cliente,fechaTramitado,fechaPreparado,fechaEntregado);
            /*  List<Pedido> pedidosData = new ArrayList();
        java.util.Date fechaActual = new java.util.Date();
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "ENVIADO"));
        pedidosData.add(new Pedido(1, 1, fechaActual, fechaActual, fechaActual, "PEPE"));
                //tablaGestionPedidos.setItems(FXCollections.observableArrayList(pedidosData));  */
            //Set table model
            tablaGestionPedidos.setItems(pedidosData);

            //Para ver la ventana
            stage.show();
        } catch (BusinessLogicException e) {
            showErrorAlert("No se ha podido abrir la ventana.\n"
                    + e.getMessage());

        }
    }

    /**
     * Initializes the window when shown.
     *
     * @param event WindowEvent event
     */
    private void handleWindowShowing(WindowEvent event) {

        LOGGER.info("Beginning Pedido controller::handleWindowsShowing");
        //Select primer estado by default
        ComboEstado.getSelectionModel().selectFirst();
        ComboEstado.requestFocus();
        //Los botones Buscar, Ver y Modificar van a estar deshabilitados
        //El boton va a estar habilitado
        btnBuscar.setDisable(false);
        btnBuscar.setTooltip(new Tooltip("Filtrar por estado."));
        btnBuscar.setMnemonicParsing(true);
        btnBuscar.setText("_Buscar");

        btnEliminar.setDisable(true);
        btnEliminar.setTooltip(new Tooltip("Seleccione una fila para eliminar un pedido."));
        btnVer.setDisable(true);
        btnVer.setTooltip(new Tooltip("Seleccione una fila para ver detalles del pedido."));
        btnVer.setMnemonicParsing(true);
        btnVer.setText("_Ver");

        btnFiltrar.setDisable(true);
        btnFiltrar.setTooltip(new Tooltip("Seleccione una fecha para filtrar por fecha tramitado."));
        datePicker.setEditable(false);
    }

    /**
     * Pedido table selection changed event handler. It enables or disables
     * buttons depending on selection state of the table.
     *
     * @param observable the property being observed: SelectedItem Property
     * @param oldValue old Pedido value for the property.
     * @param newValue new Pedido value for the property.
     */
    public void handleGestionPedidosSelectionChange(ObservableValue observable, Object oldValue, Object newValue) {

        //Si hay algo seleccionado. Si es diferente a vacio
        if (tablaGestionPedidos.getSelectionModel().getSelectedItem() != null) {
            //Se tiene que habilitar el boton VER           
            btnVer.setDisable(false);
            //Se tiene que habilitar el boton ELIMINAR        
            btnEliminar.setDisable(false);

        }
        if (tablaGestionPedidos.getSelectionModel().getSelectedItem() == null) {
            //Se tiene que deshabilitar el boton VER           
            btnVer.setDisable(true);
            //Se tiene que deshabilitar el boton ELIMINAR        
            btnEliminar.setDisable(true);
        }

    }

    /**
     * Action event handler for ver button. Metodo para ir a la ventana de
     * detalles del pedido
     *
     * @param event The ActionEvent object for the event.
     */
    @FXML
    public void handleVerAction(ActionEvent ev) {

        //Get selected pedido data from table view model
        Pedido selectedPedido = ((Pedido) tablaGestionPedidos.getSelectionModel()
                .getSelectedItem());
        try {
            //Load node graph from fxml file
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/DetalleDePedidoFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller for graph 
            DetalleDePedidoDesktopFxmlController controller
                    = ((DetalleDePedidoDesktopFxmlController) loader.getController());

            controller.setPedidoLogic(pedidoLogic);

            controller.setPedido(selectedPedido);
            //Initializes stage
            controller.initStage(root);
            //hides UserView stage
            stage.hide();
        } catch (Exception ex) {
            showErrorAlert("No se ha podido abrir la ventana:\n"
                    + ex.getLocalizedMessage());
            LOGGER.log(Level.SEVERE,
                    "UI LoginController: Error opening pedido managing window: {0}",
                    ex.getMessage());
        }
        //Clear selection and refresh table view 
        tablaGestionPedidos.getSelectionModel().clearSelection();
        tablaGestionPedidos.refresh();
    }

    /**
     * Action event handler for eliminar button. It asks pedido for confirmation
     * on delete, sends delete message to the business logic tier and updates
     * user table view.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void handleEliminarAction(ActionEvent ev) {

        Alert alert = null;
        try {

            //Get selected pedido data from table view model
            Pedido seleccionPedido = ((Pedido) tablaGestionPedidos.getSelectionModel()
                    .getSelectedItem());
            //Ask user for confirmation on delete
            alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Borrar la fila seleccionada?\n"
                    + "Esta operación no se puede deshacer.",
                    ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> result = alert.showAndWait();
            //If OK to deletion
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //delete user from server side
                this.pedidoLogic.eliminarPedido(seleccionPedido);
                //removes selected item from table
                tablaGestionPedidos.getItems().remove(seleccionPedido);
                tablaGestionPedidos.refresh();

            }

        } catch (BusinessLogicException e) {
            //If there is an error in the business logic tier show message and
            //log it.
            showErrorAlert("Error al borrar el pedido:\n"
                    + e.getMessage());
            LOGGER.log(Level.SEVERE,
                    "UI PedidosDesktopController: Error deleting pedido: {0}",
                    e.getMessage());

        }
    }

    /**
     * Action event handler for buscar button.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void buscarBoton(ActionEvent ev) {
        // "TODOS", "TRAMITADO", "PREPARADO", "ENVIADO"
        //Si selecciona TODO, que le salgan todos
        try { //TODOS
            pedidosData = FXCollections.observableArrayList(pedidoLogic.buscarTodosLosPedidos());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(PedidosDesktopFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ComboEstado.getSelectionModel().getSelectedItem().equals("TRAMITADO")) {
            for (Iterator<Pedido> iterator = pedidosData.iterator(); iterator.hasNext();) {
                Pedido next = iterator.next();
                if (next.getEstado() != EstadoPedido.TRAMITADO) {
                    iterator.remove();
                }
            }

        } else if (ComboEstado.getSelectionModel().getSelectedItem().equals("PREPARADO")) {

            for (Iterator<Pedido> iterator = pedidosData.iterator(); iterator.hasNext();) {
                Pedido next = iterator.next();
                if (next.getEstado() != EstadoPedido.PREPARADO) {
                    iterator.remove();
                }
            }

        } else if (ComboEstado.getSelectionModel().getSelectedItem().equals("ENVIADO")) {
            for (Iterator<Pedido> iterator = pedidosData.iterator(); iterator.hasNext();) {
                Pedido next = iterator.next();
                if (next.getEstado() != EstadoPedido.ENVIADO) {
                    iterator.remove();
                }
            }
        }
        tablaGestionPedidos.setItems(pedidosData);

    }
     /**
     * Action event handler for datepicker. Para habilitar el
     * button de filtrar
     *
     * @param event The ActionEvent object for the event.
     */
    public void handleDatePickerAction(ActionEvent ev) {
      
        btnFiltrar.setDisable(false);
    }
     /**
     * Action event handler for filtrar button. Metodo para filtrar por fecha tramitado
     *
     * @param event The ActionEvent object for the event.
     */
    public void handleFiltrarAction(ActionEvent ev) {

        LocalDate dateDelPicker = datePicker.getValue();

        if (dateDelPicker.isEqual(dateDelPicker)) {

            for (Iterator<Pedido> iterator = pedidosData.iterator(); iterator.hasNext();) {
                Pedido next = iterator.next();
                Instant instant = next.getFechaTramitado().toInstant();
                LocalDate tramitado = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                if (!tramitado.equals(dateDelPicker)) {
                    iterator.remove();
                }
                    datePicker.getEditor().clear();
                    //tablaGestionPedidos.setItems(pedidosData);
                    //tablaGestionPedidos.refresh();
            }
        }

    }

    /**
     * Va a refrescar la tabla despues de haber modificado y update la tabla,
     *
     * @param ev The ActionEvent object for the event.
     */
    public void handleRefrescarTabla(ActionEvent ev) {

        tablaGestionPedidos.refresh();

    }

    /* public void pedidosWindow(ActionEvent ev) {
         LOGGER.info("ClickOn Pedidos Menu");
         try{
          //Load node graph from fxml file
                    FXMLLoader loader
                            = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
                    Parent root = (Parent) loader.load();
                    //Get controller for graph 
                    PedidosDesktopFxmlController controller
                            = ((PedidosDesktopFxmlController) loader.getController());
                    controller.setPedidoLogic(pedidoLogic);
                  
                    //Initializes stage
                    controller.initStage(root);
                    //hides UserView stage
                    stage.hide();
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE,
                            "UI DetallesPedido View: Error opening Pedidos window: {0}",
                            ex.getMessage());
                }
     
        
     
     }*/
    /**
     * Para ir desde el menu, a la view de Productos.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void productosWindow(ActionEvent ev) {
        LOGGER.info("ClickOn Productos Menu");
        try {
            //Load node graph from fxml file
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/Product.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller for graph 
            ProductController controller
                    = ((ProductController) loader.getController());
            controller.setProductManager(productsManager);

            //Initializes stage
            controller.initStage(root);
            //hides UserView stage
            stage.hide();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "UI DetallesPedido View: Error opening Productos window: {0}",
                    ex.getMessage());
        }
    }

    /**
     * Action event handler for cerrar sesion Menu button.. It closes the
     * application.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void cerrarSesion(ActionEvent ev) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);  //No queremos que salga titulo eb la cabecera
        alert.setContentText("¿Esta seguro que quiere cerrar sesion?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            //CONTROLADOR DE INICIO SESION
        }

    }

}
