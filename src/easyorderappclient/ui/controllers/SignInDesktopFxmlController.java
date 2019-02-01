package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.utils.MyAlert;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.logging.Level;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller UI class for SignIn view in SignUpSignIn application. It contains
 * event handlers and initialization code for the view defined in
 * SignInDesktopFxmlController.fxml file.
 *
 * @author Imanol
 */
public class SignInDesktopFxmlController extends GenericController {

	/**
	 * User's login name UI text field.
	 */
	@FXML
	private TextField txtLogin;
	/**
	 * User's password value UI password field.
	 */
	@FXML
	private PasswordField pwdPassword;
	/**
	 * Button to fire action to log in at the UI.
	 */
	@FXML
	private Button btnSignIn;
	/**
	 * Hiperlink to fire action to Sign up at the UI.
	 */
	@FXML
	private Hyperlink hpSignUp;
	/**
	 * Remember login checkbox.
	 */
	@FXML
	private CheckBox chkRememberLogin;
	/**
	 * User's image UI image view.
	 */
	@FXML
	private ImageView imgUser;
	/**
	 * Login error label.
	 */
	@FXML
	private Label lblErrorLogin;
	/**
	 * Password error label.
	 */
	@FXML
	private Label lblErrorPass;
	/**
	 * GridPane for the window.
	 */
	@FXML
	private GridPane gpSignIn;
	/**
	 * Forgotten password hyperlink.
	 */
	@FXML
	private Hyperlink hpForgottenPassword;
	/**
	 * Sign up label
	 */
	@FXML
	private Label lblSignUp;

	/**
	 * Create new Text file to save the Login
	 */
	File file = new File("EmpleadoData.txt");

	/**
	 * Method for initializing SignIn Stage.
	 *
	 * @param root The Parent object representing root node of view graph.
	 */
	public void initStage(Parent root) {
		LOGGER.info("SignInDesktopFxmlController: Initializing Sign in stage");
		//Create a scene 
		Scene scene = new Scene(root);
		stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
		//Associate scene to stage(window)  
		stage.setScene(scene);

		//Set window title
		stage.setTitle("Iniciar sesión");

		//Set window size
		stage.setResizable(false);

		//Set window's envent handlers
		stage.setOnShowing(this::handleWindowShowing);

		//Set control events handle
		btnSignIn.setOnAction(this::handleSignInAction);
		hpSignUp.setOnAction(this::handleSignUpAction);
		pwdPassword.textProperty().addListener(this::textChanged);
		txtLogin.textProperty().addListener(this::textChanged);

		//show window
		stage.show();

	}

	/**
	 * Window event method handler. It implements behavior for WINDOW_SHOWING
	 * type event.
	 *
	 * @param event The window event
	 */
	private void handleWindowShowing(WindowEvent event) {
		LOGGER.info("SignInDesktopFxmlController: Showing window");
		readData();
		//Sign In button is disable
		btnSignIn.setDisable(true);
		//imgUser.setImage(new Image("/signupsignindesktop/ui/img/defaultUserPhoto.png"));

		btnSignIn.setTooltip(new Tooltip("Rellena los campos login y password para habilitar el boton"));

		btnSignIn.setMnemonicParsing(true);
		btnSignIn.setText("_Iniciar Sesión");

		hpSignUp.setMnemonicParsing(true);
		hpSignUp.setText("Darse de alta");
	}

	/**
	 * Action event handler for SignIn button. It validates that user and
	 * password fields are filled. If they are not, an error message dialog is
	 * shown. Otherwise the UserView window is opened.
	 *
	 * @param event The Action event
	 */
	private void handleSignInAction(ActionEvent event) {
		LOGGER.info("SignInDesktopFxmlController: Signing In action.");

		try {
			empleado = empleadoLogic.inicioSesion(txtLogin.getText(), pwdPassword.getText());
			if (chkRememberLogin.isSelected()) {
				RememberUserLogin();
			}
			MyAlert.showAlert(Alert.AlertType.INFORMATION, "¡Bienvenido " + empleado.getLogin() + "!");

			GoToEmpleadoView();
		} catch (BusinessLogicException ex) {
			LOGGER.log(Level.SEVERE, "SignInDesktopFxmlController: Exception signing in employee, {0}", ex.getMessage());

			txtLogin.clear();
			txtLogin.requestFocus();
			pwdPassword.clear();
			MyAlert.showAlert(Alert.AlertType.ERROR, "No se ha podido iniciar sesion. Por favor, revisa tus credenciales.");
		}
	}

	@FXML
	private void handleForgottenPassword(ActionEvent event) {
		LOGGER.info("SignInDesktopFxmlController: Handling forgotten password...");

		try {
			if (!txtLogin.getText().equals("")) {
				empleadoLogic.recuperarContrasegna(txtLogin.getText());
				MyAlert.showAlert(Alert.AlertType.INFORMATION, "Se ha enviado un email con su nueva contraseña.");
			} else {
				lblErrorLogin.setText("* Introduce un login");
				txtLogin.setStyle("-fx-border-color:red;");
			}
		} catch (BusinessLogicException ex) {
			LOGGER.log(Level.SEVERE, "SignInFxmlController: Exception restoring password, {0}.", ex.getMessage());
			MyAlert.showAlert(Alert.AlertType.ERROR, "Ha ocurrido un error, por favor intentelo mas tarde.");
		}

		LOGGER.info("SignInDesktopFxmlController: Handled forgotten password.");

	}

