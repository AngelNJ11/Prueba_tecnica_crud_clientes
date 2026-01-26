package com.ang.nav.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
