package com.formula1;

public class Piloto {

    private final int id;
    private String nombre;
    private String pais;
    private String equipo;
    private String rol;
    private Integer experiencia;
    private Integer habilidad;

    public Piloto(int id, String nombre, String pais, String equipo, String rol, Integer experiencia, Integer habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.equipo = equipo;
        this.rol = rol;
        this.experiencia = experiencia;
        this.habilidad = habilidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getPais() {
        return pais;
    }

    public String getRol() {
        return rol;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public Integer getHabilidad() {
        return habilidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public void setHabilidad(Integer habilidad) {
        this.habilidad = habilidad;
    }
}
