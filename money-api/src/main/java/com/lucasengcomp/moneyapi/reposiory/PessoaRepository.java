package com.lucasengcomp.moneyapi.reposiory;

import com.lucasengcomp.moneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
