package easyorderappclient.businessLogic;

import easyorderappclient.transferObjects.MyFtpFile;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * FTP logic interface encapsulating business methods for ftp management.
 *
 * @author Imanol
 */
public interface FTPLogic {

	/**
	 * This method signs in the ftp server.
	 */
	public void iniciarSesion();

	/**
	 * This method signs out from the ftp server.
	 */
	public void cerrarSesion();

	/**
	 * This method lists all the files and directories in the ftp server.
	 *
	 * @param path Path of the directory to list.
	 * @return The files and directories in the path.
	 */
	public ArrayList<MyFtpFile> listarFicheros(String path);

	/**
	 * This method creates a directory.
	 *
	 * @param path Path with the name where the directory will be created.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public void crearDirectorio(String path) throws BusinessLogicException;

	/**
	 * This method deletes a directory.
	 *
	 * @param path Path with the name where the directory is located.
	 * @return True if deleted, False otherwise.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public boolean borrarDirectorio(String path) throws BusinessLogicException;

	/**
	 * This method downloads a file.
	 *
	 * @param path Path with the name of the file.
	 * @param destino Destination of the download.
	 * @return True if deleted, False otherwise.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public boolean descargarArchivo(String path, OutputStream destino) throws BusinessLogicException;

	/**
	 * This method uploads a file.
	 *
	 * @param path Path with the name of the file.
	 * @param file File to upload.
	 * @return True if deleted, False otherwise.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public boolean subirArchivo(String path, File file) throws BusinessLogicException;

	/**
	 * This method deletes a file.
	 *
	 * @param path Path with the name of the file.
	 * @return True if deleted, False otherwise.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public boolean borrarArchivo(String path) throws BusinessLogicException;
}
