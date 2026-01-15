package com.ang.nav.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "cliente")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(name = "nro_documento",nullable = false, length = 20)
    private String nroDocumento;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 15 ,nullable = false)
    private String celular;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoCliente tipoCliente;

}
