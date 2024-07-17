package com.example.demo.Controller;

import com.example.demo.Lista.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lista")
public class ListController {

    @Autowired
    private ListaRepository repository;

    @GetMapping("/tarefas")
    public List<DadosListagemTarefas> list(Pageable pageable) {
        Page<DadosListagemTarefas> page = repository.findAll(pageable).map(DadosListagemTarefas::new);
        return page.getContent();
    }

    @PostMapping
    @Transactional
    public void adicionar(@RequestBody @Valid DadosCadastroTarefa dados) {
        repository.save(new ListaJPA(dados));
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
         try {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
        } catch (Exception e) {
        
            System.err.println("Error deleting entity with id " + id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarTarefa dados) {
        var tarefa = repository.getReferenceById(dados.id());
        tarefa.atualizar(dados);
    }
}
