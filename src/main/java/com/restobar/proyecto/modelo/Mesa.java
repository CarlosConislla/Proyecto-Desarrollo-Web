package com.restobar.proyecto.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull(message = "Ingrese el número de la mesa")
    @Column(unique = true)
    int mesa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public Mesa() {
    }
}
