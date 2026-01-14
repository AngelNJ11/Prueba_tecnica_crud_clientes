package com.ang.nav.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tipo_cliente")
public class TipoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column( nullable = false, length = 50)
    private String descripcion;

    @OneToMany(mappedBy = "tipoCliente", fetch = FetchType.LAZY)
    private List<Cliente> clientes;

}
