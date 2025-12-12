package com.iudigital.inventario_iuda.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InventarioDto {

    @NotBlank
    private String serial;

    @NotBlank
    private String modelo;

    private String descripcion;
    private String color;
    private LocalDate fechaCompra;
    private BigDecimal precio;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long marcaId;

    @NotNull
    private Long estadoEquipoId;

    @NotNull
    private Long tipoEquipoId;

    public InventarioDto() {}

    // getters y setters
    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getMarcaId() { return marcaId; }
    public void setMarcaId(Long marcaId) { this.marcaId = marcaId; }

    public Long getEstadoEquipoId() { return estadoEquipoId; }
    public void setEstadoEquipoId(Long estadoEquipoId) { this.estadoEquipoId = estadoEquipoId; }

    public Long getTipoEquipoId() { return tipoEquipoId; }
    public void setTipoEquipoId(Long tipoEquipoId) { this.tipoEquipoId = tipoEquipoId; }
}
