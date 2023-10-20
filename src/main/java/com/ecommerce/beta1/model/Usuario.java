package com.ecommerce.beta1.model;

public class Usuario {


    private Integer id;
    private String nombre;
    private String userName;
    private String correo;
    private String direccion;
    private String telefono;
    private String tipo;
    private String clave;

    // Constructores
   public Usuario() {
    }

    public Usuario(Integer id, String nombre, String userName, String correo, String direccion, String telefono, String tipo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.clave = clave;
    }

    //Getters y setters
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
