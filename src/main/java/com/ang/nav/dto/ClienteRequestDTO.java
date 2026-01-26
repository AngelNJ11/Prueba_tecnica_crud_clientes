package com.ang.nav.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteRequestDTO {


    private Integer idCliente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El número de documento es obligatorio")
    private String nroDocumento;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El celular es obligatorio")
    private String celular;

    @NotNull(message = "El tipo de cliente es obligatorio")
    private Integer tipoCliente;


}
