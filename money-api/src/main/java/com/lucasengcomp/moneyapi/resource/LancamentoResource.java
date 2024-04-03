package com.lucasengcomp.moneyapi.resource;

import com.lucasengcomp.moneyapi.evento.RecursoCriadoEvent;
import com.lucasengcomp.moneyapi.exceptionhandler.ErroMensagemPadrao;
import com.lucasengcomp.moneyapi.model.Lancamento;
import com.lucasengcomp.moneyapi.reposiory.LancamentoRepository;
import com.lucasengcomp.moneyapi.reposiory.filter.LancamentoFilter;
import com.lucasengcomp.moneyapi.service.LancamentoService;
import com.lucasengcomp.moneyapi.service.exceptions.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return repository.filtrar(lancamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamento = repository.findById(codigo).get();
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = service.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        repository.deleteById(codigo);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<ErroMensagemPadrao> erros = Arrays.asList(new ErroMensagemPadrao(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
