package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.businessLogic.EmpleadoLogicFactory;
import easyorderappclient.businessLogic.FTPLogicFactory;
import easyorderappclient.businessLogic.PedidoLogicFactory;
import easyorderappclient.businessLogic.ProductsManagerFactory;
import static easyorderappclient.ui.controllers.GenericController.LOGGER;
import easyorderappclient.utils.MyAlert;
import easyorderappclient.utils.MyRegex;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.logging.Level;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Imanol
 */
public class EmpleadoDesktopFxmlController extends GenericController {

    /**
     * Cambiar contrasegna hyperlink.
     */
    @FXML
    private Hyperlink hpCambiarContrasegna;
    /**
     * Text field for email.
     */
    @FXML
    private TextField txtEmail;
    /**
     * Text field for fullname.
     */
    @FXML
    private TextField txtFullname;
    /**
     * Text field for phone number.
     */
    @FXML
    private TextField txtTelefono;
    /**
     * Datepicker for birthdate.
     */
    @FXML
    private DatePicker dtpFechaDeNacimiento;
    /**
     * Editar and Guardar button.
     */
    @FXML
    private Button btnEditarGuardar;
    /**
     * Eliminar and Cancelar button.
     */
    @FXML
    private Button btnEliminarCancelar;

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
    private MenuItem itemMiPerfil;
    @FXML
    private MenuItem itemProductos;
    @FXML
    private MenuItem itemCerrarSesion;
    @FXML
    private MenuItem itemFacturas;
    @FXML
    private MenuItem itemPedidos;

    /**
     * Method for initializing SignIn Stage.
     *
     * @param root The Parent object representing root node of view graph.
     */
    public void initStage(Parent root) {
        LOGGER.log(Level.INFO, "EmpleadoDesktopFxmlController: Initializing stage.");
        // Create a scene associated to the node graph root	
        Scene scene = new Scene(root);
        // Associate scene to the stage
        stage = new Stage();
        stage.setScene(scene);
        // Set window properties
        stage.setTitle("EasyOrderApp");
        stage.setResizable(false);
        //Menu Pedidos
        itemPedidos.setOnAction(this::pedidosWindow);
        //Menu Productos
        itemProductos.setOnAction(this::productosWindow);
        //Menu Empleados
        //menuEmpleados.setOnAction(this::empleadosWindow);
        //Menu item cerrar sesion
        itemCerrarSesion.setOnAction(this::cerrarSesion);
        //Menu item para facturas
        itemFacturas.setOnAction(this::facturasWindow);
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set window's events handlers
        stage.setOnShowing(this::handleWindowShowing);
        // Set control events handlers
        txtEmail.textProperty().addListener(this::textChanged);
        txtFullname.textProperty().addListener(this::textChanged);
        txtTelefono.textProperty().addListener(this::textChanged);

        // Show window
        stage.show();
    }

    /**
     * Window event method handler. It implements behavior for WINDOW_SHOWING
     * type event.
     *
     * @param event The window event
     */
    private void handleWindowShowing(WindowEvent event) {
        LOGGER.log(Level.INFO, "EmpleadoDesktopFxmlController: Setting default window state.");

        // Fill textviews with employee data
        txtEmail.setText(empleado.getEmail());
        txtFullname.setText(empleado.getFullname());
        LocalDate ldFechaDeNacimiento = empleado.getFechaDeNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dtpFechaDeNacimiento.setValue(ldFechaDeNacimiento);
        txtTelefono.setText(empleado.getTelefono());

    }

