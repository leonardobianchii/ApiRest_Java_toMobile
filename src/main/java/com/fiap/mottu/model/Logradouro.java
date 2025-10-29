package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_LOGRADOURO")
@Data @NoArgsConstructor @AllArgsConstructor
public class Logradouro {
    @Id
    @Column(name = "id_logradouro")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_bairro", nullable = false)
    private Bairro bairro;

    @NotBlank @Size(max = 100)
    @Column(name = "nm_logradouro", nullable = false, length = 100)
    private String nome;

    @NotBlank @Size(max = 10)
    @Column(name = "nr_logradouro", nullable = false, length = 10)
    private String numero;

    @Size(max = 100)
    @Column(name = "nm_complemento", length = 100)
    private String complemento;
}