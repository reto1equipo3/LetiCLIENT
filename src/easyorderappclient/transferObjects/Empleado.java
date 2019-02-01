package easyorderappclient.transferObjects;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Transfer Object used in UI and client side for representing Empleado
 * entity.
 *
 * @author Imanol
 */
@XmlRootElement(name = "empleado")
public class Empleado implements Serializable {

	/**
	 * Id field for employee entity.
	 */
	private final SimpleIntegerProperty id;
	/**
	 * Login value for the employee.
	 */
	private final SimpleStringProperty login;
	/**
	 * Email value for the employee.
	 */
	private final SimpleStringProperty email;
	/**
	 * Full name of the employee.
	 */
	private final SimpleStringProperty fullname;
	/**
	 * Password of the employee.
	 */
	private final SimpleStringProperty password;
	/**
	 * Phone number of the employee.
	 */
	private final SimpleStringProperty telefono;
	/**
	 * {@link UserStatus} value for the employee.
	 */
	private final SimpleObjectProperty<UserStatus> status;
	/**
	 * {@link UserPrivilege} value for the employee.
	 */
	private final SimpleObjectProperty<UserPrivilege> privilege;
	/**
	 * Last time the employee signed in.
	 */
	private final SimpleObjectProperty<Date> lastAccess;
	/**
	 * Last time the employee changed password.
	 */
	private final SimpleObjectProperty<Date> lastPasswordChange;
	/**
	 * Birth date of the employee.
	 */
	private final SimpleObjectProperty<Date> fechaDeNacimiento;

	/**
	 * Default constructor.
	 */
	public Empleado() {
		this.id = new SimpleIntegerProperty();
		this.login = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.fullname = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.telefono = new SimpleStringProperty();
		this.status = new SimpleObjectProperty();
		this.privilege = new SimpleObjectProperty();
		this.lastAccess = new SimpleObjectProperty();
		this.lastPasswordChange = new SimpleObjectProperty();
		this.fechaDeNacimiento = new SimpleObjectProperty();
	}

	/**
	 * Parametrized constructor.
	 *
	 * @param id Id for the employee.
	 * @param login Login for the employee.
	 * @param email Email for the employee.
	 * @param fullname Fullname for the employee.
	 * @param password Password for the employee.
	 * @param telefono Phone number for the employee.
	 * @param status Status for the employee.
	 * @param privilege Privilege for the employee.
	 * @param lastAccess Last access date.
	 * @param lastPasswordChange Last password change.
	 * @param fechaDeNacimiento Birthdate for the employee.
	 */
	public Empleado(SimpleIntegerProperty id,
		SimpleStringProperty login,
		SimpleStringProperty email,
		SimpleStringProperty fullname,
		SimpleStringProperty password,
		SimpleStringProperty telefono,
		SimpleObjectProperty<UserStatus> status,
		SimpleObjectProperty<UserPrivilege> privilege,
		SimpleObjectProperty<Date> lastAccess,
		SimpleObjectProperty<Date> lastPasswordChange,
		SimpleObjectProperty<Date> fechaDeNacimiento) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.telefono = telefono;
		this.status = status;
		this.privilege = privilege;
		this.lastAccess = lastAccess;
		this.lastPasswordChange = lastPasswordChange;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Gets the id of the employee.
	 *
	 * @return The id value.
	 */
	public int getId() {
		return this.id.get();
	}

	/**
	 * Sets the id value of the employee.
	 *
	 * @param id The id value.
	 */
	public void setId(int id) {
		this.id.set(id);
	}

	/**
	 * Gets login value of the employee.
	 *
	 * @return The login value.
	 */
	@XmlElement(name = "login")
	public String getLogin() {
		return this.login.get();
	}

	/**
	 * Sets the login value of the employee.
	 *
	 * @param login The login value.
	 */
	public void setLogin(String login) {
		this.login.set(login);
	}

