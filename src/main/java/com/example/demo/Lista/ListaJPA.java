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


    // Getters
    public String getTitulo(){
        return  this.titulo;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    // Setters

    //APENAS USADO PARA TESTE
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
