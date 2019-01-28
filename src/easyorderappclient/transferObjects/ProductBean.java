/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObject;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Igor
 */
@XmlRootElement(name="producto")
public class ProductBean implements Serializable{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty precioUnidad;
    private final SimpleDoubleProperty stock;
    
    public ProductBean(){
        this.id=new SimpleIntegerProperty();
        this.nombre=new SimpleStringProperty();
        this.precioUnidad=new SimpleIntegerProperty();
        this.stock=new SimpleDoubleProperty();
    }
    
    public ProductBean(Integer id,
                    String nombre,
                    Integer precioUnidad,
                    double stock){
        this.id=new SimpleIntegerProperty(id);
        this.nombre=new SimpleStringProperty(nombre);
        this.precioUnidad=new SimpleIntegerProperty(precioUnidad);
        this.stock=new SimpleDoubleProperty(stock);
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
    
    
    public Integer getPrecioUnidad(){
        return this.precioUnidad.get();
    }
    public void setPrecioUnidad(Integer precioUnidad){
        this.precioUnidad.set(precioUnidad);
    }
    
    
    public double getStock(){
        return this.stock.get();
    }
    public void setStock(double stock){
        this.stock.set((int) stock);
    }
}
