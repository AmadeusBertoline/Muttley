package com.muttley.muttley.model;

public class Certificado {
	
	private Long id;
    private String nomeDoCurso;
    private int cargaHoraria;
    private String imagemUrl;
    
    private Aluno aluno; 
    private Instituicao instituicao;
    private Palestrante palestrante;

    public Certificado() {}

    public Certificado(Long id, String nomeDoCurso, int cargaHoraria, String imagemUrl, 
                       Aluno aluno, Instituicao instituicao, Palestrante palestrante) {
        this.id = id;
        this.nomeDoCurso = nomeDoCurso;
        this.cargaHoraria = cargaHoraria;
        this.imagemUrl = imagemUrl;
        this.aluno = aluno;
        this.instituicao = instituicao;
        this.palestrante = palestrante;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeDoCurso() { return nomeDoCurso; }
    public void setNomeDoCurso(String nomeDoCurso) { this.nomeDoCurso = nomeDoCurso; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }

    public Palestrante getPalestrante() { return palestrante; }
    public void setPalestrante(Palestrante palestrante) { this.palestrante = palestrante; }

}
