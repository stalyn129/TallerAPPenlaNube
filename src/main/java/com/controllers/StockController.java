package com.controllers;

import com.entities.MovimientoStock;
import com.entities.Producto;
import com.services.FirestoreService;
import com.services.MovimientoStockService;
import com.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final ProductoService productoService;
    private final MovimientoStockService movimientoService;
    private final FirestoreService firestoreService;

    @Autowired
    public StockController(
            ProductoService productoService,
            MovimientoStockService movimientoService,
            FirestoreService firestoreService
    ) {
        this.productoService = productoService;
        this.movimientoService = movimientoService;
        this.firestoreService = firestoreService;
    }

    @PostMapping("/salida/{id}")
    public String salida(@PathVariable Integer id, @RequestParam Integer cantidad) {

        Producto p = productoService.obtenerPorId(id);

        p.setStockActual(p.getStockActual() - cantidad);
        productoService.actualizarProducto(p);

        MovimientoStock mov = new MovimientoStock(null, p, "salida", cantidad, LocalDateTime.now());
        movimientoService.registrarMovimiento(mov);

        firestoreService.guardarLog("Salida de " + cantidad + " unidades del producto " + id, "admin");

        if (p.getStockActual() <= 5) {
            firestoreService.guardarAlerta(p.getNombre(), p.getStockActual());
        }

        return "redirect:/productos";
    }
}

