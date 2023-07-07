package com.minhaloja.core.services;

import com.minhaloja.core.models.Produto;
import com.minhaloja.core.repositories.ProdutoRepository;

import java.util.NoSuchElementException;

public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public Produto cadastrarOuAtualizar(Produto vendedor){
        //pode realizar as validacoes
        return this.repository.gravarOuAtualizar(vendedor);
    }

    public Produto obterPorCodigo(Long codigo){
        return repository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("Código: "+codigo+" do produto não foi encontrado."));
    }
}
