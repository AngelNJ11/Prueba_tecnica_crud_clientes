package com.ang.nav.mapper;

import com.ang.nav.dto.ClienteRequestDTO;
import com.ang.nav.dto.ClienteResponseAuditDTO;
import com.ang.nav.dto.ClienteResponseDTO;
import com.ang.nav.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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



}
