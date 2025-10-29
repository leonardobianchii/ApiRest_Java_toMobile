package com.fiap.mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_CM_ALUGUEL")
@Data @NoArgsConstructor @AllArgsConstructor
public class Aluguel {
    @Id
    @Column(name = "id_aluguel")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @NotNull
    @Column(name = "dt_retirada", nullable = false)
    private LocalDateTime retirada;

    @Column(name = "dt_devolucao")
    private LocalDateTime devolucao;
}
