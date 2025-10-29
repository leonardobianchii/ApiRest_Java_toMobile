package com.fiap.mottu.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.fiap.mottu.dto.ModeloDto;
import com.fiap.mottu.service.ModeloService;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @GetMapping
    public List<ModeloDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ModeloDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ModeloDto> create(@Valid @RequestBody ModeloDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ModeloDto update(@PathVariable Integer id, @Valid @RequestBody ModeloDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
