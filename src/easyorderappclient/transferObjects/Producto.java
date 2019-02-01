/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import easyorderappclient.transferObjects.ProductoPedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Data Transfer Object used in UI and client side for representing Product entity.
 * It is also used as data model for a TableView in the UI
 * @author Igor
 */
@XmlRootElement(name="producto")
public class Producto implements Serializable{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleDoubleProperty precioUnidad;
    private final SimpleDoubleProperty stock;
    private List<ProductoPedido> pedidos;
    
    public Producto(){
        this.id=new SimpleIntegerProperty(0);
        this.nombre=new SimpleStringProperty("");
        this.precioUnidad=new SimpleDoubleProperty(0.0);
        this.stock=new SimpleDoubleProperty(0.0);
        this.pedidos = new ArrayList<ProductoPedido>();
    }
   
    public Producto(Integer id,
                    String nombre,
                    double precioUnidad,
                    double stock){
        this.id=new SimpleIntegerProperty(id);
        this.nombre=new SimpleStringProperty(nombre);
        this.precioUnidad=new SimpleDoubleProperty(precioUnidad);
        this.stock=new SimpleDoubleProperty(stock);
//        this.pedidos = new ArrayList<ProductoPedido>(pedidos);
    }
    
    public Integer getId(){
        return this.id.get();
    }
    public void setId(Integer id){
        this.id.set(id);
    }
    
    
    public String getNombre(){
        return this.nombre.get();
    }
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    
    public Double getPrecioUnidad(){
        return this.precioUnidad.get();
    }
    public void setPrecioUnidad(Double precioUnidad){
        this.precioUnidad.set(precioUnidad);
    }
    
    
    public double getStock(){
        return this.stock.get();
    }
    public void setStock(double stock){
        this.stock.set((int) stock);
    }
    
    public List<ProductoPedido> getProductosPedido() {
        return pedidos;
    }

    public void setProductosPedido(List<ProductoPedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    @Override
    public String toString(){
  
    return nombre.get();
    }
}
