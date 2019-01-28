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
import easyorderappclient.transferObjects.Producto;
import easyorderappclient.transferObjects.ProductoPedido;
import static easyorderappclient.ui.controllers.GenericController.LOGGER;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Leticia Garcia
 */
public class DetalleDePedidoDesktopFxmlController extends GenericController {

    private static final Logger logger = Logger.getLogger("easyorderappclient.controllers");

    private Pedido pedido;
    private Cliente cliente;
    private Producto producto;

    @FXML
    private ComboBox cmbEstado;
    @FXML
    private AnchorPane gpDetallePedido;
    @FXML
    private TableView<ProductoPedido> tablaDetallePedido;
    @FXML
    private TableColumn tbIdProducto;
    @FXML
    private TableColumn tbNombre;
    @FXML
    private TableColumn tbCantidad;
    @FXML
    private TableColumn tbPrecio;

    @FXML
    private Button btnAtras;
    @FXML
    private Button btnGenerarFactura;
    @FXML
    private Label lblIdCliente;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblLocalidad;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHora;
    @FXML
    private Label lblCodigoPostal;
    @FXML
    private DatePicker datePicker;

    /**
     * PedidoProducto's table data model.
     */
    private ObservableList<ProductoPedido> productoPedidoData;

    //Setters del user es el que manda la interfaz SignIn.  
    public void setPedido(Pedido pedido) {

        this.pedido = pedido;
    }

    //Setter del pedido que le manda la interrfaz
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Method for initializing DetalleDePedido Stage.
     *
     * @param root The Parent object representing root node of view graph.
     */
    public void initStage(Parent root) throws BusinessLogicException {

        LOGGER.info("Initializing Detalle del Pedido stage");
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Asociamos la escena a la ventana
        stage.setScene(scene);
        //Le vamos a dar un titulo a la ventana.       
        stage.setTitle("Detalle del pedido");
        //Para que la ventana no sea redimensionable
        stage.setResizable(false);
        // Configurar los manejadores de eventos de la ventana
        stage.setOnShowing(this::handleWindowShowing);
        lblIdCliente.textProperty().addListener(this::handleTextChanged);
        lblTelefono.textProperty().addListener(this::handleTextChanged);
        lblLocalidad.textProperty().addListener(this::handleTextChanged);
        lblDireccion.textProperty().addListener(this::handleTextChanged);
        lblFecha.textProperty().addListener(this::handleTextChanged);
        lblHora.textProperty().addListener(this::handleTextChanged);
        lblCodigoPostal.textProperty().addListener(this::handleTextChanged);
        //Lo que hacen los botones
        btnAtras.setOnAction(this::handleVolverAtras);
        btnGenerarFactura.setOnAction(this::handleGenerarFactura);

        //Set factories for cell values in pedido table columns.
        //tbIdProducto.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("producto"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("producto"));//
        tbCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); //Cantidad
        //tbPrecioUnidad.setCellValueFactory(new PropertyValueFactory<>("producto")); //precioUnidad
        tbPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        //Create an observable list for pedido table       
        // productoPedidoData = FXCollections.observableArrayList(pedidoLogic.buscarTodosLosPedidos());
        productoPedidoData = FXCollections.observableArrayList(pedido.getProductos());//ProductosPedidos.
        /*   ObservableList<Producto> productos = FXCollections.observableArrayList(new ArrayList<Producto>());
        // ObservableList<ProductoPedido> productoPedidos = FXCollections.observableArrayList(new ArrayList<ProductoPedido>());

        for (int i = 0; i < productoPedidoData.size(); i++) {

            productos.add(productoPedidoData.get(i).getProducto());

        }*/

        //Set table model          
        //tablaDetallePedido.setItems(productos); //NO SALE CANTIDAD. PERO ID Y NOMBRE BIEN. Poniendo id / nombre arriba.
        tablaDetallePedido.setItems(productoPedidoData);// SALE TODO PERO CON EL ID_PRODUCTO+NOMBRE poniendo arriba producto producto
        cliente = pedido.getCliente(); //Para poder sacar los datos del cliente

