package com.minhaloja.core.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Livro implements Produto{

    private Long codigo;
    private String nome;
    private BigDecimal valorCompra;
    private LocalDateTime dataCompra;

    public Livro(Long codigo, String nome, BigDecimal valorCompra) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.dataCompra = LocalDateTime.now();
    }

    @Override
    public Long getCodigo() {
        return codigo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    @Override
    public OcorrenciaVenda vender(Cliente cliente, Vendedor vendedor, BigDecimal valor) {
        return new OcorrenciaVenda(cliente, vendedor, this, valor);
    }
}
