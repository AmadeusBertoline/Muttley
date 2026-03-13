package com.muttley.muttley.model;

public class Palestrante {
	
	private Long id;
    private String nome;
    private String especialidade;
    private String biografiaCurta;

    public Palestrante() {}

    public Palestrante(Long id, String nome, String especialidade, String biografiaCurta) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.biografiaCurta = biografiaCurta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getBiografiaCurta() { return biografiaCurta; }
    public void setBiografiaCurta(String biografiaCurta) { this.biografiaCurta = biografiaCurta; }

}
