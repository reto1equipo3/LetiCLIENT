/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

/**
 * Exception thrown when a Login already exists.
 * @author Igor
 */
public class IdExistsException extends BusinessLogicException {

    public IdExistsException(String msg) {
        super(msg);
    }
    
}
