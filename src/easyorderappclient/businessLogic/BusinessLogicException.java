/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

/**
 * Exception associated to an error produced in the business logic tier.
 * @author Leticia Garcia
 */
public class BusinessLogicException extends Exception {
    public BusinessLogicException(String msg){
        super(msg);
    }
}