package com.lucasengcomp.moneyapi.reposiory.lancamento;

import com.lucasengcomp.moneyapi.model.Lancamento;
import com.lucasengcomp.moneyapi.reposiory.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

   Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
