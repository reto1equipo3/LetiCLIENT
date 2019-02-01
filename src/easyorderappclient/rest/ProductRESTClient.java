/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.rest;

import java.util.ResourceBundle;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ProductoFacadeREST
 * [productos]<br>
 * USAGE:
 * <pre>
 *        ProductRESTClient client = new ProductRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Igor
 */
public class ProductRESTClient {

    private final WebTarget webTarget;
    private final Client client;
   private static final String BASE_URI = ResourceBundle.getBundle("easyorderappclient.config.parameters").getString("RESTful.baseURI");

    /**
     * Construct a ProductRESTClient. It creates a RESTful web client and establishes
     * the path of the WebTarget object associated to the client.
     */
    public ProductRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("productos");
    }

    /**
     * Get a product's entity XML representation from the producto RESTful web service and 
     * return it as a generic type object.
     * @param responseType The Class object of the returning instance. 
     * @param id The id of the instance in the server side. 
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.  
     */
    public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

     /**
     * Get a list of product's entities XML representation from the product RESTful web service and 
     * return it as a generic type object.
     * @param responseType The Class object of the returning instance.
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.  
     */
    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    
    /**
     * Create an Product's entity XML representation and send it as a request to create it
     * to the product RESTful web service.
     * @param requestEntity The object containing data to be created.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.  
     */
    public void create_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Create an product's entity XML representation and send it as a request to update it
     * to the product RESTful web service.
     * @param requestEntity The object containing data to be updated.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.  
     */
    public void update_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

     /**
     * Send a request to the product RESTful web service to delete a product identified by its id.
     * @param id The id of the product entity to be deleted.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.  
     */
    public void delete(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    /**
     * Close RESTful web service client.
     */
    public void close() {
        client.close();
    }
    
}
