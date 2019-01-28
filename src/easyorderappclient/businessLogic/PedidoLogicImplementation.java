/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;


import easyorderappclient.rest.PedidoRESTClient;
import easyorderappclient.transferObjects.Pedido;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.GenericType;

/**
 * This class implements {@link PedidoLogic} business logic interface using a
 * RESTful web client to access bussines logic in an Java EE application server.
 *
 * @author Leticia Garcia
 */
public class PedidoLogicImplementation implements PedidoLogic {

    //REST pedidos web client
    private PedidoRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("easyorderappclient");

    /**
     * Create a PedidoLogicImplementation object. It constructs a web client for
     * accessing a RESTful service that provides business logic in an
     * application server.
     */
    public PedidoLogicImplementation() {
        webClient = new PedidoRESTClient();
    }

    /**
     * This method returns a list of {@link Pedido}, containing all
     * pedidos data.
     *
     * @return List The List with all {@link Pedido} data for
     * pedidos.
     * @throws BusinessLogicException If there is any error while processing.
     */

    @Override
    public List<Pedido> buscarTodosLosPedidos() throws BusinessLogicException {

        List<Pedido> pedidos = null;
        try {
            LOGGER.info("PedidoLogic: Finding all pedidos from REST service (XML).");
            //Ask webClient for all pedidos' data.                   
            pedidos = webClient.findAll_XML(new GenericType<List<Pedido>>(){});
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "PedidoLogic: Exception finding all pedidos, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error finding all pedidos:\n" + ex.getMessage());
        }
        return pedidos;

    }
    
     /**
     * This method adds a new created pedido. This is done by sending a POST 
     * request to a RESTful web service.
     * @param pedido The Pedido object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void añadirPedido(Pedido pedido) throws BusinessLogicException {
         try{
            LOGGER.log(Level.INFO,"Pedido: Creating pedido {0}.",pedido.getCodigo());
            //Send user data to web client for creation. 
            webClient.create_XML(pedido);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "Pedido: Exception creating pedido, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error creating pedido:\n"+ex.getMessage());
        }
    }
    /**
     * This method updates data for an existing Pedido data for pedido. 
     * This is done by sending a PUT request to a RESTful web service.
     * @param pedido The Pedido object to be updated.
     * @throws BusinessLogicException If there is any error while processing.
     */
   @Override
    public void actualizarEstado(Pedido pedido) throws BusinessLogicException {
       try{
            LOGGER.log(Level.INFO,"Pedido: Updating pedido {0}.",pedido.getCodigo());
            pedido.setProductos(null);//Quitamos productos
            webClient.update_XML(pedido);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "Pedido: Exception updating pedido, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error updating pedido:\n"+ex.getMessage());
        }
    }
        /**
     * This method deletes data for an existing pedido. 
     * This is done by sending a DELETE request to a RESTful web service.
     * @param pedido The Pedido object to be deleted.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void eliminarPedido(Pedido pedido) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "Pedido: Deleting pedido {0}.", pedido.getCodigo());
            webClient.delete(pedido.getCodigo().toString());
           // webClient.delete(pedido.getCodigo());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "Pedido: Exception deleting pedido, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error deleting pedido:\n" + ex.getMessage());
        }
    }

    /**
     * This method checks if a pedido's id already exists, throwing an Exception 
     * if that's the case.
     * @param id
     * @throws BusinessLogicException
     * @throws IdPedidoExistsException The Exception thrown in case login already exists
     */
    @Override
    public void isIdPedidoExisting(Integer id) throws IdPedidoExistsException, BusinessLogicException {
       try{
            if(this.webClient.find_XML(Pedido.class, id.toString())!=null)
                throw new IdPedidoExistsException("Ya existe un pedido con ese id");
        }catch(NotFoundException ex){
            //If there is a NotFoundException 404,that is,
            //the login does not exist, we catch the exception and do nothing. 
        }catch(IdPedidoExistsException | ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "Pedido: Exception checking id exixtence, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error finding pedido:\n"+ex.getMessage());
        }
    }

}
