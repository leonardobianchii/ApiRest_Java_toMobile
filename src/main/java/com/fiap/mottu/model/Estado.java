package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_ESTADO")
@Data @NoArgsConstructor @AllArgsConstructor
public class Estado {
    @Id
    @Column(name = "id_estado")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @NotBlank @Size(max = 50)
    @Column(name = "nm_estado", nullable = false, length = 50)
    private String nome;
}
