package com.fiap.mottu.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.fiap.mottu.dto.MotoDto;
import com.fiap.mottu.service.MotoService;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MotoDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MotoDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<MotoDto> create(@Valid @RequestBody MotoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public MotoDto update(@PathVariable Integer id, @Valid @RequestBody MotoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