	/**
	 * Action event handler for SignUp hyperlink. It opens the SignUp window.
	 *
	 * @param event The Action event
	 */
	private void handleSignUpAction(ActionEvent event) {
		LOGGER.info("SignInDesktopFxmlController: Signin up action.");
		try {
			//Load node graph from fxml file 
			FXMLLoader loader
				= new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/SignUpDesktopFXMLDocument.fxml"));

			Parent root = loader.load();

			//Get controller for graph 
			SignUpDesktopFxmlController controller
				= ((SignUpDesktopFxmlController) loader.getController());
			controller.setEmpleadoLogic(empleadoLogic);
			//Initializes stage
			controller.initStage(root);
			//hides login stage
			//stage.hide();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
				"UI SignInDesktopFxmlController: Error opening SignUp window: {0}",
				e.getMessage());
			MyAlert.showAlert(Alert.AlertType.ERROR, "Error abriendo la ventana.");
		}
	}

	/**
	 * Text changed event handler. It validates that login and password fields
	 * has any content to enable/disable SignIn button. Also limit the number of
	 * characters on the Login textField and Password textField and changes the
	 * border color of txtFields
	 *
	 * @param observable The value being observed.
	 * @param oldValue The old value of the observable.
	 * @param newValue The new value of the observable.
	 */
	private void textChanged(ObservableValue observable, String oldValue,
		String newValue) {
		if (txtLogin.getText().trim().length() > this.MAX_LENGTH
			|| pwdPassword.getText().trim().length() > this.MAX_LENGTH) {

			LOGGER.info("UI SignInDesktopFxmlController: The maximum length of the field is 255 characters: {0}");
			btnSignIn.setDisable(true);
		} //If text fields are empty disable Sign In button
		else if (txtLogin.getText().trim().isEmpty() || pwdPassword.getText().trim().isEmpty()) {
			btnSignIn.setDisable(true);
		} //Enable Sign In button
		else {
			btnSignIn.setDisable(false);
		}

		//Put Login textField border color to default color
		if (!txtLogin.getText().trim().isEmpty()) {
			txtLogin.setStyle("");
			lblErrorLogin.setVisible(false);
		}
		//Put Password textField border color to default color
		if (!pwdPassword.getText().trim().isEmpty()) {
			pwdPassword.setStyle("");
			lblErrorPass.setVisible(false);
		}

		//Limit the number of characters on the Login textField
		if (txtLogin.getLength() == MAX_LENGTH_LOGIN) {
			txtLogin.setText(txtLogin.getText().substring(0, MAX_LENGTH_LOGIN));
		}
		//Limit the number of characters on the Password passwordField
		if (pwdPassword.getLength() == MAX_LENGTH_PASSWORD) {
			pwdPassword.setText(pwdPassword.getText().substring(0, MAX_LENGTH_PASSWORD));
		}

	}

	/**
	 * Method to open UserView window
	 *
	 */
	private void GoToEmpleadoView() {
		try {
			//Load node graph from fxml file 
			FXMLLoader loader
				= new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/EmpleadoDesktopFXMLDocument.fxml"));

			Parent root = loader.load();

			//Get controller for graph 
			EmpleadoDesktopFxmlController controller
				= ((EmpleadoDesktopFxmlController) loader.getController());
			controller.setEmpleadoLogic(empleadoLogic);
			controller.setEmpleado(empleado);

			//Initializes stage
			controller.initStage(root);

			//hides login stage
			stage.hide();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,
				"UI SignUpDesktopFxmlController: Error opening UserView window.",
				e.getMessage());
			MyAlert.showAlert(Alert.AlertType.ERROR, "Error abriendo la ventana.");

		}
	}

	/**
	 * Method to save the users Login in TXT file.
	 *
	 */
	public void RememberUserLogin() {
		try {
			//If file does not exist we create a new one
			if (!file.exists()) {
				file.createNewFile();
			}
			//Instance of BufferWriter
			BufferedWriter bw;

			//Initialization of BufferedWriter with FileWriter as parameter with "file" as parameter
			bw = new BufferedWriter(new FileWriter(file));

			//we write the data that we want to save in the TXT file
			bw.write(txtLogin.getText());

			//Close to save the user data
			bw.close();

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "SignInDesktopFxmlController: Error saving user data.", ex.getMessage());

		}
	}

	/**
	 * Method to put in the txtLogin the users Login saved in a TXT file.
	 *
	 */
	private void readData() {
		String[] datos;
		//To save the data temporarily
		String read = null;
		//If file exist we read the user data
		if (file.exists()) {
			try {
				Scanner reader = new Scanner(file);
				while (reader.hasNextLine()) {
					read = reader.next();
				}
				if (read != null) {
					datos = read.split(",");
					txtLogin.setText(datos[0]);

				}
			} catch (FileNotFoundException ex) {
				LOGGER.log(Level.SEVERE, "SignInDesktopFxmlController: Error opening file.", ex.getMessage());
			}
		}
	}

}
