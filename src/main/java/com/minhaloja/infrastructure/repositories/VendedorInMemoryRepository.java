package com.minhaloja.infrastructure.repositories;

import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.VendedorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VendedorInMemoryRepository implements VendedorRepository {
    private List<Vendedor> dataBase;

    public VendedorInMemoryRepository(){
        this.dataBase = new ArrayList<>();
    }

    @Override
    public Vendedor gravarOuAtualizar(Vendedor entidade) {
        if(dataBase.contains(entidade))
            dataBase.set(dataBase.indexOf(entidade), entidade);
        else
            dataBase.add(entidade);
        return entidade;
    }

    @Override
    public List<Vendedor> buscarTodos() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public Optional<Vendedor> buscarPorCpf(String cpfNaoFormatado) {
        return dataBase.stream().filter(v -> v.getCPF().equalsIgnoreCase(cpfNaoFormatado) ).findFirst();
    }
}
