package com.ang.nav.services.impl;

import com.ang.nav.model.dao.TipoClienteDao;
import com.ang.nav.model.entity.TipoCliente;
import com.ang.nav.services.ITipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoClienteImpl implements ITipoCliente {

    @Autowired
    private TipoClienteDao tipoClienteDao;

    @Transactional(readOnly = true)
    @Override
    public TipoCliente findById(Integer id) {
        return tipoClienteDao.findById(id).orElseThrow(() -> new RuntimeException("Tipo de cliente no existe")).getTipoCliente();
    }


}
