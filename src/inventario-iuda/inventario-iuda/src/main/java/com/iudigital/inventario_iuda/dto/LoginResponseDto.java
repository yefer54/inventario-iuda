package com.iudigital.inventario_iuda.dto;

public class LoginResponseDto {
    private String token;
    private String role;
    private String nombre;
    private String email;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, String role, String nombre, String email) {
        this.token = token;
        this.role = role;
        this.nombre = nombre;
        this.email = email;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
