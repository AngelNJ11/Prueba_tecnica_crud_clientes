package com.ang.nav.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteResponseAuditDTO {

    private String nombre;
    private String nroDocumento;
    private String email;
    private String celular;
    private Integer tipoCliente;

    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
