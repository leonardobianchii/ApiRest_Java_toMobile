package com.fiap.mottu.mapper;

import org.springframework.stereotype.Component;

import com.fiap.mottu.dto.FilialDepartamentoDto;
import com.fiap.mottu.model.FilialDepartamento;

@Component
public class FilialDepartamentoMapper {

    public FilialDepartamento toEntity(FilialDepartamentoDto dto) {
        FilialDepartamento f = new FilialDepartamento();
        f.setId(dto.id());
        f.setNome(dto.nome());
        return f;
    }

    public FilialDepartamentoDto toDto(FilialDepartamento f) {
        return new FilialDepartamentoDto(
                f.getId(),
                f.getNome()
        );
    }
}
