package com.minhaloja.core.services;

import com.minhaloja.core.models.Cliente;
import com.minhaloja.core.repositories.ClienteRepository;

import java.util.NoSuchElementException;

public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public Cliente cadastrarOuAtualizar(Cliente cliente){
        //pode realizar as validacoes
        return this.repository.gravarOuAtualizar(cliente);
    }

    public Cliente obterPorCpf(String cpf){
        return repository.buscarPorCpf(cpf)
                .orElseThrow(() -> new NoSuchElementException("CPF: "+cpf+" do cliente não foi encontrado."));
    }
}
