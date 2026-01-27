package com.ang.nav.controller;


import com.ang.nav.service.ClienteService;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.openapitools.api.ApiApi;

import java.util.List;

@RestController
@RequestMapping
public class ClienteController implements ApiApi{

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteServices) {
        this.clienteService = clienteServices;
    }

    @Override public ResponseEntity<FiltrarCliente200Response> filtrarCliente(
            String nombre,
            String nroDocumento,
            Integer idTipo,
            Integer page,
            Integer size
    ) {
        FiltrarCliente200Response response = clienteService.buscarClientes(nombre, nroDocumento, idTipo, page, size);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> apiClientesPost(ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO crear = clienteService.save(clienteRequestDTO);
        return ResponseEntity.status(201).body(crear);
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> apiClientesIdPut(Integer id, ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO actualizar = clienteService.update(id, clienteRequestDTO);
        return ResponseEntity.status(201).body(actualizar);
    }

    @Override
    public ResponseEntity<Void> apiClientesIdDelete(Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ClienteResponseAuditDTO> apiClientesIdGet(Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> apiClientesIdIdTipoPatch(
            Integer id,
            Integer idTipo
    ){
        ClienteResponseDTO clienteActualizado = clienteService.actualizarTipoCliente(id, idTipo);
        return ResponseEntity.ok(clienteActualizado);
    }

    @Override
    public ResponseEntity<List<ClienteResponseAuditDTO>> apiClientesReporteGet(){
        return ResponseEntity.ok(clienteService.obtenerClientesFiltrados());
    }
}
