/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;


/**
 * This enumeration is for orders status. Includes the values TRAMITADO, PREPARADO and ENVIADO
 * @author Leticia Garcia
 */
public enum EstadoPedido {
   
    /**
     * Cuando el pedido esta tramitado por el cliente.
     */
    TRAMITADO,
    /**
     * Cuando el pedido ha sido preparado por el empleado.
     */
    PREPARADO,
     /**
     * Cuando el pedido ha sido enviado.
     */
    ENVIADO;
}
