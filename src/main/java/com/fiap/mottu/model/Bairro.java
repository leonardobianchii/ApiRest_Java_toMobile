package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_BAIRRO")
@Data @NoArgsConstructor @AllArgsConstructor
public class Bairro {
    @Id
    @Column(name = "id_bairro")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @NotBlank @Size(max = 100)
    @Column(name = "nm_bairro", nullable = false, length = 100)
    private String nome;
}
