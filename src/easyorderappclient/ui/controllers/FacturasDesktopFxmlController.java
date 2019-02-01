package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.businessLogic.EmpleadoLogicFactory;
import easyorderappclient.businessLogic.FTPLogic;
import easyorderappclient.businessLogic.FTPLogicFactory;
import easyorderappclient.businessLogic.PedidoLogicFactory;
import easyorderappclient.businessLogic.ProductsManagerFactory;
import easyorderappclient.transferObjects.MyFtpFile;
import easyorderappclient.utils.MyAlert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class for ftp management view. It contains event handlers and
 * initialization code for the view defined in FacturasDesktopFXMLDocument.fxml
 * file.
 *
 * @author Imanol
 */
public class FacturasDesktopFxmlController extends GenericController {

	/**
	 * FTP content tree view.
	 */
	@FXML
	private TreeView<MyFtpFile> trvFacturas;
	/**
	 * Upload file button.
	 */
	@FXML
	private Button btnSubir;
	/**
	 * Download file button.
	 */
	@FXML
	private Button btnDescargar;
	/**
	 * Delete file button.
	 */
	@FXML
	private Button btnEliminar;
	/**
	 * Create directory button.
	 */
	@FXML
	private Button btnCrearDirectorio;
	/**
	 * Directory name text field.
	 */
	@FXML
	private TextField txtDirectorio;

	/**
	 * FTPLogic object.
	 */
	private FTPLogic ftpLogic;
	/**
	 * MenuBar menuBar
	 */
	@FXML
	private MenuBar menuBar;
	/**
	 * Menu menu
	 */
	@FXML
	private Menu menuMenu;
	/**
	 * MenuItem MiPerfil
	 */
	@FXML
	private MenuItem itemMiPerfil;
	/**
	 * MenuItem CerrarSesion
	 */
	@FXML
	private MenuItem itemCerrarSesion;
	/**
	 * Menu Pedidos
	 */
	@FXML
	private Menu menuPedidos;
	/**
	 * MenuItem Pedidos
	 */
	@FXML
	private MenuItem itemPedidos;
	/**
	 * Menu Productos
	 */
	@FXML
	private Menu menuProductos;
	/**
	 * MenuItem Productos
	 */
	@FXML
	private MenuItem itemProductos;

	/**
	 * Method for initializating FTPFacturas stage.
	 *
	 * @param root The Parent object representing root node of view graph.
	 */
	public void initStage(Parent root) {
		LOGGER.info("FacturasDesktopFxmlController: Initializing stage...");

		// Create a scene associated to the node graph root	
		Scene scene = new Scene(root);
		// Associate scene to the stage
		stage = new Stage();
		stage.setScene(scene);
		// Set window properties
		stage.setTitle("Facturas FTP");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		// Set window's events handlers
		stage.setOnShowing(this::handleWindowShowing);
		// Set control events handlers

		//Menu Pedidos
		itemPedidos.setOnAction(this::pedidosWindow);
		//Menu Productos
		itemProductos.setOnAction(this::productosWindow);
		//Menu Empleados
		itemMiPerfil.setOnAction(this::empleadosWindow);
		//Menu item cerrar sesion
		itemCerrarSesion.setOnAction(this::cerrarSesion);

		// Initialize FTP server
		ftpLogic = FTPLogicFactory.createFTPLogicImplementation();
		ftpLogic.iniciarSesion();
		// Show window
		stage.show();

		LOGGER.info("FacturasDesktopFxmlController: Initialized stage.");
	}

	/**
	 * This method add leaves to a branch item.
	 *
	 * @param branchItem The branch item
	 * @return The branch with its leaves.
	 */
	private TreeItem addLeaves(TreeItem<MyFtpFile> branchItem) {
		ArrayList<MyFtpFile> branch = ftpLogic.listarFicheros(branchItem.getValue().getAbsolutePath());

		branch.forEach((leaf) -> {
			if (leaf.isDirectory()) {
				TreeItem directory = addLeaves(new TreeItem<>(leaf));
				branchItem.getChildren().add(directory);
			} else {
				TreeItem<MyFtpFile> item = new TreeItem<>(leaf);
				branchItem.getChildren().add(item);
			}
		});
		return branchItem;
	}

	private void refreshTreeView() {
		// Create a custom file object for root
		MyFtpFile ftpRoot = new MyFtpFile("", "/", true);
		// Create a TreeItem for root with custom file object
		TreeItem<MyFtpFile> rootItem = new TreeItem<>(ftpRoot);
		rootItem.setExpanded(true);

		trvFacturas.setRoot(addLeaves(rootItem));
	}

