package com.ang.nav.model.dao;

import com.ang.nav.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente,Integer> {

    @Query("""
    SELECT c FROM Cliente c
    WHERE ( NULLIF(:nombre,'') IS NULL OR c.nombreCompleto ILIKE CONCAT('%', :nombre, '%'))
    AND ( NULLIF(:nroDocumento,'') IS NULL OR c.nroDocumento = :nroDocumento)
    AND (:idTipo IS NULL OR c.tipoCliente.idTipo = :idTipo)
    """)
    List<Cliente> buscarClientes(
            @Param("nombre") String nombre,
            @Param("nroDocumento") String nroDocumento,
            @Param("idTipo") Integer idTipo
    );

}
