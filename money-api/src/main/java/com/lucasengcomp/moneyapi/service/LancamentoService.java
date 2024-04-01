package com.lucasengcomp.moneyapi.service;

import com.lucasengcomp.moneyapi.model.Lancamento;
import com.lucasengcomp.moneyapi.model.Pessoa;
import com.lucasengcomp.moneyapi.reposiory.LancamentoRepository;
import com.lucasengcomp.moneyapi.reposiory.PessoaRepository;
import com.lucasengcomp.moneyapi.service.exceptions.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).get();
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return repository.save(lancamento);
    }
}
