//package com.example.Muttley.Palestrante;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/palestrante")
//public class PalestranteController {
//
//	@Autowired
//	private PalestranteService service;
//
//	@Autowired
//	private PalestranteMapper mapper;
//
//
//	// LISTAGEM
//	@GetMapping
//	public String listar(Model model) {
//		model.addAttribute("listaPalestrantes", service.procurarTodos());
//		return "palestrante/listagem";
//	}
//
//	// FORMULÁRIO
//	@GetMapping("/formulario")
//	public String formulario(@RequestParam(required = false) Long id, Model model) {
//
//    // LISTAGEM (igual ao aluno)
//    @GetMapping
//    public String carregaPaginaFormulario(Model model) {
//        model.addAttribute("listaPalestrantes", service.procurarTodos());
//        return "palestrante/listagem";
//    }
//
//    // FORMULÁRIO (igual ao cadastro-aluno)
//    @GetMapping("/cadastro-palestrante")
//    public String mostrarFormulario(@RequestParam(required = false) Long id, Model model) {
//
//
//		AtualizacaoPalestrante dto;
//
//
//		if (id != null) {
//			Palestrante p = service.procurarPorId(id)
//					.orElseThrow(() -> new EntityNotFoundException("Palestrante não encontrado"));
//
//        if (id != null) {
//            Palestrante p = service.procurarPorId(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Palestrante não encontrado"));
//
//
//			dto = mapper.toDTO(p);
//		} else {
//			dto = new AtualizacaoPalestrante(null, "", "", "");
//		}
//
//
//		model.addAttribute("palestrante", dto);
//		return "palestrante/formulario";
//	}
//
//	// SALVAR
//	@PostMapping("/salvar")
//	public String salvar(@ModelAttribute("palestrante") @Valid AtualizacaoPalestrante dto, BindingResult result) {
//
//        model.addAttribute("palestrante", dto);
//        return "palestrante/cadastro";
//    }
//
//    // FORMULÁRIO POR ID (igual ao aluno/formulario/{id})
//    @GetMapping("/formulario/{id}")
//    public String carregarFormularioPorId(@PathVariable("id") Long id,
//                                          Model model,
//                                          RedirectAttributes redirectAttributes) {
//
//        try {
//            Palestrante p = service.procurarPorId(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Palestrante não encontrado"));
//
//            AtualizacaoPalestrante dto = mapper.toDTO(p);
//            model.addAttribute("palestrante", dto);
//
//            return "palestrante/formulario";
//
//        } catch (EntityNotFoundException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/palestrante";
//        }
//    }
//
//    // SALVAR (igual ao aluno)
//    @PostMapping("/salvar")
//    public String salvar(@ModelAttribute("palestrante") @Valid AtualizacaoPalestrante dto,
//                         BindingResult result,
//                         RedirectAttributes redirectAttributes,
//                         Model model) {
//
//
//		if (result.hasErrors()) {
//			return "palestrante/formulario";
//		}
//
//
//		service.salvarOuAtualizar(dto);
//		return "redirect:/palestrante";
//	}
//
//	// DELETE
//	@GetMapping("/delete/{id}")
//	public String deletar(@PathVariable Long id) {
//		service.apagarPorId(id);
//		return "redirect:/palestrante";
//	}
//
//        try {
//            Palestrante salvo = service.salvarOuAtualizar(dto);
//
//            String mensagem = dto.id() != null
//                    ? "Palestrante ID '" + salvo.getId() + "' atualizado com sucesso!"
//                    : "Palestrante ID '" + salvo.getId() + "' criado com sucesso!";
//
//            redirectAttributes.addFlashAttribute("message", mensagem);
//
//            return "redirect:/palestrante";
//
//        } catch (EntityNotFoundException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//
//            return "redirect:/palestrante/formulario" +
//                    (dto.id() != null ? "?id=" + dto.id() : "");
//        }
//    }
//
//    // DELETE (igual ao aluno)
//    @GetMapping("/delete/{id}")
//    @Transactional
//    public String deletar(@PathVariable("id") Long id,
//                          RedirectAttributes redirectAttributes) {
//
//        try {
//            service.apagarPorId(id);
//            redirectAttributes.addFlashAttribute("message",
//                    "O palestrante " + id + " foi apagado!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//        }
//
//        return "redirect:/palestrante";
//    }
//
//}