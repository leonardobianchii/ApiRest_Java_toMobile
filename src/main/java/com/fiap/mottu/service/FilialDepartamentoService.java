package com.fiap.mottu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mottu.dto.FilialDepartamentoDto;
import com.fiap.mottu.mapper.FilialDepartamentoMapper;
import com.fiap.mottu.model.FilialDepartamento;
import com.fiap.mottu.repository.FilialDepartamentoRepository;
import com.fiap.mottu.exception.ResourceNotFoundException;

@Service
public class FilialDepartamentoService {

    @Autowired private FilialDepartamentoRepository repository;
    @Autowired private FilialDepartamentoMapper mapper;

    public List<FilialDepartamentoDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public FilialDepartamentoDto findById(Long id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public FilialDepartamentoDto create(FilialDepartamentoDto dto) {
        // se o ID for AUTO/IDENTITY, ignore dto.id() aqui
        FilialDepartamento salvo = repository.save(mapper.toEntity(
                new FilialDepartamentoDto(null, dto.nome())
        ));
        return mapper.toDto(salvo);
    }

    public FilialDepartamentoDto update(Long id, FilialDepartamentoDto dto) {
        FilialDepartamento existente = buscarOuFalhar(id);
        existente.setNome(dto.nome());
        return mapper.toDto(repository.save(existente));
    }

    public void delete(Long id) {
        repository.delete(buscarOuFalhar(id));
    }

    private FilialDepartamento buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento id " + id + " não encontrado"));
    }
}
