package com.example.demo.service;

import com.example.demo.doMain.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de uploads", e);
        }
    }

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

//    public Avaliacao salvar(Avaliacao avaliacao, MultipartFile imagem) throws IOException {
//        if (imagem != null && !imagem.isEmpty()) {
//            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
//            Path caminho = Paths.get(UPLOAD_DIRECTORY + nomeArquivo);
//            Files.write(caminho, imagem.getBytes());
//            avaliacao.setImagem(nomeArquivo);
//        }
//        return avaliacaoRepository.save(avaliacao);
//    }

    public Optional<Avaliacao> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public void excluir(Long id) {
        avaliacaoRepository.deleteById(id);
    }


    private boolean isValidImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }

    public Avaliacao salvar(Avaliacao avaliacao, MultipartFile imagem) throws IOException {
        if (imagem != null && !imagem.isEmpty()) {
            if (!isValidImageFile(imagem)) {
                throw new IllegalArgumentException("Apenas imagens JPEG e PNG são permitidas.");
            }
            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
            Path caminho = Paths.get(UPLOAD_DIRECTORY + nomeArquivo);
            Files.write(caminho, imagem.getBytes());
            avaliacao.setImagem(nomeArquivo);
            avaliacao.setNomeArquivoImagem(nomeArquivo);  // Adicionado esta linha
        }
        return avaliacaoRepository.save(avaliacao);
    }




}