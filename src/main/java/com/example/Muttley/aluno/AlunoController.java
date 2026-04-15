package com.example.Muttley.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Importante: verifique se você tem um CursoService ou remova se não for usar
// import com.example.Muttley.curso.CursoService; 

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
    private AlunoMapper alunoMapper;
	
	// @Autowired
	// private CursoService cursoService; // Antigo MarcaService
	
	@GetMapping                  
	public String carregaPaginaFormulario (Model model){ 
		// Adiciona a lista de alunos ao model para a página de listagem
		model.addAttribute("listaAlunos", alunoService.procurarTodos());
        return "aluno/listagem";              
	} 
	
	@GetMapping("/login")
	public String telaLogin() {
	    return "aluno/login"; // Procura o arquivo login.html na pasta templates/aluno/
	}

	@GetMapping("/cadastro-aluno")
	public String mostrarFormulario(@RequestParam(required = false) Long id, Model model) {
	    AtualizacaoAluno dto;
	    if (id != null) {
	        Aluno aluno = alunoService.procurarPorId(id)
	            .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
	        dto = alunoMapper.toAtualizacaoDto(aluno);
	    } else {
	        // AJUSTE AQUI: Trocamos os 0 (ints) por "" (Strings)
	        dto = new AtualizacaoAluno(null, "", "", ""); 
	    }
	    model.addAttribute("aluno", dto);
	    return "aluno/cadastro";
	}
	
	@GetMapping ("/formulario/{id}")    
	public String carregaPaginaFormulario (@PathVariable("id") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		AtualizacaoAluno dto;
		try {
			if(id != null) {
				Aluno aluno = alunoService.procurarPorId(id)
						.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
				// model.addAttribute("cursos", cursoService.procurarTodos());
				
				// Mapear aluno para AtualizacaoAluno
				dto = alunoMapper.toAtualizacaoDto(aluno);
				model.addAttribute("aluno", dto);
			}
			return "aluno/formulario";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/aluno";
		}
	}

	@PostMapping("/salvar")
    public String salvar(@ModelAttribute("aluno") @Valid AtualizacaoAluno dto,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model model) {
		if (result.hasErrors()) {
            // Se houver erro de validação, recarrega o formulário
            // model.addAttribute("cursos", cursoService.procurarTodos());
            return "aluno/formulario";
        }
        try {
            Aluno alunoSalvo = alunoService.salvarOuAtualizar(dto);
            String mensagem = dto.id() != null 
                ? "Aluno ID '" + alunoSalvo.getId() + "' atualizado com sucesso!"
                : "Aluno ID '" + alunoSalvo.getId() + "' criado com sucesso!";
            
            redirectAttributes.addFlashAttribute("message", mensagem);
            return "redirect:/aluno";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/aluno/formulario" + (dto.id() != null ? "?id=" + dto.id() : "");
        }
	}
	
	@GetMapping("/delete/{id}")
	@Transactional
	public String deletarAluno(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			alunoService.apagarPorId(id);
			redirectAttributes.addFlashAttribute("message", "O aluno " + id + " foi apagado!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/aluno";
	}
}