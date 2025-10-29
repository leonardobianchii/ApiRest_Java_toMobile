package com.fiap.mottu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mottu.dto.SensorRfidDto;
import com.fiap.mottu.mapper.SensorRfidMapper;
import com.fiap.mottu.model.FilialDepartamento;
import com.fiap.mottu.model.SensorRfid;
import com.fiap.mottu.repository.FilialDepartamentoRepository;
import com.fiap.mottu.repository.SensorRfidRepository;
import com.fiap.mottu.exception.ResourceNotFoundException;

@Service
public class SensorRfidService {

    @Autowired private SensorRfidRepository sensorRepo;
    @Autowired private FilialDepartamentoRepository filialRepo;
    @Autowired private SensorRfidMapper mapper;

    public List<SensorRfidDto> findAll() {
        return sensorRepo.findAll().stream().map(mapper::toDto).toList();
    }

    public SensorRfidDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public SensorRfidDto create(SensorRfidDto dto) {
        FilialDepartamento filial = filialRepo.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));
        SensorRfid salvo = sensorRepo.save(mapper.toEntity(dto, filial));
        return mapper.toDto(salvo);
    }

    public SensorRfidDto update(Integer id, SensorRfidDto dto) {
        SensorRfid existente = buscarOuFalhar(id);
        FilialDepartamento filial = filialRepo.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));

        existente.setNome(dto.nome());
        existente.setFilial(filial);
        existente.setLocalizacao(dto.localizacao());

        return mapper.toDto(sensorRepo.save(existente));
    }

    public void delete(Integer id) {
        sensorRepo.delete(buscarOuFalhar(id));
    }

    private SensorRfid buscarOuFalhar(Integer id) {
        return sensorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor RFID id " + id + " não encontrado"));
    }
}
