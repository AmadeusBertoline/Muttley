package com.example.Muttley.instituicao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private InstituicaoMapper instituicaoMapper;

    public Instituicao salvarOuAtualizar(AtualizacaoInstituicao dto) {
        if (dto.id() != null) {
            // ATUALIZAÇÃO: Busca a instituição existente e aplica as mudanças do DTO via Mapper
            Instituicao existente = instituicaoRepository.findById(dto.id())
                    .orElseThrow(() -> new EntityNotFoundException("Instituição não encontrada com ID: " + dto.id()));

            instituicaoMapper.updateEntityFromDto(dto, existente);
            return instituicaoRepository.save(existente);
        } else {
            // CRIAÇÃO: Converte o DTO em uma nova entidade Instituicao
            Instituicao novaInstituicao = instituicaoMapper.toEntityFromAtualizacao(dto);
            return instituicaoRepository.save(novaInstituicao);
        }
    }

    public List<Instituicao> listarTodos() {
        // Ordena por nome para facilitar a visualização na listagem
        return instituicaoRepository.findAll(Sort.by("nome").ascending());
    }

    public void excluir(Long id) {
        instituicaoRepository.deleteById(id);
    }

    public Optional<Instituicao> buscarPorId(Long id) {
        return instituicaoRepository.findById(id);
    }
    
    public Optional<Instituicao> realizarLogin(String email, String senha) {
        return instituicaoRepository.findByEmail(email)
                .filter(inst -> inst.getSenha() != null && inst.getSenha().equals(senha));
    }
}