package easyorderappclient.ui.controllers;

import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.transferObjects.Empleado;
import easyorderappclient.transferObjects.UserPrivilege;
import easyorderappclient.transferObjects.UserStatus;
import easyorderappclient.utils.MyAlert;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * SignUpDesktopFXMLDocument FXML Controller class.
 *
 * @author Imanol
 */
public class SignUpDesktopFxmlController extends GenericController {

	/**
	 * Main pane of the window.
	 */
	@FXML
	private GridPane gpSignUp;
	/**
	 * Employee's fullname UI text field.
	 */
	@FXML
	private TextField txtFullname;
	/**
	 * Employee's email UI text field.
	 */
	@FXML
	private TextField txtEmail;
	/**
	 * Employee's login UI text field.
	 */
	@FXML
	private TextField txtLogin;
	/**
	 * Employee's password UI password field.
	 */
	@FXML
	private PasswordField pwdPassword;
	/**
	 * Employee's password confirmation UI password field.
	 */
	@FXML
	private PasswordField pwdConfirmPassword;
	/**
	 * Employee's phone UI text field
	 */
	@FXML
	private TextField txtTelefono;
	/**
	 * Employee's birthday UI datepicker
	 */
	@FXML
	private DatePicker dtpFechaDeNacimiento;
	/**
	 * Sign up button.
	 */
	@FXML
	private Button btnSignUp;
	/**
	 * Sign in hyperlink.
	 */
	@FXML
	private Hyperlink hpSignIn;
	/**
	 * Error label for email field.
	 */
	@FXML
	private Label lblErrorEmail;
	/**
	 * Error label for login field.
	 */
	@FXML
	private Label lblErrorLogin;
	/**
	 * Error label for password field.
	 */
	@FXML
	private Label lblErrorPassword;
	/**
	 * Error label for fullname
	 */
	@FXML
	private Label lblErrorFullname;

	/**
	 * Method for initializing Sign Up Desktop View {@link Stage}.
	 *
	 * @param root The parent object representing root node of view graph.
	 */
	public void initStage(Parent root) {
		LOGGER.info("SignUpDesktopFxmlController: Initializing stage...");

		// Create a scene associated to the node graph root	
		Scene scene = new Scene(root);
		// Associate scene to the stage
		stage = new Stage();
		stage.setScene(scene);
		// Set window properties
		stage.setTitle("Sign Up");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		// Set window's events handlers
		stage.setOnShowing(this::handleWindowShowing);
		// Set control events handlers
		txtLogin.textProperty().addListener(this::textChanged);
		txtEmail.textProperty().addListener(this::textChanged);
		pwdPassword.textProperty().addListener(this::textChanged);
		txtFullname.textProperty().addListener(this::textChanged);
		dtpFechaDeNacimiento.valueProperty().addListener((observable, oldValue, newValue) -> this.textChanged(observable, oldValue, newValue));
		txtTelefono.textProperty().addListener(this::textChanged);
		// Show window
		stage.show();

		LOGGER.info("SignUpDesktopFxmlController: Initialized stage.");
	}

	/**
	 * Window event method handler. It implements behaviour for WINDOW_SHOWING
	 * type event.
	 *
	 * @param event The window event
	 */
	private void handleWindowShowing(WindowEvent event) {
		LOGGER.info("SignUpDesktopFxmlController: Setting default window state.");

		// Full name
		lblErrorFullname.setVisible(false);
		txtFullname.setStyle("");

		// Email
		txtEmail.setStyle("");
		lblErrorEmail.setVisible(false);

		// Login
		txtLogin.setStyle("");
		lblErrorLogin.setVisible(false);

		// Password
		pwdPassword.setStyle("");
		pwdConfirmPassword.setStyle("");
		lblErrorPassword.setVisible(false);

		btnSignUp.setMnemonicParsing(true);
		btnSignUp.setText("_Darse de alta");
		hpSignIn.setMnemonicParsing(true);
		hpSignIn.setText("_Iniciar sesión");
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
		// Set fullname TextField to default
		if (!txtFullname.getText().trim().isEmpty()) {
			txtFullname.setStyle("");
			lblErrorFullname.setVisible(false);
		}
		// Set email TextField to default
		if (!txtEmail.getText().trim().isEmpty()) {
			txtEmail.setStyle("");
			lblErrorEmail.setVisible(false);
		}
		// Set login TextField to default
		if (!txtLogin.getText().trim().isEmpty()) {
			txtLogin.setStyle("");
			lblErrorLogin.setVisible(false);
		}
		// Set password and confirm password PasswordFields to default
		if (!pwdPassword.getText().trim().isEmpty()) {
			pwdPassword.setStyle("");
			pwdConfirmPassword.setStyle("");
			lblErrorPassword.setVisible(false);
		}
		if (!txtTelefono.getText().trim().isEmpty()) {
			txtTelefono.setStyle("");
		}
	}

