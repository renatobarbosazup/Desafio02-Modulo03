package com.minhaloja.core.repositories;

import com.minhaloja.core.models.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Produto gravarOuAtualizar(Produto produto);
    List<Produto> buscarTodos();
    Optional<Produto> buscarPorCodigo(Long codigo);
}
