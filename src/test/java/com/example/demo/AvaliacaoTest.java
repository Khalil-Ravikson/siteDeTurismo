package com.example.demo;

import com.example.demo.doMain.Avaliacao;
import com.example.demo.doMain.AvaliacaoEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class AvaliacaoTest {

    @Test
    void testAvaliacaoGettersAndSetters() {
        Avaliacao avaliacao = new Avaliacao();

        Long id = 1L;
        String nomeViagem = "Viagem para Paris";
        String local = "Paris";
        LocalDate data = LocalDate.now();
        AvaliacaoEnum avaliacaoEnum = AvaliacaoEnum.QUATRO; // Assumindo que existe um enum AvaliacaoEnum
        String nomeArquivoImagem = "paris.jpg";
        String imagem = "base64EncodedImage";

        avaliacao.setId(id);
        avaliacao.setNomeViagem(nomeViagem);
        avaliacao.setLocal(local);
        avaliacao.setData(data);
        avaliacao.setAvaliacao(avaliacaoEnum);
        avaliacao.setNomeArquivoImagem(nomeArquivoImagem);
        avaliacao.setImagem(imagem);

        assertEquals(id, avaliacao.getId());
        assertEquals(nomeViagem, avaliacao.getNomeViagem());
        assertEquals(local, avaliacao.getLocal());
        assertEquals(data, avaliacao.getData());
        assertEquals(avaliacaoEnum, avaliacao.getAvaliacao());
        assertEquals(nomeArquivoImagem, avaliacao.getNomeArquivoImagem());
        assertEquals(imagem, avaliacao.getImagem());
    }
}