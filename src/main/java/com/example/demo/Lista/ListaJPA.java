package com.example.demo.Lista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "list")
@Entity(name = "Lista")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ListaJPA {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;

    public ListaJPA(DadosCadastroTarefa dados){
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.status = dados.status();
    }


    public void atualizar(DadosAtualizarTarefa dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.status() != null){
            this.status = dados.status();
        }
    }
}
