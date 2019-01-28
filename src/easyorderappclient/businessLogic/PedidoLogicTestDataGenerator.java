/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.businessLogic;

import easyorderappclient.transferObjects.Pedido;
import java.util.Collection;

/**
 *
 * @author Leticia Garcia
 */
public class PedidoLogicTestDataGenerator implements PedidoLogic {

    public PedidoLogicTestDataGenerator() {
    }

    @Override
    public Collection<Pedido> buscarTodosLosPedidos() throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void añadirPedido(Pedido pedido) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarEstado(Pedido pedido) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarPedido(Pedido pedido) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void isIdPedidoExisting(Integer id) throws IdPedidoExistsException, BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