	/**
	 * Gets email value of the employee.
	 *
	 * @return The email value.
	 */
	@XmlElement(name = "email")
	public String getEmail() {
		return this.email.get();
	}

	/**
	 * Sets the email value of the employee.
	 *
	 * @param email The email value.
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}

	/**
	 * Gets fullname value of the employee.
	 *
	 * @return The fullname value.
	 */
	@XmlElement(name = "fullname")
	public String getFullname() {
		return this.fullname.get();
	}

	/**
	 * Sets the fullname value of the employee.
	 *
	 * @param fullname The fullname value.
	 */
	public void setFullname(String fullname) {
		this.fullname.set(fullname);
	}

	/**
	 * Gets the password value of the employee.
	 *
	 * @return The password value.
	 */
	@XmlElement(name = "password")
	public String getPassword() {
		return this.password.get();
	}

	/**
	 * Sets the password value of the employee.
	 *
	 * @param password The password value.
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}

	/**
	 * Gets the phone value of the employee.
	 *
	 * @return The phone value.
	 */
	@XmlElement(name = "telefono")
	public String getTelefono() {
		return this.telefono.get();
	}

	/**
	 * Sets the phone value of the employee.
	 *
	 * @param telefono The telefono value.
	 */
	public void setTelefono(String telefono) {
		this.telefono.set(telefono);
	}

	/**
	 * Gets the status value of the employee.
	 *
	 * @return The status value.
	 */
	@XmlElement(name = "status")
	public UserStatus getStatus() {
		return this.status.get();
	}

	/**
	 * Sets the status value of the employee.
	 *
	 * @param status The status value.
	 */
	public void setStatus(UserStatus status) {
		this.status.set(status);
	}

	/**
	 * Gets the privilege value of the employee.
	 *
	 * @return The privilege value.
	 */
	@XmlElement(name = "privilege")
	public UserPrivilege getPrivilege() {
		return this.privilege.get();
	}

	/**
	 * Sets the privilege value of the employee.
	 *
	 * @param privilege The privilege value.
	 */
	public void setPrivilege(UserPrivilege privilege) {
		this.privilege.set(privilege);
	}

	/**
	 * Gets the last access value of the employee.
	 *
	 * @return The last access value.
	 */
	@XmlElement(name = "lastAccess")
	public Date getLastAccess() {
		return this.lastAccess.get();
	}

	/**
	 * Sets the last access value of the employee.
	 *
	 * @param lastAccess The last access value.
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess.set(lastAccess);
	}

	/**
	 * Gets the last password change value of the employee.
	 *
	 * @return The last password change.
	 */
	@XmlElement(name = "lastPasswordChange")
	public Date getLastPasswordChange() {
		return this.lastPasswordChange.get();
	}

	/**
	 * Sets the last password change value of the employee.
	 *
	 * @param lastPasswordChange The last password change value.
	 */
	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange.set(lastPasswordChange);
	}

	/**
	 * Gets the birth date value of the employee.
	 *
	 * @return The birth date value.
	 */
	@XmlElement(name = "fechaDeNacimiento")
	public Date getFechaDeNacimiento() {
		return this.fechaDeNacimiento.get();
	}

	/**
	 * Sets the birth date value of the employee.
	 *
	 * @param fechaDeNacimiento The birth date value.
	 */
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento.set(fechaDeNacimiento);
	}

	/**
	 * HashCode method implementation for the entity.
	 *
	 * @return An integer value as hashcode for the object.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * This method compares two employee entities for equality. This
	 * implementation compares id field value for equality.
	 *
	 * @param obj The object to compare to.
	 * @return True if objects are equal, otherwise false.
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
		final Empleado other = (Empleado) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a String representation for an employee entity
	 * instance.
	 *
	 * @return The String representation for the employee object.
	 */
	@Override
	public String toString() {
		return "Empleado{" + "id=" + id + ", login=" + login + '}';
	}

}
