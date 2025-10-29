package com.fiap.mottu.dto;

import jakarta.validation.constraints.*;

public record FilialDepartamentoDto(
        Long id,
        @NotBlank @Size(max = 100) String nome
) { }
