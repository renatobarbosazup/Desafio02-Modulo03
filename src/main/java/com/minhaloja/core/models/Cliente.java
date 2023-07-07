package com.minhaloja.core.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente implements PessoaFisica {

    private String cpf;
    private String nome;
    private Set<OcorrenciaVenda> comprasRealizadas;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.comprasRealizadas = new HashSet<>();
    }

    @Override
    public void setCPF(String cpfSemFormatacao) {
        this.cpf = cpf;
    }

    @Override
    public String getCPF() {
        return cpf;
    }

    @Override
    public String getEmail() {
        return nome;
    }

    @Override
    public void setEmail(String nome) {
        this.nome = nome;
    }

    public void addCompra(OcorrenciaVenda ocorrencia){
        this.comprasRealizadas.add(ocorrencia);
    }

    public Set<OcorrenciaVenda> getComprasRealizadas() {
        return Collections.unmodifiableSet(comprasRealizadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
