package com.fiap.mottu.mapper;

import org.springframework.stereotype.Component;

import com.fiap.mottu.dto.SensorRfidDto;
import com.fiap.mottu.model.FilialDepartamento;
import com.fiap.mottu.model.SensorRfid;

@Component
public class SensorRfidMapper {

    public SensorRfid toEntity(SensorRfidDto dto, FilialDepartamento filial) {
        SensorRfid s = new SensorRfid();
        s.setId(dto.id());
        s.setNome(dto.nome());
        s.setFilial(filial);
        s.setLocalizacao(dto.localizacao());
        return s;
    }

    public SensorRfidDto toDto(SensorRfid s) {
        Long idFilial = s.getFilial() != null ? s.getFilial().getId() : null;
        return new SensorRfidDto(
                s.getId(),
                s.getNome(),
                idFilial,
                s.getLocalizacao()
        );
    }
}
