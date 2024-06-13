package com.example.demo.Lista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTarefa(
        @NotBlank
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        Status status
) {}
