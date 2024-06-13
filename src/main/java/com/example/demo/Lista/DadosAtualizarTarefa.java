package com.example.demo.Lista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTarefa(
   @NotNull
   Long id,
   String titulo,
   String descricao,
   Status status
) {}
