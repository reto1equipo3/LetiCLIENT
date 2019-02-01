package easyorderappclient.businessLogic;

/**
 * This class is a factory for {@link EmpleadoLogic} interface implementing
 * objects.
 *
 * @author Imanol
 */
public class EmpleadoLogicFactory {

	/**
	 * Factory creation method.
	 *
	 * @return An {@link EmpleadoLogic} implementation
	 */
	public static EmpleadoLogic createEmpleadoLogicImplementation() {
		return new EmpleadoLogicImplementation();
	}

}
