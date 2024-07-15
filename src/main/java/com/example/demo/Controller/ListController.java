package com.example.demo.Controller;

import com.example.demo.Lista.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @DeleteMapping("deletar/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarTarefa dados) {
        var tarefa = repository.getReferenceById(dados.id());
        tarefa.atualizar(dados);
    }
}
