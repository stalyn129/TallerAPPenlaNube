package com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {}
