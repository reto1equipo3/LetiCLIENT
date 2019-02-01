package easyorderappclient.businessLogic;

import easyorderappclient.transferObjects.MyFtpFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * This class implements {@link FTPLogic} business logic interface.
 *
 * @author Imanol
 */
public class FTPLogicImplementation implements FTPLogic {

	/**
	 * {@link Logger} object used to log messages for the app.
	 */
	private static final Logger LOGGER = Logger.getLogger("easyorderappclient");
	/**
	 * FTPClient object.
	 */
	private FTPClient ftp;
	/**
	 * Hostname for the server.
	 */
	private final String HOSTNAME = ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("ftp.hostname");
	/**
	 * Port for the server.
	 */
	private final int PORT = Integer.parseInt(ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("ftp.port"));
	/**
	 * Username for the server.
	 */
	private final String USERNAME = ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("ftp.username");
	/**
	 * Password for the server.
	 */
	private final String PASSWORD = ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("ftp.password");

        public FTPLogicImplementation(){
        iniciarSesion();
        }
        
        
        
        
	/**
	 * This method signs in the ftp server.
	 */
	@Override
	public void iniciarSesion() {
		LOGGER.info("FTPLogicImplementation: Connecting...");
		try {
			ftp = new FTPClient();
			ftp.connect(HOSTNAME, PORT);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.login(USERNAME, PASSWORD);
			ftp.changeWorkingDirectory("/");
			// TODO
			// Usar remote cuando el hostname no es localhost
			//ftp.enterRemotePassiveMode();
			ftp.enterLocalPassiveMode();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Exception connecting, {0}.", ex.getMessage());
			// TODO
		}
		LOGGER.info("FTPLogicImplementation: Connected.");
	}

	/**
	 * This method signs out from the ftp server.
	 */
	@Override
	public void cerrarSesion() {
		LOGGER.info("FTPLogicImplementation: Closing ftp connection...");
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Exception closing ftp connection, {0}.", ex.getMessage());
			// TODO
		}
		LOGGER.info("FTPLogicImplementation: Closed ftp connection.");
	}

	/**
	 * Helper method for converting an {@link FTPFile} to {@link MyFtpFile}.
	 *
	 * @param path Path for MyFTPFile.
	 * @param ftpFile File to convert.
	 * @return Converted file.
	 */
	private MyFtpFile FTPFileToMyFtpFile(String path, FTPFile ftpFile) {
		MyFtpFile myFtpFile = new MyFtpFile();
		myFtpFile.setName(ftpFile.getName());
		myFtpFile.setPath(path);

		switch (ftpFile.getType()) {
			case FTPFile.DIRECTORY_TYPE:
				myFtpFile.setDirectory(true);
				break;
			case FTPFile.FILE_TYPE:
				myFtpFile.setDirectory(false);
				break;
			default:
				break;
		}

		return myFtpFile;
	}

	/**
	 * This method lists all the files and directories in the ftp server.
	 *
	 * @param path Path of the directory to list.
	 * @return The files and directories in the path.
	 */
	@Override
	public ArrayList<MyFtpFile> listarFicheros(String path) {
		LOGGER.log(Level.INFO, "FTPLogicImplementation: Listando ficheros path={0}.", path);
		FTPFile[] ftpFiles = null;
		ArrayList<MyFtpFile> myFtpFiles = new ArrayList<>();

		try {
			ftpFiles = ftp.listFiles(path);
			for (FTPFile ftpFile : ftpFiles) {
				myFtpFiles.add(FTPFileToMyFtpFile(path, ftpFile));
			}
		} catch (IOException ex) {
			LOGGER.log(Level.INFO, "FTPLogicImplentation: Error listando ficheros, {0}.", ex.getMessage());
			// TODO
		}

		LOGGER.log(Level.INFO, "FTPLogicImplementation: Ficheros listados.");
		return myFtpFiles;
	}

	/**
	 * This method creates a directory.
	 *
	 * @param path Path with the name where the directory will be created.
	 */
	@Override
	public void crearDirectorio(String path) throws BusinessLogicException {
		LOGGER.info("FTPLogicImplementation: Creando directorio...");
		try {
			ftp.makeDirectory(path);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error creando directorio, {0}.", ex.getMessage());
			throw new BusinessLogicException("Error creating directory.");
		}
		LOGGER.info("FTPLogicImplementation: Directorio creado.");
	}

	/**
	 * This method deletes a directory.
	 *
	 * @param path Path with the name where the directory is located.
	 * @return True if deleted, False otherwise.
	 */
	@Override
	public boolean borrarDirectorio(String path) throws BusinessLogicException {
		LOGGER.info("FTPLogicImplementation: Borrando directorio...");
		boolean deleted = false;
		try {
			deleted = ftp.removeDirectory(path);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error borrando directorio, {0}.", ex.getMessage());
			throw new BusinessLogicException("Error deleting directory.");
		}
		LOGGER.info("FTPLogicImplementation: Directorio borrado.");
		return deleted;
	}

	/**
	 * This method downloads a file.
	 *
	 * @param path Path with the name of the file.
	 * @param destino Destination of the download.
	 * @return True if deleted, False otherwise.
	 */
	@Override
	public boolean descargarArchivo(String path, OutputStream destino) throws BusinessLogicException {
		LOGGER.info("FTPLogicImplementation: Descargando archivo...");
		boolean downloaded = false;
		try {
			downloaded = ftp.retrieveFile(path, destino);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error descargando archivo, {0}.", ex.getMessage());
			throw new BusinessLogicException("Error downloading file.");
		}
		LOGGER.info("FTPLogicImplementation: Archivo descargado.");
		return downloaded;
	}

	/**
	 * This method uploads a file.
	 *
	 * @param path Path with the name of the file.
	 * @param file File to upload.
	 * @return True if deleted, False otherwise.
	 * @throws BusinessLogicException Something went wrong uploading file.
	 */
	@Override
	public boolean subirArchivo(String path, File file) throws BusinessLogicException {
		LOGGER.info("FTPLogicImplementation: Subiendo archivo...");
		FileInputStream fileInputStream = null;
		boolean uploaded = false;
		try {
			fileInputStream = new FileInputStream(file);
			uploaded = ftp.storeFile(path + file.getName(), fileInputStream);

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error subiendo archivo, {0}.", ex.getMessage());
			throw new BusinessLogicException("Error uploading file.");
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException ex) {
				LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error cerrando stream de entrada, {0}.", ex.getMessage());
			}
		}

		LOGGER.info("FTPLogicImplementation: Archivo subido.");
		return uploaded;
	}

	/**
	 * This method deletes a file.
	 *
	 * @param path Path with the name of the file.
	 * @return True if deleted, False otherwise.
	 */
	@Override
	public boolean borrarArchivo(String path) throws BusinessLogicException {
		LOGGER.info("FTPLogicImplementation: Borrando archivo...");
		boolean deleted = false;
		try {
			deleted = ftp.deleteFile(path);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, "FTPLogicImplementation: Error borrando archivo, {0}.", ex.getMessage());
			throw new BusinessLogicException("Error deleting file.");
		}
		LOGGER.info("FTPLogicImplementation: Archivo borrado.");
		return deleted;
	}

}
