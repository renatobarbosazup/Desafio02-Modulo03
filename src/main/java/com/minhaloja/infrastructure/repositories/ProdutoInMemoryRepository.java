package com.minhaloja.infrastructure.repositories;

import com.minhaloja.core.models.Produto;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProdutoInMemoryRepository implements ProdutoRepository {
    private List<Produto> dataBase;

    public ProdutoInMemoryRepository(){
        this.dataBase = new ArrayList<>();
    }

    @Override
    public Produto gravarOuAtualizar(Produto entidade) {
        if(dataBase.contains(entidade))
            dataBase.set(dataBase.indexOf(entidade), entidade);
        else
            dataBase.add(entidade);
        return entidade;
    }

    @Override
    public List<Produto> buscarTodos() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public Optional<Produto> buscarPorCodigo(Long codigo) {
        return dataBase.stream().filter(v -> v.getCodigo().equals(codigo) ).findFirst();
    }
}
