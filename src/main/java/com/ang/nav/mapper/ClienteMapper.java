package com.ang.nav.mapper;

import org.openapitools.model.ClienteRequestDTO;
import org.openapitools.model.ClienteResponseAuditDTO;
import org.openapitools.model.ClienteResponseDTO;
import com.ang.nav.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.FiltrarCliente200Response;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "nombre", target = "nombreCompleto")
    @Mapping(source = "tipoCliente", target = "tipoCliente.idTipo")
    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(source = "nombreCompleto", target = "nombre")
    @Mapping(source = "tipoCliente.idTipo", target = "tipoCliente")
    ClienteResponseDTO toDto(Cliente entity);

    @Mapping(source = "nombreCompleto", target = "nombre")
    @Mapping(source = "tipoCliente.idTipo", target = "tipoCliente")
    ClienteResponseAuditDTO toAuditDto(Cliente entity);

    List<ClienteResponseDTO> toDtoList(List<Cliente> entities);
    List<ClienteResponseAuditDTO> toAuitDtoList(List<Cliente> entities);


    @Mapping(target = "idCliente", ignore = true)
    @Mapping(source = "nombre", target = "nombreCompleto")
    @Mapping(target = "tipoCliente", ignore = true)
    void actualizarEntityDto(ClienteRequestDTO dto, @MappingTarget Cliente entity);




    default FiltrarCliente200Response toFiltrarCliente(Page<Cliente> page) {
        FiltrarCliente200Response response =
                new FiltrarCliente200Response();

        response.setContent(toDtoList(page.getContent()));
        response.setTotalElements((int) page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setNumber(page.getNumber());
        response.setSize(page.getSize());

        return response;
    }

    default OffsetDateTime map(LocalDateTime value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default LocalDateTime map(OffsetDateTime value) {
        return value == null ? null : value.toLocalDateTime();
    }

}
