package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "T_CM_CLIENTE")
@Data @NoArgsConstructor @AllArgsConstructor
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_logradouro", nullable = false)
    private Logradouro logradouro;

    @NotBlank @Size(max = 100)
    @Column(name = "nm_cliente", nullable = false, length = 100)
    private String nome;

    @NotBlank @Size(max = 14)
    @Column(name = "nr_cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank @Email @Size(max = 100)
    @Column(name = "nm_email", nullable = false, unique = true, length = 100)
    private String email;
}
