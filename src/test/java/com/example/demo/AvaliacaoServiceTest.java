package com.example.demo;

import com.example.demo.doMain.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarTodas() {
        Avaliacao avaliacao1 = new Avaliacao();
        Avaliacao avaliacao2 = new Avaliacao();
        List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);

        when(avaliacaoRepository.findAll()).thenReturn(avaliacoes);

        List<Avaliacao> result = avaliacaoService.listarTodas();

        assertEquals(2, result.size());
        verify(avaliacaoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        Long id = 1L;
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);

        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));

        Optional<Avaliacao> result = avaliacaoService.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(avaliacaoRepository, times(1)).findById(id);
    }

    @Test
    void testExcluir() {
        Long id = 1L;

        avaliacaoService.excluir(id);

        verify(avaliacaoRepository, times(1)).deleteById(id);
    }

    @Test
    void testSalvarComImagem() throws IOException {
        Avaliacao avaliacao = new Avaliacao();
        MultipartFile imagem = new MockMultipartFile("imagem", "teste.jpg", "image/jpeg", "conteudo".getBytes());

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);

        Avaliacao result = avaliacaoService.salvar(avaliacao, imagem);

        assertNotNull(result.getImagem());
        assertNotNull(result.getNomeArquivoImagem());
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    @Test
    void testSalvarSemImagem() throws IOException {
        Avaliacao avaliacao = new Avaliacao();

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);

        Avaliacao result = avaliacaoService.salvar(avaliacao, null);

        assertNull(result.getImagem());
        assertNull(result.getNomeArquivoImagem());
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    @Test
    void testSalvarComImagemInvalida() {
        Avaliacao avaliacao = new Avaliacao();
        MultipartFile imagem = new MockMultipartFile("imagem", "teste.txt", "text/plain", "conteudo".getBytes());

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.salvar(avaliacao, imagem));
    }
}