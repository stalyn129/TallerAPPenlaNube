package com.services;
import java.util.List;

import com.entities.Producto;
import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto);
    List<Producto> listarProductos();
    Producto obtenerPorId(Integer id);
    Producto actualizarProducto(Producto producto);
    void eliminarProducto(Integer id);
}
