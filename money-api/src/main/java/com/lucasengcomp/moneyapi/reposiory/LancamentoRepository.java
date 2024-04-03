package com.lucasengcomp.moneyapi.reposiory;

import com.lucasengcomp.moneyapi.model.Lancamento;
import com.lucasengcomp.moneyapi.reposiory.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
