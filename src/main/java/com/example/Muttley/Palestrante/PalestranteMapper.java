package com.example.Muttley.Palestrante;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PalestranteMapper {

    AtualizacaoPalestrante toDTO(Palestrante palestrante);

    @Mapping(target = "id", ignore = true)
    Palestrante toEntity(AtualizacaoPalestrante dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AtualizacaoPalestrante dto, @MappingTarget Palestrante palestrante);

    // 🔹 LISTAGEM
    ListagemPalestrante toListagemDto(Palestrante palestrante);
}