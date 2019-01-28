/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

import easyorderappclient.transferObjects.Pedido;
import java.util.Collection;

/**
 * Business logic interface encapsulating business methods for pedido management.
 * @author Leticia Garcia
 */
public interface PedidoLogic {
     
    /**
     * This method returns a Collection of {@link Pedido}, containing all pedidos data.
     * @return Collection The collection with all {@link Pedido} data for pedidos. 
     * @throws BusinessLogicException If there is any error while processing.
     */
    public Collection<Pedido> buscarTodosLosPedidos() throws BusinessLogicException;
     /**
     * This method adds a new created Pedido.
     * @param pedido The Pedido object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void añadirPedido(Pedido pedido) throws BusinessLogicException;
    /**
     * This method updates data for an existing Pedido data for pedido. 
     * @param pedido The Pedido object to be updated.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void actualizarEstado(Pedido pedido) throws BusinessLogicException;
    /**
     * This method deletes data for an existing pedido. 
     * @param pedido The Pedido object to be deleted.
     * @throws BusinessLogicException If there is any error while processing.
     */
    public void eliminarPedido(Pedido pedido) throws BusinessLogicException;
    /**
     * This method checks if a user's login already exists, throwing an Exception 
     * if that's the case.
     * @param id The login value to be checked.
     * @throws IdPedidoExistsException The Exception thrown in case login already exists
     */
    public void isIdPedidoExisting(Integer id) throws IdPedidoExistsException,BusinessLogicException;
    
    
}
