package com.iudigital.inventario_iuda.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"serial"}),
        @UniqueConstraint(columnNames = {"modelo"})
})
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serial;

    @Column(nullable = false, unique = true)
    private String modelo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String color;

    private LocalDate fechaCompra;

    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_equipo_id")
    private EstadoEquipo estadoEquipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_equipo_id")
    private TipoEquipo tipoEquipo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Inventario() {}

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public EstadoEquipo getEstadoEquipo() { return estadoEquipo; }
    public void setEstadoEquipo(EstadoEquipo estadoEquipo) { this.estadoEquipo = estadoEquipo; }

    public TipoEquipo getTipoEquipo() { return tipoEquipo; }
    public void setTipoEquipo(TipoEquipo tipoEquipo) { this.tipoEquipo = tipoEquipo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
