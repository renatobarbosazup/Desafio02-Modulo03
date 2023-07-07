package com.minhaloja.infrastructure.repositories;

import com.minhaloja.core.models.OcorrenciaVenda;
import com.minhaloja.core.models.Produto;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.VendaRepository;
import com.minhaloja.core.repositories.VendedorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VendaInMemoryRepository implements VendaRepository {
    private List<OcorrenciaVenda> dataBase;
    private static Long GERADOR_CODIGO = 0L;

    public VendaInMemoryRepository(){
        this.dataBase = new ArrayList<>();
    }

    @Override
    public OcorrenciaVenda gravar(OcorrenciaVenda venda) {
        OcorrenciaVenda vendaComCodigo = new OcorrenciaVenda(GERADOR_CODIGO++, venda);
        dataBase.add(vendaComCodigo);
        return vendaComCodigo;
    }

    @Override
    public List<OcorrenciaVenda> buscarTodas() {
        return Collections.unmodifiableList(dataBase);
    }

    @Override
    public Optional<OcorrenciaVenda> buscarPorCodigo(Long codigo) {
        return dataBase.stream().filter(v -> v.getCodigo().equals(codigo) ).findFirst();
    }
}
