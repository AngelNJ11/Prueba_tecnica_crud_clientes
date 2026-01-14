package com.ang.nav.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nomCompleto;

    @Column(name = "nro_documento",nullable = false, length = 20)
    private String nroDocumento;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String celular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoCliente tipoCliente;

}
