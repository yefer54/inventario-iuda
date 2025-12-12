package com.iudigital.inventario_iuda.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MarcaDto {
    @NotBlank
    private String nombre;
    @NotNull
    private Boolean activo;

    public MarcaDto() {}
    public MarcaDto(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
