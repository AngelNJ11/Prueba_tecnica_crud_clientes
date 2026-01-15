package com.ang.nav.controller;

import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.dto.ClienteGetDTO;
import com.ang.nav.model.entity.Cliente;
import com.ang.nav.services.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClienteController {


    @Autowired
    private ICliente clienteService;

    @PostMapping("/clientes")
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteSave = clienteService.save(clienteDTO);
        return ClienteDTO.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNomCompleto())
                .nroDocumento(clienteSave.getNroDocumento())
                .email(clienteSave.getEmail())
                .celular(clienteSave.getCelular())
                .tipoCliente(clienteSave.getTipoCliente().getIdTipo())
                .build();

    }

    @PutMapping("/clientes")
    public ClienteDTO update(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteUpdate =  clienteService.save(clienteDTO);
        return ClienteDTO.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNomCompleto())
                .nroDocumento(clienteUpdate.getNroDocumento())
                .email(clienteUpdate.getEmail())
                .celular(clienteUpdate.getCelular())
                .tipoCliente(clienteUpdate.getTipoCliente().getIdTipo())
                .build();

    }

    @DeleteMapping("/clientes/{id}")
    public void delete(Integer id){
        Cliente clienteDelete = clienteService.findById(id);
        clienteService.delete(clienteDelete);
    }

    @GetMapping("clientes/{id}")
    public ClienteGetDTO showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        return ClienteGetDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNomCompleto())
                .nroDocumento(cliente.getNroDocumento())
                .email(cliente.getEmail())
                .celular(cliente.getCelular())
                .tipoCliente(cliente.getTipoCliente().getIdTipo())
                .descripcion(cliente.getTipoCliente().getDescripcion())
                .build();
    }

}
