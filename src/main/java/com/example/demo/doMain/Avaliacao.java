package com.example.demo.doMain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeViagem;
    private String local;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum avaliacao;

    private String nomeArquivoImagem;
    private String imagem;
    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeViagem() {
        return nomeViagem;
    }

    public void setNomeViagem(String nomeViagem) {
        this.nomeViagem = nomeViagem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public AvaliacaoEnum getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoEnum avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getNomeArquivoImagem() {
        return nomeArquivoImagem;
    }

    public void setNomeArquivoImagem(String nomeArquivoImagem) {
        this.nomeArquivoImagem = nomeArquivoImagem;
    }

    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}