package com.ang.nav.services.impl;

import com.ang.nav.exception.ClienteNotFoundException;
import com.ang.nav.exception.TipoClienteNotFoundException;
import com.ang.nav.model.dao.ClienteDao;
import com.ang.nav.model.dao.TipoClienteDao;
import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.entity.Cliente;
import com.ang.nav.model.entity.TipoCliente;
import com.ang.nav.services.ICliente;
import com.ang.nav.services.ITipoCliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteImpl implements ICliente {

    private final ClienteDao clienteDao;
    private final TipoClienteDao tipoClienteDao;
    private final ITipoCliente tipoClienteService;


    public ClienteImpl(ClienteDao clienteDao, TipoClienteDao tipoClienteDao, ITipoCliente tipoClienteService) {
        this.clienteDao = clienteDao;
        this.tipoClienteDao = tipoClienteDao;
        this.tipoClienteService = tipoClienteService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientes(String nombre, String nroDocumento, Integer idTipo) {
        return clienteDao.buscarClientes(nombre,nroDocumento,idTipo);
    }

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDto) {

        TipoCliente tipoCliente = tipoClienteService.findById(clienteDto.getTipoCliente());

        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombreCompleto(clienteDto.getNombre())
                .nroDocumento(clienteDto.getNroDocumento())
                .email(clienteDto.getEmail())
                .celular(clienteDto.getCelular())
                .tipoCliente(tipoCliente)
                .build();

        return clienteDao.save(cliente);
    }

    @Transactional
    @Override
    public Cliente update(ClienteDTO clienteDto) {
        Cliente clienteExistente = clienteDao.findById(clienteDto.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente con el id " + clienteDto.getIdCliente() + " no encontrado"
                ));

        TipoCliente tipoCliente = tipoClienteService.findById(clienteDto.getTipoCliente());

        clienteExistente.setNombreCompleto(clienteDto.getNombre());
        clienteExistente.setNroDocumento(clienteDto.getNroDocumento());
        clienteExistente.setEmail(clienteDto.getEmail());
        clienteExistente.setCelular(clienteDto.getCelular());
        clienteExistente.setTipoCliente(tipoCliente);

        return clienteDao.save(clienteExistente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public void delete( Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Transactional
    @Override
    public Cliente actualizarTipoCliente(Integer idCliente, Integer idTipo) {

        Cliente cliente = clienteDao.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + idCliente + " no encontrado"));

        TipoCliente tipoCliente = tipoClienteDao.findById(idTipo)
                .orElseThrow(() -> new TipoClienteNotFoundException("Tipo de cliente con id " + idTipo + " no existe"));

        cliente.setTipoCliente(tipoCliente);
        return clienteDao.save(cliente);
    }

    @Override
    public List<ClienteDTO> obtenerClientesFiltrados() {
        List<Object[]> result = clienteDao.filtrarClientes();
        return result.stream()
                .map(r -> new ClienteDTO(
                        (Integer) r[0],
                        (String) r[1],
                        (String) r[2],
                        (String) r[3],
                        (String) r[4],
                        (Integer) r[5]
                ))
                .collect(Collectors.toList());
    }


}
