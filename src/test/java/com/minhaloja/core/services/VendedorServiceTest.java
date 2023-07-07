package com.minhaloja.core.services;

import com.minhaloja.core.models.Vendedor;
import com.minhaloja.core.repositories.VendedorRepository;
import com.minhaloja.infrastructure.repositories.VendedorInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class VendedorServiceTest {

    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        VendedorRepository vendedorRepository = new VendedorInMemoryRepository();
        vendedorService = new VendedorService(vendedorRepository);
    }

    @Test
    void obterTodosVendedores() {
        Vendedor v1 = new Vendedor("1", "Vendedor 1");
        Vendedor v2 = new Vendedor("2", "Vendedor 2");
        Vendedor v3 = new Vendedor("3", "Vendedor 3");

        vendedorService.cadastrarOuAtualizar(v1);
        vendedorService.cadastrarOuAtualizar(v2);
        vendedorService.cadastrarOuAtualizar(v3);

        List<Vendedor> vendedores = vendedorService.obterTodos();

        assertEquals(3, vendedores.size());
    }

    @Test
    void cadastrar_comDadosValidados_deveGravarComSucesso() {
        Vendedor v1 = new Vendedor("1", "Vendedor 1");
        Vendedor v2 = new Vendedor("2", "Vendedor 2");
        Vendedor v3 = new Vendedor("3", "Vendedor 3");

        vendedorService.cadastrarOuAtualizar(v1);
        vendedorService.cadastrarOuAtualizar(v2);
        vendedorService.cadastrarOuAtualizar(v3);

        Vendedor vendedorSalvo =  vendedorService.obterPorCpf("2");

        assertEquals("Vendedor 2", vendedorSalvo.getEmail());
    }

    @Test
    void obterPorCpf_comDadosValidados_deveGravarComSucesso() {
        Vendedor v1 = new Vendedor("1", "Vendedor 1");
        Vendedor v2 = new Vendedor("2", "Vendedor 2");
        Vendedor v3 = new Vendedor("3", "Vendedor 3");

        vendedorService.cadastrarOuAtualizar(v1);
        vendedorService.cadastrarOuAtualizar(v2);
        vendedorService.cadastrarOuAtualizar(v3);

        Vendedor vendedorPesquisado = vendedorService.obterPorCpf("3");
        assertEquals("Vendedor 3", vendedorPesquisado.getEmail());

        vendedorPesquisado = vendedorService.obterPorCpf("2");
        assertEquals("Vendedor 2", vendedorPesquisado.getEmail());

        vendedorPesquisado = vendedorService.obterPorCpf("1");
        assertEquals("Vendedor 1", vendedorPesquisado.getEmail());
    }

    @Test
    void obterPorCpf_comCpfNaoEncontrado_DeveOcorrerErro() {
        Vendedor v1 = new Vendedor("1", "Vendedor 1");
        Vendedor v2 = new Vendedor("2", "Vendedor 2");
        Vendedor v3 = new Vendedor("3", "Vendedor 3");

        vendedorService.cadastrarOuAtualizar(v1);
        vendedorService.cadastrarOuAtualizar(v2);
        vendedorService.cadastrarOuAtualizar(v3);

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class, () -> vendedorService.obterPorCpf("11111"));

        assertEquals("CPF: 11111 do vendedor não foi encontrado.", exception.getMessage());
    }

}