package easyorderappclient.utils;

import java.util.regex.Pattern;

/**
 * Custom class for regex validation.
 *
 * @author Imanol
 */
public class MyRegex {

	private static final int MAX_LENGTH = 255;
	private static final int MAX_LENGTH_FULLNAME = 50;
	private static final int MAX_LENGTH_EMAIL = 50;
	private static final int MAX_LENGTH_LOGIN = 20;
	private static final int MAX_LENGTH_PASSWORD = 10;
	private static final int MIN_LENGTH_PASSWORD = 6;

	/**
	 * Method for validating fullname.
	 *
	 * @param fullname Fullname to validate
	 * @throws IllegalArgumentException Fullname is not valid
	 */
	public static void validateFullname(String fullname) throws IllegalArgumentException {
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
	public static void validateEmail(String email) throws IllegalArgumentException {
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
	public static void validateLogin(String login) throws IllegalArgumentException {
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
	 * @throws IllegalArgumentException Password is not valid.
	 */
	public static void validatePassword(String password) throws IllegalArgumentException {
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

	}

	/**
	 * Method for validating spanish phone number.
	 *
	 * @param telefono Phone number to validate.
	 * @throws IllegalArgumentException Phone number is not valid.
	 */
	public static void validateTelefono(String telefono) throws IllegalArgumentException {
		String PHONE_PATTERN = "^[0-9]{2,3}-? ?[0-9]{6,7}$";
		Pattern pattern = Pattern.compile(PHONE_PATTERN);

		if (telefono == null || telefono.trim().equals("")) {
			throw new IllegalArgumentException("* El teléfono no puede estar vacío.");
		}

		if (!pattern.matcher(telefono).matches()) {
			throw new IllegalArgumentException("* El teléfono debe ser un número válido.");
		}
	}
}
