package com.example.demo.Lista;

import jakarta.validation.constraints.NotNull;

public record DadosDeletarTarefa(
    @NotNull
    Long id
) {}
