package com.ang.nav.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteResponseDTO {

    private Integer idCliente;
    private String nombre;
    private String nroDocumento;
    private String email;
    private String celular;
    private Integer tipoCliente;

}
