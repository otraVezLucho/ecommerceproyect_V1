package com.ecommerce.beta1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detallesOrden")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento de atributo id
    private Integer id;
    private String nombre;
    private double precio;
    private double cantidad;
    private double total;


    @ManyToOne // muchos a uno porque vvarios detalles pueden pertenecer a una orden
    private Orden orden;

    @ManyToOne // Varios productos pueden estar en el detalle de la orden
    private Producto producto;

    public DetalleOrden() {
    }

    public DetalleOrden(Integer id, String nombre, double precio, double cantidad, double total) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad='" + cantidad + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
