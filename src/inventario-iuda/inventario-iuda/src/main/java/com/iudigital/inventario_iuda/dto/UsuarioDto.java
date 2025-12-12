package com.iudigital.inventario_iuda.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDto {
    @NotBlank
    private String nombre;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Boolean activo;

    public UsuarioDto() {}
    public UsuarioDto(String nombre, String email, Boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
