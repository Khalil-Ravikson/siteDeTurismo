package com.example.demo.controller;

import com.example.demo.doMain.Avaliacao;
import com.example.demo.doMain.AvaliacaoEnum;
import com.example.demo.service.AvaliacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/avaliacoes")
    public String avaliacoesPage() {
        return "avaliacoes"; // Supondo que você tenha um template chamado avaliacoes.html
    }

    private static final Logger logger = (Logger) LoggerFactory.getLogger(AvaliacaoController.class);
    
    @GetMapping("/cadastrar")
    public String cadastrarForm(Model model) {
        model.addAttribute("avaliacao", new Avaliacao());
        return "cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Avaliacao avaliacao,
                         @RequestParam("imagemFile") MultipartFile imagemFile,
                         @RequestParam("avaliacaoValor") int avaliacaoValor,
                         RedirectAttributes redirectAttributes) {
        try {
            avaliacao.setAvaliacao(AvaliacaoEnum.fromValue(avaliacaoValor));
            avaliacaoService.salvar(avaliacao, imagemFile);
            redirectAttributes.addFlashAttribute("mensagem", "Avaliação salva com sucesso!");
            return "redirect:/listar";
        } catch (IOException e) {
            logger.error("Erro ao salvar avaliação", e);
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar avaliação: " + e.getMessage());
            return "redirect:/cadastrar";
        }
    }



    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("avaliacoes", avaliacaoService.listarTodas());

        return "listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("avaliacao", avaliacaoService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Avaliação inválida: " + id)));
        return "cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        avaliacaoService.excluir(id);
        return "redirect:/listar";
    }
}