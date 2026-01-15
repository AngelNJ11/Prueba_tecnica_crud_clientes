package com.ang.nav.model.dao;

import com.ang.nav.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteDao extends JpaRepository<Cliente, Integer> {

}
