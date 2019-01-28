/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

/**
 * Exception thrown when a Id Pedido already exists.
 * @author Leticia Garcia
 */
public class IdPedidoExistsException extends BusinessLogicException {

    public IdPedidoExistsException(String msg) {
        super(msg);
    }
    
}