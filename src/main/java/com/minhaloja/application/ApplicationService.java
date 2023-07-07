package com.minhaloja.application;

import com.minhaloja.core.models.Cliente;
import com.minhaloja.core.models.Livro;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.services.ClienteService;
import com.minhaloja.core.services.ProdutoService;
import com.minhaloja.core.services.VendaService;
import com.minhaloja.core.services.VendedorService;

import java.math.BigDecimal;

public class ApplicationService {

    private VendedorService vendedorService;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private VendaService vendaService;

    public ApplicationService(VendedorService vendedorService, ClienteService clienteService, ProdutoService produtoService, VendaService vendaService) {
        this.vendedorService = vendedorService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.vendaService = vendaService;
    }

    public void cadastrarCliente(String cpf, String email){
        clienteService.cadastrarOuAtualizar(new Cliente(cpf, email));
    }
    public void cadastrarVendedor(String cpf, String email){
        vendedorService.cadastrarOuAtualizar(new Vendedor(cpf, email));
    }

    public void cadastrarLivro(Long codigo, String nome, BigDecimal valorCompra){
        produtoService.cadastrarOuAtualizar(new Livro(codigo, nome, valorCompra));
    }

    public void cadastrarVendaLivro(String cpfCliente, String cpfVendedor, Long codigoLivro, BigDecimal valorVenda){
        vendaService.cadastrarVendaLivro(cpfCliente, cpfVendedor, codigoLivro, valorVenda);
    }

}
