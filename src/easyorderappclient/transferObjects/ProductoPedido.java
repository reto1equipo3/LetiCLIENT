/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Entidad clase JPA para datos relacionados entre pedido y productos.
 *
 * @author Leticia Garcia
 */
@XmlRootElement(name = "productos")
public class ProductoPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pedido pedido;
    private Producto producto;
    private final SimpleIntegerProperty cantidad;
    private SimpleDoubleProperty precio;

    //Constructor
    public ProductoPedido() {
        this.pedido = new Pedido();
        this.producto = new Producto();
        // this.producto= new SimpleObjectProperty<>();
        this.cantidad = new SimpleIntegerProperty();
        this.precio = new SimpleDoubleProperty();
       this.setPrecio(getCantidad() * this.getProducto().getPrecioUnidad());
    }

    //Getters and setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.setPrecio(getCantidad() * producto.getPrecioUnidad());
        this.producto = producto;
    }

    public Integer getCantidad() {
        return this.cantidad.get();
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad.set(cantidad);
       this.setPrecio(cantidad * this.getProducto().getPrecioUnidad());

    }

    public Double getPrecio() {
        return this.precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }

    @Override
    public String toString() {

        return precio.toString();
    }

}
