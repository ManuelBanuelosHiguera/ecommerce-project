package com.ecommerce.springecommerce.controller;

import com.ecommerce.springecommerce.model.Producto;
import com.ecommerce.springecommerce.model.Usuario;
import com.ecommerce.springecommerce.service.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "/productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(u);

        //imagen
        if (producto.getId()==null){ //si creamos un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{

        }

        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.get(id);
        producto=optionalProducto.get();

        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);
        return "/productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam ("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();

        if (file.isEmpty()){//editar el producto pero no cambiar imagen

            producto.setImagen(p.getImagen());
        }else{//si editamos la imagen tambien

            if (!p.getImagen().equals("default.jpg")){ //eliminar cuando no sea la imagen por defecto
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable Integer id){
        Producto p = new Producto();
        p=productoService.get(id).get();

        if (!p.getImagen().equals("default.jpg")){ //eliminar cuando no sea la imagen por defecto
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }
}
