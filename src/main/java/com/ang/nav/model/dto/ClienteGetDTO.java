package com.ang.nav.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteGetDTO {

    private Integer idCliente;
    private String nombre;
    private String nroDocumento;
    private String email;
    private String celular;

    private Integer tipoCliente;
    private String descripcion;

}
