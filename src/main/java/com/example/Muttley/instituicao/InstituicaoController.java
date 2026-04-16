package com.example.Muttley.instituicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService service;

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("instituicao", new Instituicao());
        return "instituicao/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Instituicao instituicao, RedirectAttributes attr) {
        service.salvar(instituicao);
        attr.addFlashAttribute("message", "Instituição salva com sucesso!");
        return "redirect:/instituicao/perfil"; 
    }

    @GetMapping("/perfil")
    public String exibirPerfil(Model model) {
        // Por enquanto, listamos para teste, depois filtramos por ID da sessão
        model.addAttribute("listaInstituicoes", service.listarTodos());
        return "instituicao/card-perfil";
    }
}
