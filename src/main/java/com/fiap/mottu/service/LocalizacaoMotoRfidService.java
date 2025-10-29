package com.fiap.mottu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mottu.dto.LocalizacaoMotoRfidDto;
import com.fiap.mottu.exception.ResourceNotFoundException;
import com.fiap.mottu.mapper.LocalizacaoMotoRfidMapper;
import com.fiap.mottu.model.LocalizacaoMotoRfid;
import com.fiap.mottu.model.Moto;
import com.fiap.mottu.model.SensorRfid;
import com.fiap.mottu.repository.LocalizacaoMotoRfidRepository;
import com.fiap.mottu.repository.MotoRepository;
import com.fiap.mottu.repository.SensorRfidRepository;

@Service
public class LocalizacaoMotoRfidService {

    @Autowired private LocalizacaoMotoRfidRepository repository;
    @Autowired private MotoRepository motoRepository;
    @Autowired private SensorRfidRepository sensorRepository;
    @Autowired private LocalizacaoMotoRfidMapper mapper;

    public List<LocalizacaoMotoRfidDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public LocalizacaoMotoRfidDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public LocalizacaoMotoRfidDto create(LocalizacaoMotoRfidDto dto) {
        Moto moto = motoRepository.findById(dto.idMoto())
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));
        SensorRfid sensor = sensorRepository.findById(dto.idSensor())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor RFID não encontrado"));

        LocalizacaoMotoRfid salvo = repository.save(mapper.toEntity(dto, moto, sensor));
        return mapper.toDto(salvo);
    }

    public LocalizacaoMotoRfidDto update(Integer id, LocalizacaoMotoRfidDto dto) {
        LocalizacaoMotoRfid existente = buscarOuFalhar(id);

        Moto moto = motoRepository.findById(dto.idMoto())
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));
        SensorRfid sensor = sensorRepository.findById(dto.idSensor())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor RFID não encontrado"));

        existente.setMoto(moto);
        existente.setSensor(sensor);
        existente.setData(dto.data());

        return mapper.toDto(repository.save(existente));
    }

    public void delete(Integer id) {
        repository.delete(buscarOuFalhar(id));
    }

    private LocalizacaoMotoRfid buscarOuFalhar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Localização RFID id " + id + " não encontrada"));
    }
}
