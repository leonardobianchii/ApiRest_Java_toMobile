package com.fiap.mottu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mottu.dto.MotoDto;
import com.fiap.mottu.exception.ResourceNotFoundException;
import com.fiap.mottu.mapper.MotoMapper;
import com.fiap.mottu.model.FilialDepartamento;
import com.fiap.mottu.model.Modelo;
import com.fiap.mottu.model.Moto;
import com.fiap.mottu.repository.FilialDepartamentoRepository;
import com.fiap.mottu.repository.ModeloRepository;
import com.fiap.mottu.repository.MotoRepository;

@Service
public class MotoService {

    @Autowired private MotoRepository motoRepository;
    @Autowired private ModeloRepository modeloRepository;
    @Autowired private FilialDepartamentoRepository filialRepository;
    @Autowired private MotoMapper mapper;

    public List<MotoDto> findAll() {
        return motoRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public MotoDto findById(Integer id) {
        return mapper.toDto(buscarOuFalhar(id));
    }

    public MotoDto create(MotoDto dto) {
        Modelo modelo = modeloRepository.findById(dto.idModelo())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo não encontrado"));
        FilialDepartamento filial = filialRepository.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));
        Moto salvo = motoRepository.save(mapper.toEntity(dto, modelo, filial));
        return mapper.toDto(salvo);
    }

    public MotoDto update(Integer id, MotoDto dto) {
        Moto existente = buscarOuFalhar(id);

        Modelo modelo = modeloRepository.findById(dto.idModelo())
                .orElseThrow(() -> new ResourceNotFoundException("Modelo não encontrado"));
        FilialDepartamento filial = filialRepository.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial/Departamento não encontrado"));

        existente.setModelo(modelo);
        existente.setFilial(filial);
        existente.setPlaca(dto.placa());
        existente.setStatus(dto.status());
        existente.setKmRodado(dto.kmRodado());

        return mapper.toDto(motoRepository.save(existente));
    }

    public void delete(Integer id) {
        motoRepository.delete(buscarOuFalhar(id));
    }

    private Moto buscarOuFalhar(Integer id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto id " + id + " não encontrada"));
    }
}
