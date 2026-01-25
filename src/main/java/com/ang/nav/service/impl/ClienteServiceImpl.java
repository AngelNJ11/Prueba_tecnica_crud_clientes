package com.ang.nav.service.impl;

import com.ang.nav.exception.ClienteNotFoundException;
import com.ang.nav.exception.TipoClienteNotFoundException;
import com.ang.nav.repository.ClienteRepository;
import com.ang.nav.repository.TipoClienteRepository;
import com.ang.nav.dto.ClienteDTO;
import com.ang.nav.entity.Cliente;
import com.ang.nav.entity.TipoCliente;
import com.ang.nav.service.ClienteService;
import com.ang.nav.service.TipoClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;
    private final TipoClienteService tipoClienteService;


    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, TipoClienteService tipoClienteService) {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.tipoClienteService = tipoClienteService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientes(String nombre, String nroDocumento, Integer idTipo) {
        return clienteRepository.buscarClientes(nombre,nroDocumento,idTipo);
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

        return clienteRepository.save(cliente);
    }

    @Transactional
    @Override
    public Cliente update(ClienteDTO clienteDto) {
        Cliente clienteExistente = clienteRepository.findById(clienteDto.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente con el id " + clienteDto.getIdCliente() + " no encontrado"
                ));

        TipoCliente tipoCliente = tipoClienteService.findById(clienteDto.getTipoCliente());

        clienteExistente.setNombreCompleto(clienteDto.getNombre());
        clienteExistente.setNroDocumento(clienteDto.getNroDocumento());
        clienteExistente.setEmail(clienteDto.getEmail());
        clienteExistente.setCelular(clienteDto.getCelular());
        clienteExistente.setTipoCliente(tipoCliente);

        return clienteRepository.save(clienteExistente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public void delete( Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Transactional
    @Override
    public Cliente actualizarTipoCliente(Integer idCliente, Integer idTipo) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + idCliente + " no encontrado"));

        TipoCliente tipoCliente = tipoClienteRepository.findById(idTipo)
                .orElseThrow(() -> new TipoClienteNotFoundException("Tipo de cliente con id " + idTipo + " no existe"));

        cliente.setTipoCliente(tipoCliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public List<ClienteDTO> obtenerClientesFiltrados() {
        List<Object[]> result = clienteRepository.filtrarClientes();
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
