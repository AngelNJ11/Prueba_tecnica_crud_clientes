package com.ang.nav.service.impl;

import com.ang.nav.exception.TipoClienteNotFoundException;
import com.ang.nav.repository.TipoClienteRepository;
import com.ang.nav.entity.TipoCliente;
import com.ang.nav.service.TipoClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoClienteServiceImpl implements TipoClienteService {

    private final TipoClienteRepository tipoClienteRepository;

    public TipoClienteServiceImpl(TipoClienteRepository tipoClienteRepository) {
        this.tipoClienteRepository = tipoClienteRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public TipoCliente findById(Integer id) {
        return tipoClienteRepository.findById(id)
                .orElseThrow(() -> new TipoClienteNotFoundException("Tipo de cliente con id " + id + " no existe"));
    }


}