	/**
	 * Initializes window state.
	 *
	 * @param event The window event.
	 */
	private void handleWindowShowing(WindowEvent event) {
		LOGGER.info("FacturasDesktopFxmlController: Setting default window state...");
		// Set tree view
		// Create a custom file object for root
		MyFtpFile ftpRoot = new MyFtpFile("", "/", true);
		// Create a TreeItem for root with custom file object
		TreeItem<MyFtpFile> rootItem = new TreeItem<>(ftpRoot);
		rootItem.setExpanded(true);

		trvFacturas.setRoot(addLeaves(rootItem));
	}

	/**
	 * Action event handler for upload button.
	 *
	 * @param event The ActionEvent object for the event.
	 */
	@FXML
	private void handleSubirAction(ActionEvent event) {
		LOGGER.info("FacturasDesktopFxmlController: Handling subir archivo action...");

		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			try {
				TreeItem<MyFtpFile> ftpItem = trvFacturas.getSelectionModel().getSelectedItem();
				if (ftpItem == null) {
					ftpItem = trvFacturas.getRoot();
				}
				String path = ftpItem.getValue().getAbsolutePath() + "/" + txtDirectorio.getText();
				if (ftpLogic.subirArchivo(path, file)) {
					// Actualizar tabla
					MyFtpFile ftpFile = new MyFtpFile(file.getName(), file.getAbsolutePath(), false);
					//trvFacturas.getRoot().getChildren().add(new TreeItem<>(ftpFile));
					ftpItem.getChildren().add(new TreeItem<>(ftpFile));
					//trvFacturas.refresh();
					refreshTreeView();

					MyAlert.showAlert(Alert.AlertType.INFORMATION, "Archivo subido con exito.");
				} else {
					throw new BusinessLogicException();
				}
			} catch (BusinessLogicException ex) {
				LOGGER.log(Level.SEVERE, "FacturasDesktopFxmlController: Exception uploading file, {0}.", ex.getMessage());
				MyAlert.showAlert(Alert.AlertType.ERROR, "Error subiendo el archivo.");
			}
		}
		LOGGER.info("FacturasDesktopFxmlController: Handled subir archivo action.");
	}

	/**
	 * Action event handler for download button.
	 *
	 * @param event The ActionEvent object for the event.
	 */
	@FXML
	private void handleDescargarAction(ActionEvent event) {
		LOGGER.info("FacturasDesktopFxmlController: Handling descargar archivo action...");

		try {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File file = directoryChooser.showDialog(stage);

			TreeItem<MyFtpFile> ftpItem = trvFacturas.getSelectionModel().getSelectedItem();
			FileOutputStream o = new FileOutputStream(file.getAbsolutePath() + "/" + ftpItem.getValue().getName());

			if (ftpLogic.descargarArchivo(ftpItem.getValue().getAbsolutePath(), o)) {
				MyAlert.showAlert(Alert.AlertType.INFORMATION, "Archivo descargado con exito.");
			} else {
				throw new BusinessLogicException();
			}
		} catch (FileNotFoundException | BusinessLogicException ex) {
			LOGGER.log(Level.SEVERE, "FacturasDesktopFxmlController: Exception downloading file, {0}.", ex.getMessage());
			MyAlert.showAlert(Alert.AlertType.ERROR, "Error descargando el archivo.");
		}

		LOGGER.info("FacturasDesktopFxmlController: Handled descargar archivo action.");
	}

	/**
	 * Action event handler for deleting button.
	 *
	 * @param event The ActionEvent object for the event.
	 */
	@FXML
	private void handleEliminarAction(ActionEvent event) {
		LOGGER.info("FacturasDesktopFxmlController: Handling eliminar archivo action...");

		TreeItem<MyFtpFile> ftpItem = trvFacturas.getSelectionModel().getSelectedItem();

		if (ftpItem.getValue().isDirectory()) {
			Optional<ButtonType> result = MyAlert.showAlert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el directorio definitivamente?");

			if (result.get() == ButtonType.OK) {
				try {
					if (ftpItem.getChildren().isEmpty()) {
						if (ftpLogic.borrarDirectorio(ftpItem.getValue().getAbsolutePath())) {

							//trvFacturas.getRoot().getChildren().remove(ftpItem);
							ftpItem.getChildren().remove(ftpItem);
							//trvFacturas.refresh();
							refreshTreeView();
							MyAlert.showAlert(Alert.AlertType.INFORMATION, "Directorio borrado con exito.");
						} else {
							throw new BusinessLogicException();
						}
					} else {
						MyAlert.showAlert(Alert.AlertType.ERROR, "No se pudo borrar el directorio, pruebe a borrar los ficheros que contiene.");
					}
				} catch (BusinessLogicException ex) {
					LOGGER.log(Level.SEVERE, " FacturasDesktopFxmlController: Error deleting directory, {0}.", ex.getMessage());
					MyAlert.showAlert(Alert.AlertType.ERROR, "Error borrando el directorio.");
				}
			}
		} else {
			Optional<ButtonType> result = MyAlert.showAlert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el archivo definitivamente?");

			if (result.get() == ButtonType.OK) {

				try {
					if (ftpLogic.borrarArchivo(ftpItem.getValue().getPath())) {
						//trvFacturas.getRoot().getChildren().remove(ftpItem);
						ftpItem.getChildren().remove(ftpItem);
						//trvFacturas.refresh();
						refreshTreeView();
						LOGGER.info("FacturasDesktopFxmlController: Archivo eliminado con exito.");
						MyAlert.showAlert(Alert.AlertType.INFORMATION, "Archivo eliminado con exito.");
					} else {
						throw new BusinessLogicException();
					}
				} catch (BusinessLogicException ex) {
					LOGGER.log(Level.SEVERE, "FacturasDesktopFxmlController: Error borrando el archivo, {0}.", ex.getMessage());
					MyAlert.showAlert(Alert.AlertType.ERROR, "Error borrando el archivo.");
				}
			}
		}
		LOGGER.info("FacturasDesktopFxmlController: Handled eliminar archivo action.");
	}

	/**
	 * Action event handler for creating directory button.
	 *
	 * @param event The ActionEvent object for the event.
	 */
	@FXML
	private void handleCrearDirectorioAction(ActionEvent event) {
		LOGGER.info("FacturasDesktopFxmlController: Handling crear directorio action...");
		if (!txtDirectorio.getText().equals("") && txtDirectorio.getText().length() < MAX_LENGTH) {

			TreeItem<MyFtpFile> ftpItem = trvFacturas.getSelectionModel().getSelectedItem();
			if (ftpItem == null) {
				ftpItem = trvFacturas.getRoot();
			}
			String path = ftpItem.getValue().getAbsolutePath() + "/" + txtDirectorio.getText();
			try {
				ftpLogic.crearDirectorio(path);

				TreeItem<MyFtpFile> ti = new TreeItem<>(new MyFtpFile(txtDirectorio.getText(), ftpItem.getValue().getAbsolutePath(), true));
				ftpItem.getChildren().add(ti);
				//trvFacturas.refresh();
				refreshTreeView();

				txtDirectorio.clear();
			} catch (BusinessLogicException ex) {
				LOGGER.log(Level.SEVERE, "FacturasDesktopFxmlController: Error creating directory, {0}.", ex.getMessage());
				MyAlert.showAlert(Alert.AlertType.ERROR, "Error creando directorio.");
			}
		} else {
			MyAlert.showAlert(Alert.AlertType.ERROR, "Por favor, introduce un nombre valido.");
		}
		LOGGER.info("FacturasDesktopFxmlController: Handled crear directorio action.");
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
			// Close ftp server
			ftpLogic.cerrarSesion();
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
			// Close ftp server
			ftpLogic.cerrarSesion();
			//hides UserView stage
			stage.hide();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
				"UI DetallesPedido View: Error opening Productos window: {0}",
				ex.getMessage());
		}
	}

	/**
	 * Para ir desde el menu, a la view de datos del perfil de empleado.
	 *
	 * @param ev The ActionEvent object for the event.
	 */
	public void empleadosWindow(ActionEvent ev) {
		LOGGER.info("ClickOn Empleado datos Menu");
		try {
			//Load node graph from fxml file
			FXMLLoader loader
				= new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/EmpleadoDesktopFXMLDocument.fxml"));
			Parent root = (Parent) loader.load();
			//Get controller for graph 
			EmpleadoDesktopFxmlController controller
				= ((EmpleadoDesktopFxmlController) loader.getController());
			empleadoLogic = EmpleadoLogicFactory.createEmpleadoLogicImplementation();
			controller.setEmpleadoLogic(empleadoLogic);
                         controller.setEmpleado(empleado);
			//Initializes stage
			controller.initStage(root);
			// Close ftp server
			ftpLogic.cerrarSesion();
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

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
				controller.setEmpleadoLogic(empleadoLogic);
                                controller.setEmpleado(empleado);
				// Initialize stage
				controller.initStage(root);
				// Close ftp server
				ftpLogic.cerrarSesion();
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
