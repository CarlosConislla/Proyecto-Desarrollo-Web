package com.restobar.proyecto.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Ingrese su nombre")
    private String nombre;

    @NotBlank(message = "Ingrese sus apellidos")
    private String apellido;

    @NotNull(message = "Ingrese su telefono")
    private int telefono;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    @Column(unique = true)
    @NotNull(message = "Ingrese una fecha correcta")
    private LocalDate fecha;

    @NotNull(message = "Ingrese el numero de personas")
    private int npersonas;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNpersonas() {
        return npersonas;
    }

    public void setNpersonas(int npersonas) {
        this.npersonas = npersonas;
    }

    public Reserva() {
    }
}
