package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_CIDADE")
@Data @NoArgsConstructor @AllArgsConstructor
public class Cidade {
    @Id
    @Column(name = "id_cidade")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @NotBlank @Size(max = 50)
    @Column(name = "nm_cidade", nullable = false, length = 50)
    private String nome;
}
