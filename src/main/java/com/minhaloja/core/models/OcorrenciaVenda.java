package com.minhaloja.core.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OcorrenciaVenda {

    private Long codigo;
    private Cliente cliente;
    private Vendedor vendedor;
    private Produto produto;
    private LocalDateTime dataVenda;
    private BigDecimal valorVenda;

    public OcorrenciaVenda(Long codigo, OcorrenciaVenda venda){
        this.codigo = codigo;
        this.cliente = venda.cliente;
        this.vendedor = venda.vendedor;
        this.produto = venda.produto;
        this.valorVenda = venda.valorVenda;
        this.dataVenda = venda.dataVenda;
    }

    public OcorrenciaVenda(Cliente cliente, Vendedor vendedor, Produto produto, BigDecimal valorVenda) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produto = produto;
        this.valorVenda = valorVenda;
        this.dataVenda = LocalDateTime.now();
    }

    public Long getCodigo() {
        return codigo;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcorrenciaVenda that = (OcorrenciaVenda) o;
        return Objects.equals(cliente, that.cliente) &&
                Objects.equals(vendedor, that.vendedor) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(dataVenda, that.dataVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vendedor, produto, dataVenda);
    }
}
