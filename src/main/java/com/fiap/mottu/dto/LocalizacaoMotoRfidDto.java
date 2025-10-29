package com.fiap.mottu.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record LocalizacaoMotoRfidDto(
        Integer id,
        @NotNull Integer idMoto,
        @NotNull Integer idSensor,
        @NotNull LocalDateTime data
) { }
