package easyorderappclient.rest;

import java.util.ResourceBundle;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:EmpleadoREST [empleado]<br>
 * USAGE:
 * <pre>
 *        EmpleadoRESTClient client = new EmpleadoRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Imanol
 */
public class EmpleadoRESTClient {

	/**
	 * Web target object
	 */
	private final WebTarget webTarget;
	/**
	 * Client object
	 */
	private final Client client;
	/**
	 * URI from properties' values file
	 */
	private static final String BASE_URI = ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("RESTful.baseURI");

	/**
	 * Default constructor.
	 */
	public EmpleadoRESTClient() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
		webTarget = client.target(BASE_URI).path("empleado");
	}

	/**
	 * Finds an employee
	 *
	 * @param <T> Template
	 * @param responseType ResponseType
	 * @param id Id of the employee
	 * @return Employee found
	 */
	public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
	}

	/**
	 * Creates an employee
	 *
	 * @param requestEntity Employee to create
	 * @throws WebApplicationException Something went wrong
	 */
	public void create(Object requestEntity) throws WebApplicationException {
		Object entity = new Object();
		Object response = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), (Class) entity.getClass());
	}

	/**
	 * Updates and employee
	 *
	 * @param requestEntity Employee to update
	 * @throws ClientErrorException Something went wrong
	 */
	public void update(Object requestEntity) throws ClientErrorException {
		webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
	}

	/**
	 * Signs in an employee
	 *
	 * @param <T> Template
	 * @param responseType Response type
	 * @param login Login of the employee
	 * @param password Password of the employee
	 * @return Signed in employee
	 * @throws ClientErrorException Something went wrong.
	 */
	public <T> T iniciarSesion(Class<T> responseType, String login, String password) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("login/{0}/{1}", new Object[]{login, password}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
	}

	/**
	 * Changes password of an employee
	 *
	 * @param <T> Template
	 * @param responseType Response type
	 * @param login Login of the employee
	 * @param actualPassword Current password
	 * @param nuevaPassword New password
	 * @return Employee with updated data
	 * @throws ClientErrorException Something went wrong
	 */
	public <T> T cambiarContrasegna(Class<T> responseType, String login, String actualPassword, String nuevaPassword) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("login/{0}/{1}/{2}", new Object[]{login, actualPassword, nuevaPassword}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
	}

	/**
	 * Gets all employees
	 *
	 * @param <T> Template
	 * @param responseType Response type
	 * @return All employees
	 * @throws ClientErrorException Something went wrong
	 */
	public <T> T findAll(GenericType<T> responseType) throws ClientErrorException {
		WebTarget resource = webTarget;
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
	}

	/**
	 * Deletes an employee
	 *
	 * @param id Id of the employee
	 * @throws ClientErrorException Something went wrong
	 */
	public void delete(String id) throws ClientErrorException {
		webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
	}

	/**
	 * Restores password
	 *
	 * @param <T> Template
	 * @param responseType ResponseType
	 * @param login Login of the employee
	 * @return Something
	 * @throws ClientErrorException Something went wrong
	 */
	public <T> T recuperarContrasegna(Class<T> responseType, String login) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("login/{0}", new Object[]{login}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
	}

	/**
	 * Closes the connection
	 */
	public void close() {
		client.close();
	}

}
