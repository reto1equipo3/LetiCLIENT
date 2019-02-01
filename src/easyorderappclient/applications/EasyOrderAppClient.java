package easyorderappclient.applications;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import easyorderappclient.businessLogic.EmpleadoLogic;
import easyorderappclient.businessLogic.EmpleadoLogicFactory;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import easyorderappclient.ui.controllers.SignInDesktopFxmlController;

/**
 * Application class for UI SignUpSignInApp. Entry point for the JavaFX
 * application.
 *
 * @author Igor
 */
public class EasyOrderAppClient extends javafx.application.Application {

	/**
	 * Entry point for the application. Loads, sets and shows primary
	 * window(SignIn window).
	 *
	 * @param stage The primary window of the application
	 * @throws Exception Something went wrong
	 */
	@Override
	public void start(Stage stage) throws Exception {
		//Create Logic  to be passed to UI controllers

		EmpleadoLogic empleadoLogic = EmpleadoLogicFactory.createEmpleadoLogicImplementation();
		//This sentence if you want fake data for testing the UI
		//logic= LogicFactory.createLogicImplementation(TEST_TYPE);
		//Load node graph from fxml file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/easyorderappclient/ui/fxml/SignInDesktopFXMLDocument.fxml"));

		Parent root = (Parent) loader.load();

		//Get controller for graph 
		SignInDesktopFxmlController controller = ((SignInDesktopFxmlController) loader.getController());

		//Set a reference in UI controller for logic
		controller.setEmpleadoLogic(empleadoLogic);

		//Set a reference for Stage
		controller.setStage(stage);

		//Initializes stage
		controller.initStage(root);

	}

	/**
	 * Entry point for the Java application.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch();
	}

}
