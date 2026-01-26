package com.ang.nav.service;

import com.ang.nav.dto.ClienteRequestDTO;
import com.ang.nav.dto.ClienteResponseAuditDTO;
import com.ang.nav.dto.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    Page<ClienteResponseDTO> buscarClientes(String nombre, String nroDocumento, Integer idTipo, Pageable pageable);

    ClienteResponseDTO save(ClienteRequestDTO clienteDto);

    ClienteResponseDTO update(Integer id, ClienteRequestDTO clienteDto);

    ClienteResponseAuditDTO findById(Integer id);

    void delete(Integer cliente);

    ClienteResponseDTO actualizarTipoCliente(Integer idCliente , Integer idTipo);

    List<ClienteResponseAuditDTO> obtenerClientesFiltrados();
}
