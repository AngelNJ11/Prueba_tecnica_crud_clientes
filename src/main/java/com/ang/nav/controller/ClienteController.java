package com.ang.nav.controller;

import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.dto.ClienteGetDTO;
import com.ang.nav.model.entity.Cliente;
import com.ang.nav.services.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private ICliente clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarCliente(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String nroDocumento,
            @RequestParam(required = false) Integer idTipo
    ) {
        return ResponseEntity.ok(
                clienteService.buscarClientes(nombre, nroDocumento, idTipo)
        );
    }

    @PostMapping
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteSave = clienteService.save(clienteDTO);
        return ClienteDTO.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombreCompleto())
                .nroDocumento(clienteSave.getNroDocumento())
                .email(clienteSave.getEmail())
                .celular(clienteSave.getCelular())
                .tipoCliente(clienteSave.getTipoCliente().getIdTipo())
                .build();

    }

    @PutMapping
    public ClienteDTO update(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteUpdate =  clienteService.save(clienteDTO);
        return ClienteDTO.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombreCompleto())
                .nroDocumento(clienteUpdate.getNroDocumento())
                .email(clienteUpdate.getEmail())
                .celular(clienteUpdate.getCelular())
                .tipoCliente(clienteUpdate.getTipoCliente().getIdTipo())
                .build();

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteService.findById(id);
        clienteService.delete(clienteDelete);
    }

    @GetMapping("/{id}")
    public ClienteGetDTO showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        return ClienteGetDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombreCompleto())
                .nroDocumento(cliente.getNroDocumento())
                .email(cliente.getEmail())
                .celular(cliente.getCelular())
                .tipoCliente(cliente.getTipoCliente().getIdTipo())
                .descripcion(cliente.getTipoCliente().getDescripcion())
                .build();
    }

}
