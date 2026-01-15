package com.ang.nav.services.impl;

import com.ang.nav.model.dao.ClienteDao;
import com.ang.nav.model.dao.TipoClienteDao;
import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.entity.Cliente;
import com.ang.nav.model.entity.TipoCliente;
import com.ang.nav.services.ICliente;
import com.ang.nav.services.ITipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteImpl implements ICliente {

    private final ClienteDao clienteDao;
    private final ITipoCliente tipoClienteService;

    public ClienteImpl(ClienteDao clienteDao, ITipoCliente tipoClienteService) {
        this.clienteDao = clienteDao;
        this.tipoClienteService = tipoClienteService;
    }

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDto) {

        TipoCliente tipoCliente = tipoClienteService.findById(clienteDto.getTipoCliente());

        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nomCompleto(clienteDto.getNombre())
                .nroDocumento(clienteDto.getNroDocumento())
                .email(clienteDto.getEmail())
                .celular(clienteDto.getCelular())
                .tipoCliente(tipoCliente)
                .build();

        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete( Cliente cliente) {
        clienteDao.delete(cliente);
    }
}
