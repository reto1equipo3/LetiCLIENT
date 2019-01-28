/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

import easyorderappclient.transferObject.ProductBean;
import java.util.Collection;


/**
 * Business logic interface encapsulating business methods for products management.
 * @author javi
 */
public interface ProductsManager {
    /**
     * This method returns a Collection of {@link ProductBean}, containing all products data.
     * @return Collection The collection with all {@link ProductBean} data for products. 
     * @throws BusinessLogicException If there is any error while processing.
     */
    public Collection<ProductBean> getAllProducts() throws BusinessLogicException;
   
    /**
     * This method adds a new created ProductBean.
     * @param product The ProductBean object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void createProduct(ProductBean product) throws BusinessLogicException;
    /**
     * This method updates data for an existing ProductBean data for product. 
     * @param product The ProductBean object to be updated.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void updateProduct(ProductBean product) throws BusinessLogicException;
    /**
     * This method deletes data for an existing product. 
     * @param product The ProductBean object to be deleted.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void deleteProduct(ProductBean product) throws BusinessLogicException;
    /**
     * This method checks if a product's id already exists, throwing an Exception 
     * if that's the case.
     * @param id The id value to be checked.
     * @throws IdExistsException The Exception thrown in case id already exists
     */
    public void isIdExisting(Integer id) throws IdExistsException,BusinessLogicException;
    
}
