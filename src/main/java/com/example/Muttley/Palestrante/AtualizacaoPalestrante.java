package com.example.Muttley.Palestrante;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizacaoPalestrante(
	    
		Long id,
	    @NotBlank(message = "Nome é obrigatório") 
	    String nome,
	    
	    @NotBlank(message = "Telefone é obrigatória")
	    String telefone,
	    
	    @NotNull(message = "CPF é obrigatório")
	    Integer cpf
	    
	    
	) {

 

}
