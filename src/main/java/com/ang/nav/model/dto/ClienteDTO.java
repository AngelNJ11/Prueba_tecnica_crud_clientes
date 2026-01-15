package com.ang.nav.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteDTO {

    private Integer idCliente;
    private String nombre;
    private String nroDocumento;
    private String email;
    private String celular;
    private Integer tipoCliente;

}
