package com.ang.nav.services;

import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDTO cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

}
