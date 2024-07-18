package com.example.demo.Controller;

import com.example.demo.Lista.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("lista")
public class ListController {

    @Autowired
    private ListaRepository repository;

    private final ListaRepository logrepository;
    private static final Logger logger = LoggerFactory.getLogger(ListController.class);

    public ListController(ListaRepository logrepository) {
        this.logrepository = logrepository;
    }

    @GetMapping("/tarefas")
    public List<DadosListagemTarefas> list(Pageable pageable) {
        Page<DadosListagemTarefas> page = repository.findAll(pageable).map(DadosListagemTarefas::new);
        return page.getContent();
    }

    @PostMapping("/tarefas")
    @Transactional
    public void adicionar(@RequestBody @Valid DadosCadastroTarefa dados) {
        repository.save(new ListaJPA(dados));
    }

    @DeleteMapping("/deletar")
    @Transactional
    public ResponseEntity<Void> remover(@RequestBody @Valid DadosDeletarTarefa dados) {
        if(!repository.existsById(dados.id())){
            return ResponseEntity.notFound().build();
        }
        try {
            repository.deleteById(dados.id());
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e) {
            logger.error("Item com id {} não encontrado para deletar", dados.id(), e);
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            logger.error("Erro no banco de dados ao deletar item com id {}", dados.id(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            logger.error("Erro inesperado ao deletar item com id {}", dados.id(), e);
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
