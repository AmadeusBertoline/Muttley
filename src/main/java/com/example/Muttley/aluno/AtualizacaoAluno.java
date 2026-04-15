package com.example.Muttley.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizacaoAluno(
    Long id,
    
    @NotNull(message = "Altura é obrigatória")
    @Positive(message = "Altura deve ser um valor positivo")
    int altura,
    
    @NotNull(message = "Largura é obrigatória")
    @Positive(message = "Largura deve ser um valor positivo")
    int largura,
    
    @NotNull(message = "Comprimento é obrigatório")
    @Positive(message = "Comprimento deve ser um valor positivo")
    int comprimento,
    
    @NotBlank(message = "O material deve ser informado")
    String material
) {}