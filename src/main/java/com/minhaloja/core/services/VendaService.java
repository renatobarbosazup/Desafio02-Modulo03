package com.minhaloja.core.services;

import com.minhaloja.core.models.Cliente;
import com.minhaloja.core.models.OcorrenciaVenda;
import com.minhaloja.core.models.Produto;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.VendaRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class VendaService {

    private VendedorService vendedorService;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository,
                        VendedorService vendedorService,
                        ClienteService clienteService,
                        ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.vendedorService = vendedorService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public OcorrenciaVenda cadastrarVendaLivro(String cpfCliente, String cpfVendedor, Long codigoLivro, BigDecimal valorVenda){
        Cliente cliente = clienteService.obterPorCpf(cpfCliente);
        Vendedor vendedor = vendedorService.obterPorCpf(cpfVendedor);
        Produto produto = produtoService.obterPorCodigo(codigoLivro);
        OcorrenciaVenda venda = new OcorrenciaVenda(cliente, vendedor, produto, valorVenda);
        OcorrenciaVenda vendaRegistrada = vendaRepository.gravar(venda);

        cliente.addCompra(vendaRegistrada);
        vendedor.addVenda(vendaRegistrada);
        vendedorService.cadastrarOuAtualizar(vendaRegistrada.getVendedor());
        clienteService.cadastrarOuAtualizar(vendaRegistrada.getCliente());

        return vendaRegistrada;
    }

    public OcorrenciaVenda obterPorCodigo(Long codigo){
        return vendaRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("Código: "+codigo+" da venda não foi encontrado."));
    }

    public List<OcorrenciaVenda> obterTodas(){
        return Collections.unmodifiableList(vendaRepository.buscarTodas());
    }
}
