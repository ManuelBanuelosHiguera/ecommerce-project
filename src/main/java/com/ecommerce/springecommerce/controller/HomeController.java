package com.ecommerce.springecommerce.controller;

import com.ecommerce.springecommerce.repository.ProductoRepository;
import com.ecommerce.springecommerce.service.IProductoService;
import com.ecommerce.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger log= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id){
        log.info("Id enviado como parametro {}", id);
        return "usuario/productohome";
    }

}
