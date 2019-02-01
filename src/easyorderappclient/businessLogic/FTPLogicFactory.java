package easyorderappclient.businessLogic;

/**
 * This class is a factory for {@link FTPLogic} interface implementing objects.
 *
 * @author Imanol
 */
public class FTPLogicFactory {

	/**
	 * Factory creation method.
	 *
	 * @return An object implementing FTPLogic interface.
	 */
	public static FTPLogic createFTPLogicImplementation() {
		return new FTPLogicImplementation();
	}
}
