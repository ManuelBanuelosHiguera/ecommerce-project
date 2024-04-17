package com.ecommerce.springecommerce.service;

import com.ecommerce.springecommerce.model.Orden;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.util.List;

@Service
public interface IOrdenService {
    List<Orden> findAll();
    Orden save (Orden orden);
}

