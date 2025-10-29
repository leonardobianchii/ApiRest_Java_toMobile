package com.fiap.mottu.mapper;

import org.springframework.stereotype.Component;

import com.fiap.mottu.dto.LocalizacaoMotoRfidDto;
import com.fiap.mottu.model.LocalizacaoMotoRfid;
import com.fiap.mottu.model.Moto;
import com.fiap.mottu.model.SensorRfid;

@Component
public class LocalizacaoMotoRfidMapper {

    public LocalizacaoMotoRfid toEntity(LocalizacaoMotoRfidDto dto, Moto moto, SensorRfid sensor) {
        LocalizacaoMotoRfid l = new LocalizacaoMotoRfid();
        l.setId(dto.id());
        l.setMoto(moto);
        l.setSensor(sensor);
        l.setData(dto.data());
        return l;
    }

    public LocalizacaoMotoRfidDto toDto(LocalizacaoMotoRfid l) {
        Integer idMoto = l.getMoto() != null ? l.getMoto().getId() : null;
        Integer idSensor = l.getSensor() != null ? l.getSensor().getId() : null;
        return new LocalizacaoMotoRfidDto(
                l.getId(),
                idMoto,
                idSensor,
                l.getData()
        );
    }
}
