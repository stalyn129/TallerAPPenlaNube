package com.services.impl;

import com.entities.MovimientoStock;
import com.repositories.MovimientoStockRepository;
import com.services.MovimientoStockService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoStockServiceImpl implements MovimientoStockService {

    private final MovimientoStockRepository movimientoRepository;

    public MovimientoStockServiceImpl(MovimientoStockRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public MovimientoStock registrarMovimiento(MovimientoStock movimiento) {
        return movimientoRepository.save(movimiento);
    }
}

