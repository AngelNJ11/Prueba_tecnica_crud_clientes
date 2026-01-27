package com.ang.nav.service;

import org.openapitools.model.ClienteRequestDTO;
import org.openapitools.model.ClienteResponseAuditDTO;
import org.openapitools.model.ClienteResponseDTO;
import org.openapitools.model.FiltrarCliente200Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    FiltrarCliente200Response buscarClientes(String nombre, String nroDocumento, Integer idTipo, Integer page, Integer size);

    ClienteResponseDTO save(ClienteRequestDTO clienteDto);

    ClienteResponseDTO update(Integer id, ClienteRequestDTO clienteDto);

    ClienteResponseAuditDTO findById(Integer id);

    void delete(Integer cliente);

    ClienteResponseDTO actualizarTipoCliente(Integer idCliente , Integer idTipo);

    List<ClienteResponseAuditDTO> obtenerClientesFiltrados();
}