    /**
     * Text changed event handler. It changes the border colors to default and
     * hides the error labels when some correction is done on a textfield.
     *
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void textChanged(ObservableValue observable, String oldValue, String newValue) {
        if (!txtEmail.getText().trim().isEmpty()) {
            txtEmail.setStyle("");
        }
        if (!txtFullname.getText().trim().isEmpty()) {
            txtFullname.setStyle("");
        }
        if (!txtTelefono.getText().trim().isEmpty()) {
            txtTelefono.setStyle("");
        }
    }

    /**
     * Action event handler for Editar and Guardar actions.
     *
     * @param event The action event.
     */
    @FXML
    private void handleEditarGuardarAction(ActionEvent event) {
        if (btnEditarGuardar.getText().equals("Editar")) {
            txtEmail.setEditable(true);
            txtFullname.setEditable(true);
            dtpFechaDeNacimiento.setEditable(true);
            txtTelefono.setEditable(true);

            btnEditarGuardar.setText("Guardar");
            btnEliminarCancelar.setText("Cancelar");

            LOGGER.info("EmpleadoDesktopFxmlController: handled edit action.");
        } else if (btnEditarGuardar.getText().equals("Guardar")) {

            try {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea modificar sus datos?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    LOGGER.info("EmpleadoDesktopFxmlController: handling update action.");

                    // Validate fields' input
                    boolean validFields = true;
                    try {
                        MyRegex.validateEmail(txtEmail.getText());
                        empleado.setEmail(txtEmail.getText());
                    } catch (IllegalArgumentException ex) {
                        txtEmail.setStyle("-fx-border-color:red;");
                        validFields = false;
                    }
                    try {
                        MyRegex.validateFullname(txtFullname.getText());
                        empleado.setFullname(txtFullname.getText());
                    } catch (IllegalArgumentException ex) {
                        txtFullname.setStyle("-fx-border-color:red;");
                        validFields = false;
                    }
                    java.sql.Date d = java.sql.Date.valueOf(dtpFechaDeNacimiento.getValue());
                    empleado.setFechaDeNacimiento(d);
                    try {
                        MyRegex.validateTelefono(txtTelefono.getText());
                        empleado.setTelefono(txtTelefono.getText());
                        empleado.setTelefono(txtTelefono.getText());
                    } catch (IllegalArgumentException ex) {
                        txtTelefono.setStyle("-fx-border-color:red;");
                        validFields = false;
                    }

                    if (validFields) {
                        empleadoLogic.updateEmpleado(empleado);

                        MyAlert.showAlert(AlertType.INFORMATION,"Perfil actualizado con exito.");
                        
                        txtEmail.setEditable(false);
                        txtFullname.setEditable(false);
                        dtpFechaDeNacimiento.setEditable(false);
                        txtTelefono.setEditable(false);

                        btnEditarGuardar.setText("Editar");
                        btnEliminarCancelar.setText("Eliminar");
                    }
                    LOGGER.info("EmpleadoDesktopFxmlController: handled update action.");
                }
            } catch (BusinessLogicException ex) {
                LOGGER.log(Level.SEVERE, "EmpleadoDesktopFxmlController: Error updating employee, {0}.", ex.getMessage());
                MyAlert.showAlert(AlertType.ERROR, "Ha ocurrido un error actualizando los datos, intentelo de nuevo.");
            }

        }

    }

    /**
     * Event handler for Eliminar and Cancelar action.
     *
     * @param event The action event.
     */
    @FXML
    private void handleEliminarCancelarAction(ActionEvent event) {
        if (btnEliminarCancelar.getText().equals("Eliminar")) {
            try {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea darse de baja?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    LOGGER.info("EmpleadoDesktopFxmlController: handling delete action.");

                    empleadoLogic.deleteEmpleado(empleado);
                    LOGGER.info("EmpleadoDesktopFxmlController: handled delete action.");

                    openSignInWindow();
                }
            } catch (BusinessLogicException ex) {
                LOGGER.log(Level.SEVERE, "EmpleadoDesktopFxmlController: Error deleting employee, {0}.", ex.getMessage());
            }
        } else if (btnEliminarCancelar.getText().equals("Cancelar")) {
            LOGGER.info("EmpleadoDesktopFxmlController: handled cancel action.");
            txtEmail.setEditable(false);
            txtFullname.setEditable(false);
            dtpFechaDeNacimiento.setEditable(false);
            txtTelefono.setEditable(false);

            btnEditarGuardar.setText("Editar");
            btnEliminarCancelar.setText("Eliminar");
        }
    }

    /**
     * Opens Sign In window.
     */
    private void openSignInWindow() {
        LOGGER.info("SignUpDesktopFxmlController: Opening SignIn window action.");
        try {
            // Load node graph from fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/SignInDesktopFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            // Get controller for graph
            SignInDesktopFxmlController controller = ((SignInDesktopFxmlController) loader.getController());
            controller.setEmpleadoLogic(empleadoLogic);
            // Initialize stage
            controller.initStage(root);
            // Hide sign up stage
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "SignUpDesktopFxmlController: Error opening SignIn window.", ex.getMessage());
        }
    }

    /**
     * Action event handler for Cambiar contrasegna action.
     *
     * @param event The action event.
     */
    @FXML
    private void handleCambiarContrasegnaAction(ActionEvent event) {
        LOGGER.info("EmpleadoDesktopFxmlController: Handling cambiar contrasegna action...");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/CambiarContrasegnaFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            CambiarContrasegnaFxmlController controller = ((CambiarContrasegnaFxmlController) loader.getController());
            controller.setEmpleadoLogic(empleadoLogic);
            controller.setEmpleado(empleado);
            controller.initStage(root);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "EmpleadoDesktopFxmlController: Error opening cambiar contrasegna window, {0}.", ex.getMessage());
        }

        LOGGER.info("EmpleadoDesktopFxmlController: Handled cambiar contrasegna action.");
    }

    /**
     * Para ir desde el menu, a la view de Pedidos.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void pedidosWindow(ActionEvent ev) {
        LOGGER.info("ClickOn Pedidos Menu");
        try {
            //Load node graph from fxml file
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/PedidosDesktopFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller for graph 
            PedidosDesktopFxmlController controller
                    = ((PedidosDesktopFxmlController) loader.getController());
            pedidoLogic = PedidoLogicFactory.createPedidoLogic("REST_WEB_CLIENT");
            controller.setPedidoLogic(pedidoLogic);
            controller.setEmpleado(empleado);

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
                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/ProductDesktopFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller for graph 
            ProductController controller
                    = ((ProductController) loader.getController());
            productsManager = ProductsManagerFactory.createProductsManager("REST_WEB_CLIENT");

            controller.setProductManager(productsManager);
            controller.setEmpleado(empleado);

            //Initializes stage
            controller.initStage(root);
            //hides UserView stage
            stage.hide();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "UI DetallesProducto View: Error opening Productos window: {0}",
                    ex.getMessage());
        }
    }

    /**
     * Action event handler for para ir a Facturas.
     *
     * @param ev The ActionEvent object for the event.
     */
    public void facturasWindow(ActionEvent ev) {
        LOGGER.info("ClickOn Productos Menu");
        try {
            //Load node graph from fxml file
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/FacturasDesktopFXMLDocument.fxml"));
            Parent root = (Parent) loader.load();
            //Get controller for graph 
            FacturasDesktopFxmlController controller
                    = ((FacturasDesktopFxmlController) loader.getController());
            ftpLogic = FTPLogicFactory.createFTPLogicImplementation();
            controller.setFTPLogic(ftpLogic);
            controller.setEmpleado(empleado);

            //Initializes stage
            controller.initStage(root);
            //hides UserView stage
            stage.hide();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "UI Facturas View: Error opening Facturas window: {0}",
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

            // Load node graph from fxml file
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/SignInDesktopFXMLDocument.fxml"));
                Parent root = (Parent) loader.load();
                // Get controller for graph
                SignInDesktopFxmlController controller = ((SignInDesktopFxmlController) loader.getController());
                empleadoLogic = EmpleadoLogicFactory.createEmpleadoLogicImplementation();
                controller.setEmpleadoLogic(empleadoLogic);
                // Initialize stage
                controller.initStage(root);
                //hides UserView stage
                stage.hide();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE,
                        "UI Empleado View: Error cerrando sesion: {0}",
                        ex.getMessage());
            }

        }

    }

}
