package com.ang.nav.service.impl;

import com.ang.nav.dto.ClienteResponseAuditDTO;
import com.ang.nav.dto.ClienteResponseDTO;
import com.ang.nav.exception.ClienteNotFoundException;
import com.ang.nav.exception.TipoClienteNotFoundException;
import com.ang.nav.mapper.ClienteMapper;
import com.ang.nav.repository.ClienteRepository;
import com.ang.nav.repository.TipoClienteRepository;
import com.ang.nav.dto.ClienteRequestDTO;
import com.ang.nav.entity.Cliente;
import com.ang.nav.entity.TipoCliente;
import com.ang.nav.service.ClienteService;
import com.ang.nav.service.TipoClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;
    private final TipoClienteService tipoClienteService;
    private final ClienteMapper clienteMapper;


    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoClienteRepository tipoClienteRepository, TipoClienteService tipoClienteService, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.tipoClienteRepository = tipoClienteRepository;
        this.tipoClienteService = tipoClienteService;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Page<ClienteResponseDTO> buscarClientes(String nombre, String nroDocumento, Integer idTipo, Pageable pageable) {
        return clienteRepository.buscarClientes(nombre,nroDocumento,idTipo,pageable).map(clienteMapper::toDto);
    }

    @Transactional
    @Override
    public ClienteResponseDTO save(ClienteRequestDTO clienteRequestDto) {
        Cliente cliente = clienteMapper.toEntity(clienteRequestDto);
        cliente.setTipoCliente(tipoClienteService.findById(clienteRequestDto.getTipoCliente()));
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Transactional
    @Override
    public ClienteResponseDTO update(Integer id,ClienteRequestDTO clienteRequestDto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente con el id " + clienteRequestDto.getIdCliente() + " no encontrado"
                ));

        clienteMapper.actualizarEntityDto(clienteRequestDto, clienteExistente);
        TipoCliente tipoCliente = tipoClienteRepository.findById(clienteRequestDto.getTipoCliente())
                .orElseThrow(() -> new TipoClienteNotFoundException(
                        "El tipo de cliente no fue encontrado"
                ));
        clienteExistente.setTipoCliente(tipoCliente);
        Cliente clienteActualizado = clienteRepository.save(clienteExistente);
        return clienteMapper.toDto(clienteActualizado);
    }

    @Override
    public ClienteResponseAuditDTO findById(Integer id) {
        return clienteMapper.toAuditDto( clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + id + " no encontrado"))
        );
    }

    @Transactional
    @Override
    public void delete( Integer id) {
        clienteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClienteResponseDTO actualizarTipoCliente(Integer idCliente, Integer idTipo) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con id " + idCliente + " no encontrado"));

        TipoCliente tipoCliente = tipoClienteRepository.findById(idTipo)
                .orElseThrow(() -> new TipoClienteNotFoundException("Tipo de cliente con id " + idTipo + " no existe"));

        cliente.setTipoCliente(tipoCliente);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteResponseAuditDTO> obtenerClientesFiltrados() {
        return clienteMapper.toAuitDtoList(clienteRepository.filtrarClientes());
    }


}
