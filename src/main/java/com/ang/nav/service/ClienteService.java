package com.ang.nav.service;

import com.ang.nav.dto.ClienteDTO;
import com.ang.nav.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> buscarClientes(String nombre, String nroDocumento, Integer idTipo);

    Cliente save(ClienteDTO cliente);

    Cliente update(ClienteDTO cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    Cliente actualizarTipoCliente(Integer idCliente , Integer idTipo);

    List<ClienteDTO> obtenerClientesFiltrados();
}
