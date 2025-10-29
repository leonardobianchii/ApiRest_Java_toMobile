package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_PAIS")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pais {
    @Id
    @Column(name = "id_pais")
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nm_pais", nullable = false, length = 50)
    private String nome;
}
