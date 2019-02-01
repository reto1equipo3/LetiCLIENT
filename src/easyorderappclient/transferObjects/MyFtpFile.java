package easyorderappclient.transferObjects;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Custom class for ftp file.
 *
 * @author Imanol
 */
public class MyFtpFile {

	/**
	 * Name of the file.
	 */
	private String name;
	/**
	 * Path of the file.
	 */
	private String path;
	/**
	 * If it is directory true, else false.
	 */
	private boolean directory;

	/**
	 * Default constructor.
	 */
	public MyFtpFile() {
	}

	/**
	 * Parametrized constructor.
	 *
	 * @param name Name of the file.
	 * @param path Path of the file.
	 * @param isDirectory If directory or not.
	 */
	public MyFtpFile(String name, String path, boolean isDirectory) {
		this.name = name;
		this.path = path;
		this.directory = isDirectory;
	}

	/**
	 * Gets the name of the file.
	 *
	 * @return Name of the file
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the path of the file.
	 *
	 * @return Path of the file.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets if it is a directory or not.
	 *
	 * @return True if directory, false otherwise.
	 */
	public boolean isDirectory() {
		return directory;
	}

	/**
	 * Sets the name of the file.
	 *
	 * @param name Name of the file.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the path of the file.
	 *
	 * @param path Path of the file.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Sets if it is a directory or not.
	 *
	 * @param directory If directory.
	 */
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * Gets the absolute path of the file.
	 *
	 * @return Absolute path of the file.
	 */
	public String getAbsolutePath() {
		String absolutePath = null;
		final String root = "/";

		if (path.equals(root)) {
			absolutePath = path + name;
		} else {
			absolutePath = path + "/" + name;
		}

		Logger.getLogger("easyorderappclient").log(Level.INFO, "MyFtpFile: Absolute path={0}", absolutePath);

		return absolutePath;
	}

	/**
	 * HashCode method implementation for the entity.
	 *
	 * @return An integer value as hashcode for the object.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (name != null ? name.hashCode() : 0);
		return hash;
	}

	/**
	 * Compares the object to another one.
	 *
	 * @param obj Object to compare to.
	 * @return True if are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final MyFtpFile other = (MyFtpFile) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets a string value representing the object.
	 *
	 * @return String value.
	 */
	@Override
	public String toString() {
		return name;
	}

}
