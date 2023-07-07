package com.minhaloja.core.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vendedor implements PessoaFisica {

    private String cpf;
    private String nome;
    private Set<OcorrenciaVenda> vendasRealizadas;

    public Vendedor(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.vendasRealizadas = new HashSet<>();
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

    public void addVenda(OcorrenciaVenda ocorrencia){
        this.vendasRealizadas.add(ocorrencia);
    }

    public Set<OcorrenciaVenda> getVendasRealizadas() {
        return Collections.unmodifiableSet(vendasRealizadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(cpf, vendedor.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
