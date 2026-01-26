package com.ang.nav.controller;

import com.ang.nav.dto.ClienteRequestDTO;
import com.ang.nav.dto.ClienteResponseAuditDTO;
import com.ang.nav.dto.ClienteResponseDTO;
import com.ang.nav.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Busqueda por Filtro", description = "Filtra a los cliente por los campos de nombre , documento y tipo de cliente")
    public ResponseEntity<Page<ClienteResponseDTO>> buscarCliente(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String nroDocumento,
            @RequestParam(required = false) Integer idTipo,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                clienteService.buscarClientes(nombre, nroDocumento, idTipo, pageable)
        );
    }

    @PostMapping
    @Operation(summary = "Crear Cliente", description = "Permite la creación de nuevos clientes.")
    public ResponseEntity<ClienteResponseDTO> create(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO crear = clienteService.save(clienteRequestDTO);
        return ResponseEntity.ok(crear);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cliente",description = "Permite modificar los datos de los clientes.")
    public ResponseEntity<ClienteResponseDTO> update(@Valid @PathVariable Integer id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO actualizar = clienteService.update(id, clienteRequestDTO);
        return ResponseEntity.ok(actualizar);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cliente", description = "Permite eliminar un cliente mediante su Id.")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente", description = "Permite buscar a los clientes por medio de su ID.")
    public ResponseEntity<ClienteResponseAuditDTO> showById(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PatchMapping("/{id}/{idTipo}")
    @Operation(summary = "Editar el Tipo de cliente", description = "Permite cambiar el tipo de cliente de los clientes.")
    public ResponseEntity<ClienteResponseDTO> actualizarTipoCliente(
            @PathVariable Integer id,
            @PathVariable Integer idTipo
    ){
        ClienteResponseDTO clienteActualizado = clienteService.actualizarTipoCliente(id, idTipo);
        return ResponseEntity.ok(clienteActualizado);
    }

    @GetMapping("/reporte")
    @Operation(summary = "Lista de cliente", description = "Permite obtener los clientes que empiecen con la letra 'A' y los números que tengan el '+51'.")
    public ResponseEntity<List<ClienteResponseAuditDTO>> reporteCliente(){
        return ResponseEntity.ok(clienteService.obtenerClientesFiltrados());
    }
}
