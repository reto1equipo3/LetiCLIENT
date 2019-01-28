/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * Entidad clase JPA para datos de pedido. Las propiedades de esta clase son ,
 * Nombre, fecha tramitado, fecha preparado, fecha entregado y estado.
 *
 * @author Leticia Garcia
 */
@XmlRootElement
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Nombre del pedido
     */
    private String nombre;
    /**
     * {@link Empleado} del pedido
     */
    private List<Empleado> empleados;
  
     /**
     * fechaPedido del pedido
     */
    private java.util.Date fechaPedido;
     /**
     * horaPedido del pedido
     */
    private String horaPedido;
     /**
     * Codigo field for pedido entity.
     */
    private final SimpleIntegerProperty codigo;
     /**
     * {@link Cliente} del pedido
     */
    private final SimpleObjectProperty<Cliente> cliente;
    /**
     * fechaTramitado del pedido
     */
    private final SimpleObjectProperty fechaTramitado;
     /**
     * fechaPreparado del pedido
     */
    private final SimpleObjectProperty fechaPreparado;
     /**
     * fechaEntregado del pedido
     */
    private final SimpleObjectProperty fechaEntregado;
    /**
     * {@link EstadoPedido} value for the pedido.
     */
    private final SimpleObjectProperty<EstadoPedido> estado;
    /**
     * {@link ProductoPedido} del pedido
     */
    private final SimpleObjectProperty<List<ProductoPedido>> productoPedido;
    // private final SimpleListProperty<ProductoPedido> productoPedido;

    //Constructor
    public Pedido() {
        this.nombre= new String("");
        this.empleados = new ArrayList<Empleado>();
        this.fechaPedido = new Date();
        this.horaPedido = new String();
        this.codigo = new SimpleIntegerProperty();
        this.cliente = new SimpleObjectProperty();
        this.fechaTramitado = new SimpleObjectProperty();
        this.fechaPreparado = new SimpleObjectProperty();
        this.fechaEntregado = new SimpleObjectProperty();
        this.estado = new SimpleObjectProperty();
        this.productoPedido = new SimpleObjectProperty();
    }

  /*  public Pedido(Integer codigo, Cliente cliente, java.util.Date fechaTramitado, java.util.Date fechaPreparado, java.util.Date fechaEntregado, EstadoPedido estado, List<ProductoPedido> productoPedido) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.cliente = new SimpleObjectProperty(cliente);
        this.fechaTramitado = new SimpleObjectProperty(fechaTramitado);
        this.fechaPreparado = new SimpleObjectProperty(fechaPreparado);
        this.fechaEntregado = new SimpleObjectProperty(fechaEntregado);
        this.estado = new SimpleObjectProperty(estado);
        this.productoPedido = new SimpleObjectProperty<List<ProductoPedido>>(productoPedido);

    }*/

    //Getters and setters
    @XmlElement(name = "id")
    public Integer getCodigo() {
        return this.codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public Cliente getCliente() {
        return this.cliente.get();
    }

    public void setCliente(Cliente cliente) {
        this.cliente.set(cliente);
    }

    public List<ProductoPedido> getProductos() {
        return this.productoPedido.get();
    }

    public void setProductos(List<ProductoPedido> productoPedido) {
        this.productoPedido.set(productoPedido);
    }

    public java.util.Date getFechaPreparado() {
        return (java.util.Date) this.fechaPreparado.get();
    }

    public void setFechaPreparado(java.util.Date fechaPreparado) {
        this.fechaPreparado.set(fechaPreparado);
    }

    public java.util.Date getFechaEntregado() {
        return (java.util.Date) this.fechaEntregado.get();
    }

    public void setFechaEntregado(java.util.Date fechaEntregado) {
        this.fechaEntregado.set(fechaEntregado);
    }

    public java.util.Date getFechaTramitado() {
        return (java.util.Date) this.fechaTramitado.get();

    }

    public void setFechaTramitado(java.util.Date fechaTramitado) {
        this.fechaTramitado.set(fechaTramitado);
    }

    @XmlElement(name = "estadoPedido")
    public EstadoPedido getEstado() {
        return this.estado.get();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado.set(estado);
    }

    //Getters and setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @XmlTransient
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public String getHora() {
        return horaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setHora(String horaPedido) {
        this.horaPedido = horaPedido;
    }
    
    @Override
    public String toString() {
        return codigo.toString();
    }


}
