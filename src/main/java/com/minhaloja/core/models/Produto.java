package com.minhaloja.core.models;

import java.math.BigDecimal;

public interface Produto {

    Long getCodigo();
    String getNome();

    OcorrenciaVenda vender(Cliente cliente, Vendedor vendedor, BigDecimal valor);

}
