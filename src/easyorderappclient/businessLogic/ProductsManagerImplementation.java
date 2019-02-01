package easyorderappclient.businessLogic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import easyorderappclient.businessLogic.BusinessLogicException;
import easyorderappclient.businessLogic.IdExistsException;
import easyorderappclient.businessLogic.ProductsManager;
import easyorderappclient.rest.ProductRESTClient;
import easyorderappclient.transferObjects.Producto;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;

import javax.ws.rs.NotFoundException;

import javax.ws.rs.core.GenericType;


/**
 * This class implements {@link ProductsManager} business logic interface using a 
 * RESTful web client to access bussines logic in an Java EE application server. 
 * @author Igor
 */
public class ProductsManagerImplementation implements ProductsManager{
    //REST products web client
    private ProductRESTClient webClient;
    private static final Logger LOGGER=Logger.getLogger("easyorderappclient");

    /**
     * Create a ProductsManagerImplementation object. It constructs a web client for 
     * accessing a RESTful service that provides business logic in an application
     * server.
     */
    public ProductsManagerImplementation(){
        webClient=new ProductRESTClient();
    }
    /**
     * This method returns a Collection of {@link Producto}, containing all products data.
     * @return Collection The collection with all {@link Producto} data for products. 
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public Collection<Producto> getAllProducts() throws BusinessLogicException {
        List<Producto> products =null;
        try{
            LOGGER.info("ProductsManager: Finding all products from REST service (XML).");
            products = webClient.findAll_XML(new GenericType<List<Producto>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductsManager: Exception finding all products, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error finding all products:\n"+ex.getMessage());
        }
        return products;
    }
    
  
    /**
     *This method checks if a product's id already exists, throwing an Exception 
     * if that's the case.
     * @param id
     * @throws IdExistsException The Exception thrown in case id already exists
     * @throws BusinessLogicException 
     */
    @Override
    public void isIdExisting(Integer id) throws IdExistsException,BusinessLogicException {
      
        try{
            if(this.webClient.find_XML(Producto.class, id.toString())!=null)
                throw new IdExistsException("Ya existe un producto con ese codigo");
        }catch(NotFoundException ex){
            //If there is a NotFoundException 404,that is,
            //the id does not exist, we catch the exception and do nothing. 
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductsManager: Exception checking code(id) exixtence, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error finding product:\n"+ex.getMessage());
        }
    }
    /**
     * This method adds a new created ProductBean. This is done by sending a POST 
     * request to a RESTful web service.
     * @param product The ProductBean object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void createProduct(Producto product) throws BusinessLogicException {
        try{
            LOGGER.log(Level.INFO,"ProductsManager: Creating product {0}.",product.getId());
            //Send product data to web client for creation. 
            webClient.create_XML(product);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductsManager: Exception creating product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error creating product:\n"+ex.getMessage());
        }
    }
    /**
     * This method updates data for an existing ProductBean data for product. 
     * This is done by sending a PUT request to a RESTful web service.
     * @param product The ProductBean object to be updated.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void updateProduct(Producto product) throws BusinessLogicException {
        try{
            LOGGER.log(Level.INFO,"ProductsManager: Updating product {0}.",product.getId());
            webClient.update_XML(product);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductsManager: Exception updating product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error updating product:\n"+ex.getMessage());
        }
    }
    /**
     * This method deletes data for an existing product. 
     * This is done by sending a DELETE request to a RESTful web service.
     * @param product The ProductBean object to be deleted.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void deleteProduct(Producto product) throws BusinessLogicException {
        try{
            LOGGER.log(Level.INFO,"ProductsManager: Deleting product {0}.",product.getId());
            webClient.delete(product.getId().toString());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductsManager: Exception deleting product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("Error deleting product:\n"+ex.getMessage());
        }
    }

   

    
 
}
