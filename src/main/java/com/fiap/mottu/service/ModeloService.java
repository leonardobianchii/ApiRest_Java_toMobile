package com.fiap.mottu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mottu.dto.ModeloDto;
import com.fiap.mottu.exception.ResourceNotFoundException;
import com.fiap.mottu.mapper.ModeloMapper;
import com.fiap.mottu.model.Modelo;
import com.fiap.mottu.repository.ModeloRepository;

@Service
public class ModeloService {

    @Autowired private ModeloRepository repository;
    @Autowired private ModeloMapper mapper;

    public List<ModeloDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ModeloDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public ModeloDto create(ModeloDto dto) {
        Modelo salvo = repository.save(mapper.toEntity(dto));
        return mapper.toDto(salvo);
    }

    public ModeloDto update(Integer id, ModeloDto dto) {
        Modelo existente = buscarOuFalhar(id);
        existente.setNome(dto.nome());
        return mapper.toDto(repository.save(existente));
    }

    public void delete(Integer id) {
        repository.delete(buscarOuFalhar(id));
    }

    private Modelo buscarOuFalhar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modelo id " + id + " não encontrado"));
    }
}
