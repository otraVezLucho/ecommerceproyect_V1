package com.ecommerce.beta1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detallesOrdem")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento de atributo id
    private Integer id;
    private String nombre;
    private double precio;
    private String cantidad;
    private String total;


    @OneToOne
    private Orden orden;

    @ManyToOne // Varios productos pueden estar en el detalle de la orden
    private Producto producto;

    public DetalleOrden() {
    }

    public DetalleOrden(Integer id, String nombre, double precio, String cantidad, String total) {
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
