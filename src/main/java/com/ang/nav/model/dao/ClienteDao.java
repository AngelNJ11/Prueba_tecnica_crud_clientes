package com.ang.nav.model.dao;

import com.ang.nav.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente,Integer> {

    @Query("""
    SELECT c FROM Cliente c
    WHERE (:nombre IS NULL OR LOWER(c.nomCompleto) LIKE LOWER(CONCAT('%', :nombre, '%')))
    AND (:nroDocumento IS NULL OR c.nroDocumento = :nroDocumento)
    """)
    List<Cliente> buscarClientes(
            @Param("nombre") String nombre,
            @Param("nroDocumento") String nroDocumento
    );

}
