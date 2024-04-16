package com.ecommerce.springecommerce.service;

import com.ecommerce.springecommerce.model.Usuario;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public interface IUsuarioService {
    Optional<Usuario> findById(Integer id);

}
