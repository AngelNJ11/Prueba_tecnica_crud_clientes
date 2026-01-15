package com.ang.nav.services;

import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.entity.Cliente;

import java.util.List;

public interface ICliente {

    List<Cliente> buscarClientes(String nombre, String nroDocumento, Integer idTipo);

    Cliente save(ClienteDTO cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

}
