
package com.example.api.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuarios")
public class UserModel {

    @Id
    @Column(name = "Correo_electronico")
    private String correoElectronico;

    private String nombre;
    private String apellido;
    private String contrasena;

    // Relación inversa: un usuario puede tener muchas aplicaciones
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore //  evita bucles infinitos al serializar
    private List<AplicacionModel> aplicaciones;

    // ==== Getters y setters ====

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<AplicacionModel> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(List<AplicacionModel> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "correoElectronico='" + correoElectronico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}