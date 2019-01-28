/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

import javax.naming.OperationNotSupportedException;

/**
 * This class is a factory for {@link PedidoLogic} interface implementing objects.
 * @author Leticia Garcia
 */
public class PedidoLogicFactory {
      /**
     * PedidoLogic type for client of a RESTful web service. 
     */
    public static final String REST_WEB_CLIENT_TYPE="REST_WEB_CLIENT";
    /**
     * PedidoLogic type for a manager producing fake data for test purposes. 
     */
    public static final String TEST_MOCK_TYPE="TEST_MOCK";
    /**
     * Factory creation method. It returns different {@link PedidoLogic} interface implementing 
     * objects depending on the type parameter value.
     * @param type Type of implementation for object being returned.
     * @return An object implementing PedidoLogic according to type.
     * @throws OperationNotSupportedException If type is not supported.
     */
    public static PedidoLogic createPedidoLogic (String type) 
            throws OperationNotSupportedException{
        //The object to be returned.
        PedidoLogic pedidoLogic=null;
        //Evaluate type parameter.
        switch(type){
            case REST_WEB_CLIENT_TYPE:
                //If rest web client type is asked for, use PedidoLogicImplementation.
                pedidoLogic=(PedidoLogic) new PedidoLogicImplementation();
                break;
            case TEST_MOCK_TYPE:
                //If rest fake data test type is asked for, use PedidoLogicTestDataGenerator.
                pedidoLogic=(PedidoLogic) new PedidoLogicTestDataGenerator();
                break;
            default:
                //If type is not one of the types accepted.
                throw new OperationNotSupportedException("Pedido Loigc type not supported.");
        }
        return pedidoLogic;
    }
    
    
}
