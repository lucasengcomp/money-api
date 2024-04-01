package com.lucasengcomp.moneyapi.service;

import com.lucasengcomp.moneyapi.model.Pessoa;
import com.lucasengcomp.moneyapi.reposiory.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaSalva = repository.findById(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return repository.save(pessoaSalva.get());
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        repository.save(pessoaSalva);
    }

    private Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pessoaSalva = repository.findById(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva.get();
    }
}
