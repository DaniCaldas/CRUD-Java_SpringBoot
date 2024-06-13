package com.example.demo.Lista;

public record DadosListagemTarefas(
        Long id,
        String titulo,
        String descricao,
        Status status
) {
    public DadosListagemTarefas(ListaJPA lista){
        this(lista.getId(), lista.getTitulo(), lista.getDescricao(), lista.getStatus());
    }
}
