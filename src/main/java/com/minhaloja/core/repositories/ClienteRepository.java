package com.minhaloja.core.repositories;

import com.minhaloja.core.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente gravarOuAtualizar(Cliente cliente);
    List<Cliente> buscarTodos();
    Optional<Cliente> buscarPorCpf(String cpfNaoFormatado);
}
