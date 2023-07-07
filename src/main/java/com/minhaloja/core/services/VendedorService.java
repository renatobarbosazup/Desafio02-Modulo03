package com.minhaloja.core.services;

import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.VendedorRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class VendedorService {

    private VendedorRepository repository;

    public VendedorService(VendedorRepository repository){
        this.repository = repository;
    }

    public Vendedor cadastrarOuAtualizar(Vendedor vendedor){
        //pode realizar as validacoes
        return this.repository.gravarOuAtualizar(vendedor);
    }

    public List<Vendedor> obterTodos(){
        return repository.buscarTodos();
    }

    public Vendedor obterPorCpf(String cpf){
        return repository.buscarPorCpf(cpf)
                .orElseThrow(() -> new NoSuchElementException("CPF: "+cpf+" do vendedor não foi encontrado."));
    }
}
