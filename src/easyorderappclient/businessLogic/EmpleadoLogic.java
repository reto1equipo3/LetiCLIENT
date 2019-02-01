package easyorderappclient.businessLogic;

import easyorderappclient.transferObjects.Empleado;

/**
 * Interface for Empleado CRUD and password methods.
 *
 * @author Imanol
 */
public interface EmpleadoLogic {

	/**
	 * Creates a new Empleado.
	 *
	 * @param empleado Employee to create.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public void createEmpleado(Empleado empleado) throws BusinessLogicException;

	/**
	 * Updates an existing Empleado.
	 *
	 * @param empleado Employee to update.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public void updateEmpleado(Empleado empleado) throws BusinessLogicException;

	/**
	 * Deletes an exisisting Empleado.
	 *
	 * @param empleado Employee to delete.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public void deleteEmpleado(Empleado empleado) throws BusinessLogicException;

	/**
	 * Signs in an Empleado.
	 *
	 * @param login Login of the employee.
	 * @param password Password of the employee.
	 * @return Employee with all its data.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public Empleado inicioSesion(String login, String password) throws BusinessLogicException;

	/**
	 * Restores an Empleado's password.
	 *
	 * @param login Login of the employee.
	 * @throws BusinessLogicException Something went wrong.
	 */
	public void recuperarContrasegna(String login) throws BusinessLogicException;

	/**
	 * Changes an Empleado's password for a new one.
	 *
	 * @param login Login of the employee.
	 * @param actualPassword Current password.
	 * @param nuevaPassword New password.
	 * @return New employee data
	 * @throws BusinessLogicException Something went wrong.
	 */
	public Empleado cambiarContrasegna(String login, String actualPassword, String nuevaPassword) throws BusinessLogicException;

}
