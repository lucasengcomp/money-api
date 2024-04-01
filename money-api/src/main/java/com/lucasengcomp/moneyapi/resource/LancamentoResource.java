package com.lucasengcomp.moneyapi.resource;

import com.lucasengcomp.moneyapi.evento.RecursoCriadoEvent;
import com.lucasengcomp.moneyapi.model.Lancamento;
import com.lucasengcomp.moneyapi.reposiory.LancamentoRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;


    @GetMapping
    public List<Lancamento> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento Lancamento, HttpServletResponse response) {
        Lancamento lancamentoEncontrado = repository.save(Lancamento);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, Lancamento.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoEncontrado);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        return this.repository.findById(codigo)
                .map(pessoa -> ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }
}
