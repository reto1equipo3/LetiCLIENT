/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Igor
 */

@XmlRootElement
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
  
    private Integer id;
    private String nombre;
    private Integer stock;
    //private Double precioUnidad;
    private Double precioUnidad;  
    private List<ProductoPedido> pedidos;
    
    
        //Constructor
    public Producto() {
        this.id = new Integer(0);
        this.nombre = new String("");
        this.stock = new Integer(0);
        this.precioUnidad = new Double(0.0);
        this.pedidos = new ArrayList<ProductoPedido>();
    }
     
    
    
    //Getters and setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

   /* public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

      public Double getPrecioUnidad() {
        return precioUnidad;
    }
   */
  /*  public Double getPrecioUnidad(){
    return this.precioUnidad.get();
    }
    public void setPrecioUnidad(Double precio){
    this.precioUnidad.set(precio);
    }*/

    public Double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public Integer getStock() {
        return stock;
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<ProductoPedido> getProductosPedido() {
        return pedidos;
    }

    public void setProductosPedido(List<ProductoPedido> pedidos) {
        this.pedidos = pedidos;
    }

    
    
   //Metodoak
    
    
       @Override
    public String toString(){
  
    return nombre;
    
    //Como pongo dos variables? 
    
    }
    
}
