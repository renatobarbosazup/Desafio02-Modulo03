package com.minhaloja.infrastructure.repositories;

import com.minhaloja.core.models.Cliente;
import com.minhaloja.core.models.Produto;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.ClienteRepository;
import com.minhaloja.core.repositories.VendedorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteInMemoryRepository implements ClienteRepository {
    private List<Cliente> dataBase;

    public ClienteInMemoryRepository(){
        this.dataBase = new ArrayList<>();
    }

    @Override
    public Cliente gravarOuAtualizar(Cliente entidade) {
        if(dataBase.contains(entidade))
            dataBase.set(dataBase.indexOf(entidade), entidade);
        else
            dataBase.add(entidade);
        return entidade;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpfNaoFormatado) {
        return dataBase.stream().filter(v -> v.getCPF().equalsIgnoreCase(cpfNaoFormatado) ).findFirst();
    }
}
