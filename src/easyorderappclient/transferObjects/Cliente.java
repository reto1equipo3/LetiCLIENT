package easyorderappclient.transferObjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity JPA class for the client data.
 *
 * @author Leticia Garcia
 */

@XmlRootElement
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Id field for client entity.
	 */
        private  Integer id;
	//private Integer id;

	/**
	 * Login value for the employee.
	 */
	private String login;
	/**
	 * Email value for the employee.
	 */
	private String email;
	/**
	 * Full name of the employee.
	 */
	private String fullname;
	/**
	 * {@link UserStatus} value for the employee.
	 */
	
	private UserStatus status;
	/**
	 * {@link UserPrivilege} value for the employee.
	 */
	
	private UserPrivilege privilege;
	/**
	 * Password of the employee.
	 */
	private String password;
	/**
	 * Last time the employee signed in.
	 */
	
	private Date lastAccess;
	/**
	 * Last time the employee changed password.
	 */
	
	private Date lastPasswordChange;
	/**
	 * Birth date of the employee.
	 */
	
	private Date fechaDeNacimiento;
	/**
	 * Phone number of the employee.
	 */
	private String telefono;
	/**
	 * Residence of the client.
	 */
	private String localidad;
	private String codigoPostal;
	private String direccion;
	/**
	 * Account of the client.
	 */
	private String numeroCuenta;
	private String codigoSeguridad;
	private Double saldo;
	/**
	 * Shifts of the client.
	 */
	
	private List<Pedido> pedidos;

         
    //Getters and setters
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id=id;
    }
       
	/**
	 * Gets login value of the cliente.
	 *
	 * @return The login value.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login value of the employee.
	 *
	 * @param login The login value.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets email value of the employee.
	 *
	 * @return The email value.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email value of the employee.
	 *
	 * @param email The email value.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets fullname value of the employee.
	 *
	 * @return The fullname value.
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Sets the fullname value of the employee.
	 *
	 * @param fullname The fullname value.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Gets the status value of the employee.
	 *
	 * @return The status value.
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status value of the employee.
	 *
	 * @param status The status value.
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * Gets the privilege value of the employee.
	 *
	 * @return The privilege value.
	 */
	public UserPrivilege getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege value of the employee.
	 *
	 * @param privilege The privilege value.
	 */
	public void setPrivilege(UserPrivilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * Gets the password value of the employee.
	 *
	 * @return The password value.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password value of the employee.
	 *
	 * @param password The password value.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the last access value of the employee.
	 *
	 * @return The last access value.
	 */
	public Date getLastAccess() {
		return lastAccess;
	}

	/**
	 * Sets the last access value of the employee.
	 *
	 * @param lastAccess The last access value.
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	/**
	 * Gets the last password change value of the employee.
	 *
	 * @return The last password change.
	 */
	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	/**
	 * Sets the last password change value of the employee.
	 *
	 * @param lastPasswordChange The last password change value.
	 */
	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	/**
	 * Gets the birth date value of the employee.
	 *
	 * @return The birth date value.
	 */
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Sets the birth date value of the employee.
	 *
	 * @param fechaDeNacimiento The birth date value.
	 */
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	/**
	 * Gets the phone value of the employee.
	 *
	 * @return The phone value.
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the phone value of the employee.
	 *
	 * @param telefono The telefono value.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the town value of the residence.
	 *
	 * @return The town value.
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the town value of the residence.
	 *
	 * @param localidad The town value.
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the CP value of the residence.
	 *
	 * @return The CP value.
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the CP value of the residence.
	 *
	 * @param codigoPostal The CP value.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the address value of the residence.
	 *
	 * @return The address value.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the address value of the residence.
	 *
	 * @param direccion The address value.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the account number of the account.
	 *
	 * @return The account number.
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the account number of the account.
	 *
	 * @param numeroCuenta The account number value.
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the security code of the account.
	 *
	 * @return The security code value.
	 */
	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	/**
	 * Sets the security code of the account.
	 *
	 * @param codigoSeguridad The security code value.
	 */
	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	/**
	 * Gets the balance of the account.
	 *
	 * @return The balance value.
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Sets the balance of the account.
	 *
	 * @param saldo The balance value.
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

      /**
     * @return the pedidos
     */
        @XmlTransient
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    @Override
    public String toString(){
    return login;
    }

}