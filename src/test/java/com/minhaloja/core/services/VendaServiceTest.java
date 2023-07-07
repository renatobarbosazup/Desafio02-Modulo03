package com.minhaloja.core.services;

import com.minhaloja.core.models.Cliente;
import com.minhaloja.core.models.Livro;
import com.minhaloja.core.models.OcorrenciaVenda;
import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.ClienteRepository;
import com.minhaloja.core.repositories.ProdutoRepository;
import com.minhaloja.core.repositories.VendaRepository;
import com.minhaloja.core.repositories.VendedorRepository;
import com.minhaloja.infrastructure.repositories.ClienteInMemoryRepository;
import com.minhaloja.infrastructure.repositories.ProdutoInMemoryRepository;
import com.minhaloja.infrastructure.repositories.VendaInMemoryRepository;
import com.minhaloja.infrastructure.repositories.VendedorInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VendaServiceTest {

    private VendaService vendaService;
    private VendedorService vendedorService;
    private ClienteService clienteService;
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        VendedorRepository vendedorRepository = new VendedorInMemoryRepository();
        ClienteRepository clienteRepository = new ClienteInMemoryRepository();
        ProdutoRepository produtoRepository = new ProdutoInMemoryRepository();
        VendaRepository vendaRepository = new VendaInMemoryRepository();

        vendedorService = new VendedorService(vendedorRepository);
        clienteService = new ClienteService(clienteRepository);
        produtoService = new ProdutoService(produtoRepository);

        vendaService = new VendaService(vendaRepository, vendedorService, clienteService, produtoService);
    }

    @Test
    void cadastrarVendaLivro() {
        clienteService.cadastrarOuAtualizar(new Cliente("1", "Cliente 1"));
        clienteService.cadastrarOuAtualizar(new Cliente("2", "Cliente 2"));

        vendedorService.cadastrarOuAtualizar(new Vendedor("3", "Vendedor 1"));
        vendedorService.cadastrarOuAtualizar(new Vendedor("4", "Vendedor 2"));

        produtoService.cadastrarOuAtualizar(new Livro(1L, "Livro 1", BigDecimal.valueOf(100)));
        produtoService.cadastrarOuAtualizar(new Livro(2L, "Livro 2", BigDecimal.valueOf(200)));
        produtoService.cadastrarOuAtualizar(new Livro(3L, "Livro 3", BigDecimal.valueOf(300)));

        OcorrenciaVenda novaVenda =
            vendaService.cadastrarVendaLivro("1", "3", 2L, BigDecimal.valueOf(150));

        assertNotNull(novaVenda, "Uma nova venda deveria ser gerada");

        OcorrenciaVenda vendaGravada =
            vendaService.obterPorCodigo(novaVenda.getCodigo());

        assertNotNull(vendaGravada, "A venda deveria ser encontrada");

        Cliente cliente = clienteService.obterPorCpf("1");
        Vendedor vendedor = vendedorService.obterPorCpf("3");

        assertEquals(1, cliente.getComprasRealizadas().size());
        assertEquals(1, vendedor.getVendasRealizadas().size());
        assertTrue(cliente.getComprasRealizadas().containsAll(vendedor.getVendasRealizadas()));
        assertEquals(vendaGravada, vendedor.getVendasRealizadas().iterator().next());
        assertEquals(vendaGravada, cliente.getComprasRealizadas().iterator().next());
    }

    @Test
    void cadastrarVendaLivro_paraVendedorNaoCadastrao_deveOcorrerErro() {
        clienteService.cadastrarOuAtualizar(new Cliente("1", "Cliente 1"));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                vendaService.cadastrarVendaLivro("1", "3", 2L, BigDecimal.valueOf(150)));

        assertEquals("CPF: 3 do vendedor não foi encontrado.", exception.getMessage());

    }

    @Test
    void cadastrarVendaLivro_paraClienteNaoCadastrao_deveOcorrerErro() {
        vendedorService.cadastrarOuAtualizar(new Vendedor("3", "Vendedor 1"));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                vendaService.cadastrarVendaLivro("1", "3", 2L, BigDecimal.valueOf(150)));

        assertEquals("CPF: 1 do cliente não foi encontrado.", exception.getMessage());
    }

    @Test
    void obterTodasAsVendas() {
        clienteService.cadastrarOuAtualizar(new Cliente("1", "Cliente 1"));
        clienteService.cadastrarOuAtualizar(new Cliente("2", "Cliente 2"));

        vendedorService.cadastrarOuAtualizar(new Vendedor("3", "Vendedor 1"));
        vendedorService.cadastrarOuAtualizar(new Vendedor("4", "Vendedor 2"));

        produtoService.cadastrarOuAtualizar(new Livro(1L, "Livro 1", BigDecimal.valueOf(100)));
        produtoService.cadastrarOuAtualizar(new Livro(2L, "Livro 2", BigDecimal.valueOf(200)));
        produtoService.cadastrarOuAtualizar(new Livro(3L, "Livro 3", BigDecimal.valueOf(300)));

        vendaService.cadastrarVendaLivro("1", "3", 2L, BigDecimal.valueOf(150));
        vendaService.cadastrarVendaLivro("2", "3", 1L, BigDecimal.valueOf(150));
        vendaService.cadastrarVendaLivro("1", "4", 2L, BigDecimal.valueOf(200));

        List<OcorrenciaVenda> todasAsVendas = vendaService.obterTodas();

        assertEquals(3, todasAsVendas.size(), "Não é a quantidade esperada");
    }
}