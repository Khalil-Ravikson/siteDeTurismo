package com.example.demo;

import com.example.demo.doMain.Avaliacao;
import com.example.demo.doMain.AvaliacaoEnum;
import com.example.demo.service.AvaliacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AvaliacaoIntegrationTest {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Test
    void testCrudOperations() throws IOException {
        // Criar
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNomeViagem("Teste Viagem");
        avaliacao.setLocal("Teste Local");
        avaliacao.setData(LocalDate.now());
        avaliacao.setAvaliacao(AvaliacaoEnum.QUATRO);

        MockMultipartFile imagemFile = new MockMultipartFile("imagem", "teste.jpg", "image/jpeg", "conteudo".getBytes());

        Avaliacao savedAvaliacao = avaliacaoService.salvar(avaliacao, imagemFile);
        assertNotNull(savedAvaliacao.getId());
        assertNotNull(savedAvaliacao.getImagem());
        assertNotNull(savedAvaliacao.getNomeArquivoImagem());

        // Ler
        Optional<Avaliacao> retrievedAvaliacao = avaliacaoService.buscarPorId(savedAvaliacao.getId());
        assertTrue(retrievedAvaliacao.isPresent());
        assertEquals("Teste Viagem", retrievedAvaliacao.get().getNomeViagem());

        // Atualizar
        retrievedAvaliacao.get().setNomeViagem("Teste Viagem Atualizada");
        Avaliacao updatedAvaliacao = avaliacaoService.salvar(retrievedAvaliacao.get(), null);
        assertEquals("Teste Viagem Atualizada", updatedAvaliacao.getNomeViagem());

        // Listar todos
        List<Avaliacao> avaliacoes = avaliacaoService.listarTodas();
        assertFalse(avaliacoes.isEmpty());

        // Excluir
        avaliacaoService.excluir(savedAvaliacao.getId());
        Optional<Avaliacao> deletedAvaliacao = avaliacaoService.buscarPorId(savedAvaliacao.getId());
        assertFalse(deletedAvaliacao.isPresent());
    }
}