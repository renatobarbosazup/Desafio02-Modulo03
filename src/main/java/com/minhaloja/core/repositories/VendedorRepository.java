package com.minhaloja.core.repositories;

import com.minhaloja.core.models.Vendedor;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository {
    Vendedor gravarOuAtualizar(Vendedor vendedor);
    List<Vendedor> buscarTodos();
    Optional<Vendedor> buscarPorCpf(String cpfNaoFormatado);
}
