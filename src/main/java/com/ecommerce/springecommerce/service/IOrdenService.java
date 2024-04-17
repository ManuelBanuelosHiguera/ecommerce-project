package com.ecommerce.springecommerce.service;

import com.ecommerce.springecommerce.model.Orden;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.OutputDeviceAssigned;

@Service
public interface IOrdenService {
    Orden save (Orden orden);
}

