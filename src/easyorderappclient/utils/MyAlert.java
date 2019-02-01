package easyorderappclient.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Custom class to create {@link Alert} objects.
 *
 * @author Imanol
 */
public class MyAlert {

	/**
	 * Creates a custom alert window.
	 *
	 * @param type Type of the alert.
	 * @param context Message to show.
	 * @return Button input of the user.
	 */
	public static Optional<ButtonType> showAlert(Alert.AlertType type, String context) {
		Alert alert = new Alert(type);
		alert.setHeaderText(null);
		alert.setContentText(context);
		return alert.showAndWait();
	}
}
