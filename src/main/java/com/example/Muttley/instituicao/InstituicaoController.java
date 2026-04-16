package com.example.Muttley.instituicao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @Autowired
    private InstituicaoMapper instituicaoMapper;

    @GetMapping
    public String carregaPaginaPerfil(Model model) {
        model.addAttribute("listaInstituicoes", instituicaoService.listarTodos());
        // AJUSTADO: Nome exato do seu arquivo HTML
        return "instituicao/card-perfil";
    }

    @GetMapping("/login")
    public String telaLogin() {
        // Se você ainda não tem login.html, isso vai dar erro 500 se acessar /login
        return "instituicao/login"; 
    }

    @GetMapping("/cadastro-instituicao")
    public String mostrarFormulario(@RequestParam(required = false) Long id, Model model) {
        AtualizacaoInstituicao dto;
        if (id != null) {
            Instituicao inst = instituicaoService.buscarPorId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Instituição não encontrada"));
            dto = instituicaoMapper.toAtualizacaoDto(inst);
        } else {
            dto = new AtualizacaoInstituicao(null, "", "", "", "", "");
        }
        model.addAttribute("instituicao", dto);
        // AJUSTADO: Nome exato do seu arquivo HTML
        return "instituicao/cadastro-instituicao";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("instituicao") @Valid AtualizacaoInstituicao dto, 
                        BindingResult result,
                        RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            // AJUSTADO: Nome exato do seu arquivo HTML
            return "instituicao/cadastro-instituicao";
        }

        try {
            instituicaoService.salvarOuAtualizar(dto);
            String mensagem = (dto.id() != null) ? "Editado com Sucesso!" : "Cadastrado com sucesso!";
            redirectAttributes.addFlashAttribute("message", mensagem);
            return "redirect:/instituicao"; 

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro: " + e.getMessage());
            return "redirect:/instituicao/cadastro-instituicao";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Instituicao inst = instituicaoService.buscarPorId(id)
            .orElseThrow(() -> new EntityNotFoundException("Instituição não encontrada"));
        
        AtualizacaoInstituicao dto = instituicaoMapper.toAtualizacaoDto(inst);
        
        model.addAttribute("instituicao", dto);
        // AJUSTADO: Nome exato do seu arquivo HTML
        return "instituicao/cadastro-instituicao";
    }
    
    @PostMapping("/login")
    public String logar(@RequestParam String email, @RequestParam String senha, RedirectAttributes attributes) {
        Optional<Instituicao> instLogada = instituicaoService.realizarLogin(email, senha);

        if (instLogada.isPresent()) {
            // Redireciona para /instituicao/perfil/ID_DA_INSTITUICAO
            return "redirect:/instituicao/perfil/" + instLogada.get().getId(); 
        } else {
            attributes.addFlashAttribute("error", "E-mail ou senha inválidos!");
            return "redirect:/instituicao/login";
        }
    }

    // Nova rota para visualizar o perfil específico
    @GetMapping("/perfil/{id}")
    public String exibirPerfil(@PathVariable Long id, Model model) {
        Instituicao inst = instituicaoService.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Instituição não encontrada"));
        
        model.addAttribute("instituicao", inst);
        return "instituicao/perfil-detalhes";
    }
}