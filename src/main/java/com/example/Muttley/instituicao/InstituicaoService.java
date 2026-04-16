package com.example.Muttley.instituicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository repository;

    // Salvar ou Atualizar
    public void salvar(Instituicao instituicao) {
        repository.save(instituicao);
    }

    // Listar todas (usado no card enquanto não temos a sessão de login)
    public List<Instituicao> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID para a Edição
    public Optional<Instituicao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Excluir Instituição
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Lógica de Login da Instituição
    public Optional<Instituicao> realizarLogin(String email, String senha) {
        return repository.findByEmail(email)
                .filter(inst -> inst.getSenha() != null && inst.getSenha().equals(senha));
    }
}