        //Para ver la ventana
        stage.show();

    }

    /**
     * Initializes window state. It implements behavior for WINDOW_SHOWING type
     * event.
     *
     * @param event The window event
     */
    private void handleWindowShowing(WindowEvent event) {
        //Lo que viene de la tabla de Gestion de Pedidos
        if (pedido.getEstado() == EstadoPedido.ENVIADO) {
            //Quiero que se me deshabilite el boton del comboBox, porque no puede actualizar nada   

            cmbEstado.setEditable(false);
        }
        if (pedido.getEstado() == EstadoPedido.TRAMITADO) {
            ObservableList<String> estadoNames = FXCollections.observableArrayList("  ", "PREPARADO");
            cmbEstado.setItems(estadoNames);
            cmbEstado.getSelectionModel().select("  ");

        }
        if (pedido.getEstado() == EstadoPedido.PREPARADO) {
            ObservableList<String> estadoNames = FXCollections.observableArrayList("  ", "ENVIADO");
            cmbEstado.setItems(estadoNames);
            cmbEstado.getSelectionModel().select("  ");
        }
        //Datos del cliente       
        if (cliente.getId() == null) {

            lblIdCliente.setText("ID not found");
        } else {
            lblIdCliente.setText(cliente.getId().toString());

        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().equals("")) {

            lblTelefono.setText("Telefono not found");
        } else {
            lblTelefono.setText(cliente.getTelefono());

        }

        if (cliente.getLocalidad() == null || cliente.getLocalidad().trim().equals("")) {

            lblLocalidad.setText("Localidad not found");
        } else {
            lblLocalidad.setText(cliente.getLocalidad());

        }

        if (cliente.getDireccion() == null || cliente.getDireccion().trim().equals("")) {

            lblDireccion.setText("Direccion not found");
        } else {
            lblDireccion.setText(cliente.getDireccion());

        }

        if (cliente.getCodigoPostal() == null || cliente.getCodigoPostal().trim().equals("")) {

            lblCodigoPostal.setText("Codigo postal not found");
        } else {
            lblCodigoPostal.setText(cliente.getCodigoPostal());

        }

        if (pedido.getFechaPedido() != null) {

            String fecha = pedido.getFechaPedido().toString();

            lblFecha.setText(fecha);

        } else {
            lblFecha.setText("Fecha not found");

        }

        if (pedido.getHora() == null || pedido.getHora().trim().equals("")) {

            lblHora.setText("Hora not found");
        } else {
            lblHora.setText(pedido.getHora());

        }

    }

    /*public void handleDetallePedidoSelectionChange(ObservableValue observable, Object oldValue, Object newValue) {

    }*/
    /**
     * Action event handler for atras button. It validates pedido data, send it
     * to the business logic tier and updates pedido table view with new pedido
     * data.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void handleVolverAtras(ActionEvent ev) {

        if (pedido.getEstado() != EstadoPedido.ENVIADO) {
            if (!cmbEstado.getSelectionModel().getSelectedItem().equals("  ")) {

                //Tenemos que mostrar dialogo para ver que quiere hacer el usuario. 
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);  //No queremos que salga titulo eb la cabecera
                alert.setContentText("Esta seguro que quiere ese estado?\n" + "Esta operación no se puede deshacer.");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    if (cmbEstado.getSelectionModel().getSelectedItem().equals("PREPARADO")) {

                        try {

                            pedido.setEstado(EstadoPedido.PREPARADO);
                            java.util.Date fecha = new Date();
                            pedido.setFechaPreparado(fecha);
                            this.pedidoLogic.actualizarEstado(pedido);

                            //Load node graph from fxml file
                            FXMLLoader loader
                                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
                            Parent root = (Parent) loader.load();
                            //Get controller for graph 
                            PedidosDesktopFxmlController controller
                                    = ((PedidosDesktopFxmlController) loader.getController());
                            controller.setPedidoLogic(pedidoLogic);
                            controller.setPedido(pedido);
                            controller.handleRefrescarTabla(ev);

                            //Initializes stage
                            controller.initStage(root);
                            //hides UserView stage
                            stage.hide();
                        } catch (Exception ex) {
                            LOGGER.log(Level.SEVERE,
                                    "UI DetallesPedido View: Error opening Pedidos window: {0}",
                                    ex.getMessage());
                        }
                        //If there is an error in the business logic tier show message and
                        //log it.

                    } else if (cmbEstado.getSelectionModel().getSelectedItem().equals("ENVIADO")) {

                        //ACTUALIZAR LA TABLA. estado y fechaEntregado
                        try {

                            pedido.setEstado(EstadoPedido.ENVIADO);
                            java.util.Date fecha = new Date();
                            pedido.setFechaEntregado(fecha);
                            this.pedidoLogic.actualizarEstado(pedido);

                            //Load node graph from fxml file
                            FXMLLoader loader
                                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
                            Parent root = (Parent) loader.load();
                            //Get controller for graph 
                            PedidosDesktopFxmlController controller
                                    = ((PedidosDesktopFxmlController) loader.getController());
                            controller.setPedidoLogic(pedidoLogic);
                            controller.setPedido(pedido);
                            controller.handleRefrescarTabla(ev);

                            //Initializes stage
                            controller.initStage(root);
                            //hides UserView stage
                            stage.hide();
                        } catch (Exception ex) {
                            LOGGER.log(Level.SEVERE,
                                    "UI DetallesPedido View: Error opening Pedidos window: {0}",
                                    ex.getMessage());
                        }
                    }

                }
            } else {  //Si el pedido es igual a enviado, no puede cambiar estado asique solo vera los detalles.
                try {
                    //Load node graph from fxml file
                    FXMLLoader loader
                            = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
                    Parent root = (Parent) loader.load();
                    //Get controller for graph 
                    PedidosDesktopFxmlController controller
                            = ((PedidosDesktopFxmlController) loader.getController());
                    controller.setPedidoLogic(pedidoLogic);
                    //controller.setPedido(pedido);
                    //controller.handleRefrescarTabla(ev);
                    //Initializes stage
                    controller.initStage(root);
                    //hides UserView stage
                    stage.hide();
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE,
                            "UI DetallesPedido View: Error opening Pedidos window: {0}",
                            ex.getMessage());
                }

            }

        } else {

            try {
                //Load node graph from fxml file
                FXMLLoader loader
                        = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
                Parent root = (Parent) loader.load();
                //Get controller for graph 
                PedidosDesktopFxmlController controller
                        = ((PedidosDesktopFxmlController) loader.getController());
                controller.setPedidoLogic(pedidoLogic);
                // controller.setPedido(pedido);
                //Initializes stage
                controller.initStage(root);
                //hides UserView stage
                stage.hide();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE,
                        "UI DetallesPedido View: Error opening Pedidos window: {0}",
                        ex.getMessage());
            }
        }

    }

    @FXML
    public void handleGenerarFactura(ActionEvent ev) {
    }
   /**
     * Text change event handler
     * @param observable the property being observed: TextProperty of TextField.
     * @param oldValue   old String value for the property.
     * @param newValue   new String value for the property.
     */
    private void handleTextChanged(ObservableValue observable,
            String oldValue,
            String newValue) {
        //Validate maximum length for login & name fields
        //If text fields values are too long, show error message and disable 
        //accept button
        if (lblIdCliente.getText().trim().length() > this.MAX_LENGTH
                || lblTelefono.getText().trim().length() > this.MAX_LENGTH || lblLocalidad.getText().trim().length() > this.MAX_LENGTH
                || lblDireccion.getText().trim().length() > this.MAX_LENGTH || lblCodigoPostal.getText().trim().length() > this.MAX_LENGTH || lblFecha.getText().trim().length() > this.MAX_LENGTH
                || lblHora.getText().trim().length() > this.MAX_LENGTH) {
            showErrorAlert("La longitud máxima del campo es de 255 caracteres.");

        }

    }

}
