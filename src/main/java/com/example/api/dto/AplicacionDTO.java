package com.example.api.dto;

import com.example.api.Entity.AplicacionModel;

public class AplicacionDTO {

    private String aplicacion;
    private String nombreUsuario;
    private String contrasena;
    private String emailRecuperacion;
    private String correoUsuario;

    public AplicacionDTO(AplicacionModel app) {
        this.aplicacion = app.getAplicacion();
        this.nombreUsuario = app.getNombreUsuario();
        this.contrasena = app.getContrasena();
        this.emailRecuperacion = app.getEmailRecuperacion();
        this.correoUsuario = (app.getUsuario() != null) ? app.getUsuario().getCorreoElectronico() : null;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmailRecuperacion() {
        return emailRecuperacion;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }
}
