package com.minhaloja.core.repositories;

import com.minhaloja.core.models.OcorrenciaVenda;
import com.minhaloja.core.models.Produto;

import java.util.List;
import java.util.Optional;

public interface VendaRepository {
    OcorrenciaVenda gravar(OcorrenciaVenda venda);
    List<OcorrenciaVenda> buscarTodas();

    Optional<OcorrenciaVenda> buscarPorCodigo(Long codigo);
}
