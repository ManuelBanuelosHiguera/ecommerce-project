package com.ecommerce.springecommerce.repository;

import com.ecommerce.springecommerce.model.DetalleOrden;
import com.ecommerce.springecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}
