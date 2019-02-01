package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.utils.MyAlert;
import easyorderappclient.utils.MyRegex;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Imanol
 */
public class CambiarContrasegnaFxmlController extends GenericController {

	/**
	 * Current password text field.
	 */
	@FXML
	private PasswordField pwdActual;
	/**
	 * New password text field.
	 */
	@FXML
	private PasswordField pwdNueva;
	/**
	 * New password confirmation text field.
	 */
	@FXML
	private PasswordField pwdConfirmar;
	/**
	 * Aceptar button.
	 */
	@FXML
	private Button btnAceptar;
	/**
	 * Cancelar button.
	 */
	@FXML
	private Button btnCancelar;

	/**
	 * Method for initializing Sign Up Desktop View {@link Stage}.
	 *
	 * @param root The parent object representing root node of view graph.
	 */
	public void initStage(Parent root) {
		LOGGER.info("CambiarContrasegnaFxmlController: Initializing stage...");

		// Create a scene associated to the node graph root	
		Scene scene = new Scene(root);
		// Associate scene to the stage
		stage = new Stage();
		stage.setScene(scene);
		// Set window properties
		stage.setTitle("EasyOrderApp");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		// Set window's events handlers
		stage.setOnShowing(this::handleWindowShowing);
		// Set control events handlers

		// Show window
		stage.show();

		LOGGER.info("CambiarContrasegnaFxmlController: Initialized stage.");
	}

	/**
	 * Window event method handler. It implements behaviour for WINDOW_SHOWING
	 * type event.
	 *
	 * @param event The window event
	 */
	private void handleWindowShowing(WindowEvent event) {
		LOGGER.info("CambiarContrasegnaFxmlController: Setting default window stage...");

		LOGGER.info("CambiarContrasegnaFxmlController: Set default window stage.");
	}

	/**
	 * Action event handler for aceptar action.
	 *
	 * @param event The action event.
	 */
	@FXML
	private void handleAceptarAction(ActionEvent event) {
		LOGGER.info("CambiarContrasegnaFxmlController: Handling aceptar action...");

		try {
			try {
				MyRegex.validatePassword(pwdActual.getText());
				MyRegex.validatePassword(pwdNueva.getText());
				if (!pwdNueva.getText().trim().equals(pwdConfirmar.getText().trim())) {
					throw new IllegalArgumentException("Las contraseñas no coinciden.");
				}
				empleadoLogic.cambiarContrasegna(empleado.getLogin(), pwdActual.getText(), pwdNueva.getText());
				MyAlert.showAlert(Alert.AlertType.INFORMATION, "Contraseña cambiada con exito. Se ha enviado ha enviado un email a su correo.");
				stage.close();
			} catch (IllegalArgumentException ex) {
				LOGGER.log(Level.SEVERE, "CambiarContrasegnaFxmlController: {0}", ex.getMessage());
				MyAlert.showAlert(Alert.AlertType.ERROR, "Error: " + ex.getMessage());
			}
		} catch (BusinessLogicException ex) {
			Logger.getLogger(CambiarContrasegnaFxmlController.class.getName()).log(Level.SEVERE, null, ex);
			LOGGER.log(Level.SEVERE, "CambiarContrasegnaFxmlController: Error changing password, {0}.", ex.getMessage());
			MyAlert.showAlert(Alert.AlertType.ERROR, "Ha ocurrido un error, intentalo mas tarde.");
		}

		LOGGER.info("CambiarContrasegnaFxmlController: Handled aceptar action.");
	}

	/**
	 * Action event hendler for cancelar action.
	 *
	 * @param event The action event
	 */
	@FXML
	private void handleCancelarAction(ActionEvent event) {
		LOGGER.info("CambiarContrasegnaFxmlController: Handling cancelar action...");
		stage.close();

		LOGGER.info("CambiarContrasegnaFxmlController: Handled cancelar action.");
	}

}
