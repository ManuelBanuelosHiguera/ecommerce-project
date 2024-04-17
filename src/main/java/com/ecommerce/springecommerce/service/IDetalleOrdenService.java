package com.ecommerce.springecommerce.service;

import com.ecommerce.springecommerce.model.DetalleOrden;
import org.springframework.stereotype.Service;

@Service
public interface IDetalleOrdenService {

    DetalleOrden save(DetalleOrden detalleOrden);

}
