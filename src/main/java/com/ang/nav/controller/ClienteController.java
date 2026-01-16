package com.ang.nav.controller;

import com.ang.nav.model.dto.ClienteDTO;
import com.ang.nav.model.dto.ClienteGetDTO;
import com.ang.nav.model.entity.Cliente;
import com.ang.nav.services.ICliente;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private ICliente clienteService;

    @GetMapping
    @Operation(summary = "Busqueda por Filtro", description = "Filtra a los cliente por los campos de nombre , documento y tipo de cliente")
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
    @Operation(summary = "Crear Cliente", description = "Permite la creación de nuevos clientes.")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSave = clienteService.save(clienteDTO);

        ClienteDTO response = ClienteDTO.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombreCompleto())
                .nroDocumento(clienteSave.getNroDocumento())
                .email(clienteSave.getEmail())
                .celular(clienteSave.getCelular())
                .tipoCliente(clienteSave.getTipoCliente().getIdTipo())
                .build();

        return ResponseEntity.ok(response);
    }


    @PutMapping
    @Operation(summary = "Actualizar Cliente",description = "Permite modificar los datos de los clientes.")
    public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteUpdate =  clienteService.update(clienteDTO);

        ClienteDTO response = ClienteDTO.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombreCompleto())
                .nroDocumento(clienteUpdate.getNroDocumento())
                .email(clienteUpdate.getEmail())
                .celular(clienteUpdate.getCelular())
                .tipoCliente(clienteUpdate.getTipoCliente().getIdTipo())
                .build();
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cliente", description = "Permite eliminar un cliente mediante su Id.")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Cliente clienteDelete = clienteService.findById(id);
        clienteService.delete(clienteDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente", description = "Permite buscar a los clientes por medio de su ID.")
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

    @PatchMapping("/{id}/{idTipo}")
    @Operation(summary = "Editar el Tipo de cliente", description = "Permite cambiar el tipo de cliente de los clientes.")
    public ResponseEntity<Cliente>actualizarTipoCliente(
            @PathVariable Integer id,
            @PathVariable Integer idTipo
    ){
        Cliente clienteActualizado = clienteService.actualizarTipoCliente(id, idTipo);
        return ResponseEntity.ok(clienteActualizado);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<ClienteDTO>> reporteCliente(){
        return ResponseEntity.ok(clienteService.obtenerClientesFiltrados());
    }
}
