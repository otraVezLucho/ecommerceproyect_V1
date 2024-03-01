package com.ecommerce.beta1.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esta anotacion de se usa para que el atributo de id sea autoincrementable
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaIngreso;
    private double total;

    //Revisar V.42
    @ManyToOne // Varias ordenes de 1 usuario
    private Usuario usuario;

    //Revisar V.42
    @OneToMany(mappedBy = "orden") // Una orden puede tener muchos detalles
    private List<DetalleOrden> detalle; // se crea una lista porque detalles orden trae muchos datos.

    public Orden() {
    }

    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaIngreso, double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaIngreso = fechaIngreso;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaIngreso=" + fechaIngreso +
                ", total=" + total +
                '}';
    }
}
