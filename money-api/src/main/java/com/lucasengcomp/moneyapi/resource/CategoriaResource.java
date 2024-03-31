package com.lucasengcomp.moneyapi.resource;

import com.lucasengcomp.moneyapi.model.Categoria;
import com.lucasengcomp.moneyapi.reposiory.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = repository.save(categoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo())
                .toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
        return repository.findById(codigo).get();
    }
}