	/**
	 * Text changed event handler. It changes the border colors to default and
	 * hides the error labels when some correction is done on a datepicker.
	 *
	 * @param observable The value being observed.
	 * @param oldValue The old value of the observable.
	 * @param newValue The new value of the observable.
	 */
	private void textChanged(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
		// TODO
		// Get changes of datepicker
	}

	/**
	 * Method for validating fullname.
	 *
	 * @param fullname Fullname to validate
	 * @throws IllegalArgumentException Fullname is not valid
	 */
	private void validateFullname(String fullname) throws IllegalArgumentException {
		String FULLNAME_PATTERN = "[a-zA-Z ñÑáÁéÉíÍóÓúÚ]+$";
		Pattern pattern = Pattern.compile(FULLNAME_PATTERN);

		if (fullname == null || fullname.trim().equals("")) {
			throw new IllegalArgumentException("* Field can not be empty.");
		}

		if (fullname.trim().length() >= MAX_LENGTH_FULLNAME) {
			throw new IllegalArgumentException("* Fullname is too long.");
		}

		if (!pattern.matcher(fullname).matches()) {
			throw new IllegalArgumentException("* Only uppercase or lowercase letters.");
		}
	}

	/**
	 * Method for validating email.
	 *
	 * @param email Email to validate.
	 * @throws IllegalArgumentException Email is not valid.
	 */
	private void validateEmail(String email) throws IllegalArgumentException {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);

		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("* Field can not be empty.");
		}

		if (email.trim().length() >= MAX_LENGTH_EMAIL) {
			throw new IllegalArgumentException("* Email is too long.");
		}

		if (!pattern.matcher(email).matches()) {
			throw new IllegalArgumentException("* Enter a valid email.");
		}
	}

	/**
	 * Method for validating login.
	 *
	 * @param login Login to validate
	 * @throws IllegalArgumentException Login is not valid.
	 */
	private void validateLogin(String login) throws IllegalArgumentException {
		String LOGIN_PATTERN = "[a-zA-Z0-9]+";
		Pattern pattern = Pattern.compile(LOGIN_PATTERN);

		if (login == null || login.trim().equals("")) {
			throw new IllegalArgumentException("* How are you supposed to sign in?");
		}

		if (login.trim().length() >= MAX_LENGTH_LOGIN) {
			throw new IllegalArgumentException("* Login is too long.");
		}

		if (!pattern.matcher(login).matches()) {
			throw new IllegalArgumentException("* Login can only be composed by letters and numbers.");
		}
	}

	/**
	 * Method for validating password.
	 *
	 * @param password Password to validate.
	 * @param confirmPassword Password confirmation.
	 * @throws IllegalArgumentException Password is not valid.
	 */
	private void validatePassword(String password, String confirmPassword) throws IllegalArgumentException {
		String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

		if (password == null || password.trim().equals("")) {
			throw new IllegalArgumentException("* Security first, enter a password.");
		}

		if (password.trim().length() >= MAX_LENGTH_PASSWORD) {
			throw new IllegalArgumentException("* Password is too long.");
		}

		if (password.trim().length() < MIN_LENGTH_PASSWORD) {
			throw new IllegalArgumentException("* Password is too short.");
		}

		if (!pattern.matcher(password).matches()) {
			throw new IllegalArgumentException("* Not secure enough! Try combining lowercase, uppercase and numbers.");
		}

		if (confirmPassword == null || (!confirmPassword.trim().equals(password))) {
			throw new IllegalArgumentException("* Passwords must coincide.");
		}
	}

	/**
	 * Method for validating spanish phone number.
	 *
	 * @param telefono Phone number to validate.
	 * @throws IllegalArgumentException Phone number is not valid.
	 */
	private void validateTelefono(String telefono) throws IllegalArgumentException {
		String PHONE_PATTERN = "^[0-9]{2,3}-? ?[0-9]{6,7}$";
		Pattern pattern = Pattern.compile(PHONE_PATTERN);

		if (telefono == null || telefono.trim().equals("")) {
			throw new IllegalArgumentException("* El teléfono no puede estar vacío.");
		}

		if (!pattern.matcher(telefono).matches()) {
			throw new IllegalArgumentException("* El teléfono debe ser un número válido.");
		}
	}

	/**
	 * Action event handler for SignUp button. It validates that all fields are
	 * filled and that they don't have invalid characters. If they are not
	 * border of the corresponding text fields are red colored and an error
	 * labels are shown. If all fields are filled correctly it sends the user
	 * data to the business logic tier.
	 *
	 * @param event The Action event
	 */
	@FXML
	private void handleSignUpAction(ActionEvent event) {
		LOGGER.info("SignUpDesktopFxmlController: Signing up action.");

		// Boolean to check if all fields are filled correctly
		boolean validFields = true;

		// Validate phone number
		try {
			validateTelefono(txtTelefono.getText());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "SignUpDesktopFxmlController: {0}", e.getMessage());

			txtTelefono.setStyle("-fx-border-color:red;");
			txtTelefono.requestFocus();

			validFields = false;
		}
		// Validate password
		try {
			validatePassword(pwdPassword.getText(), pwdConfirmPassword.getText());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Sign Up controller::handleSignUpAction: {0}", e.getMessage());

			lblErrorPassword.setText(e.getMessage());
			lblErrorPassword.setVisible(true);

			pwdPassword.setStyle("-fx-border-color:red;");
			pwdConfirmPassword.setStyle("-fx-border-color:red;");
			pwdPassword.requestFocus();

			validFields = false;
		}
		// Validate login
		try {
			validateLogin(txtLogin.getText());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Sign Up controller::handleSignUpAction: {0}", e.getMessage());

			lblErrorLogin.setText(e.getMessage());
			lblErrorLogin.setVisible(true);

			txtLogin.setStyle("-fx-border-color:red;");
			txtLogin.requestFocus();

			validFields = false;
		}
		// Validate email
		try {
			validateEmail(txtEmail.getText());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Sign Up controller::handleSignUpAction: {0}", e.getMessage());

			lblErrorEmail.setText(e.getMessage());
			lblErrorEmail.setVisible(true);

			txtEmail.setStyle("-fx-border-color:red;");
			txtEmail.requestFocus();

			validFields = false;
		}
		// Validate full name
		try {
			validateFullname(txtFullname.getText());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Sign Up controller::handleSignUpAction: {0}", e.getMessage());

			lblErrorFullname.setText(e.getMessage());
			lblErrorFullname.setVisible(true);

			txtFullname.setStyle("-fx-border-color:red;");
			txtFullname.requestFocus();

			validFields = false;
		}
		// If all fields are filled correctly sign up the user
		if (validFields) {
			// Create an employee to encapsulate all the data
			Empleado emple = new Empleado();
			emple.setLogin(txtLogin.getText());
			emple.setEmail(txtEmail.getText());
			emple.setPassword(pwdPassword.getText());
			emple.setFullname(txtFullname.getText());
			java.sql.Date d = java.sql.Date.valueOf(dtpFechaDeNacimiento.getValue());
			emple.setFechaDeNacimiento(d);
			emple.setTelefono(txtTelefono.getText());

			emple.setStatus(UserStatus.ENABLED);
			emple.setPrivilege(UserPrivilege.USER);
			emple.setLastAccess(new Date());
			emple.setLastPasswordChange(new Date());

			// Sign up the new employee
			try {
				LOGGER.log(Level.INFO, "SignUpDesktopFxmlController: Signing up employee {0}.", emple.getLogin());
				empleadoLogic.createEmpleado(emple);
				LOGGER.log(Level.INFO, "SignUpDesktopFxmlController: Signed up employee.");
				MyAlert.showAlert(Alert.AlertType.INFORMATION, "¡Bienvenido a bordo, " + emple.getLogin() + "!");

				// If user is signed up correctly open SignIn window
				openSignInWindow();
			} catch (BusinessLogicException ex) {
				LOGGER.log(Level.SEVERE, "SignUpDesktopFxmlController: Exception signing up employee, {0}.", ex.getMessage());

				MyAlert.showAlert(Alert.AlertType.ERROR, "Ha ocurrido un error durante el registro, prueba con otro login o intentalo mas tarde.");
			}
		}
	}

	/**
	 * Opens Sign In window.
	 */
	private void openSignInWindow() {
		LOGGER.info("SignUpDesktopFxmlController: Opening SignIn window action.");
		/*
		try {
			// Load node graph from fxml file
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/easyorderappdesktop/ui/fxml/SignInDesktopFXMLDocument.fxml"));
			Parent root = (Parent) loader.load();
			// Get controller for graph
			SignInDesktopFxmlController controller = ((SignInDesktopFxmlController) loader.getController());
			controller.setEmpleadoLogic(empleadoLogic);
			// Initialize stage
			controller.initStage(root);
		 */
		// Hide sign up stage
		stage.hide();
		/*
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "SignUpDesktopFxmlController: Error opening SignIn window.", ex.getMessage());
		}
		 */
	}

	/**
	 * Action event handler for SignIn hyperlink. It opens the sign in window.
	 *
	 * @param event The Action event
	 */
	@FXML
	private void handleSignInAction(ActionEvent event) {
		LOGGER.info("SignUpDesktopFxmlController: Opening SignIn action.");
		openSignInWindow();
	}

}
