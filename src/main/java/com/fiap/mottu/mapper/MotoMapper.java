package com.fiap.mottu.mapper;

import org.springframework.stereotype.Component;
import com.fiap.mottu.dto.MotoDto;
import com.fiap.mottu.model.FilialDepartamento;
import com.fiap.mottu.model.Modelo;
import com.fiap.mottu.model.Moto;

@Component
public class MotoMapper {

    public Moto toEntity(MotoDto dto, Modelo modelo, FilialDepartamento filial) {
        Moto moto = new Moto();
        moto.setId(dto.id());
        moto.setModelo(modelo);
        moto.setFilial(filial);
        moto.setPlaca(dto.placa());
        moto.setStatus(dto.status());
        moto.setKmRodado(dto.kmRodado());
        return moto;
    }

    public MotoDto toDto(Moto moto) {
        return new MotoDto(
                moto.getId(),
                moto.getModelo().getId(),
                moto.getFilial().getId(),
                moto.getPlaca(),
                moto.getStatus(),
                moto.getKmRodado()
        );
    }
}
