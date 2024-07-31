package com.example.demo.Controller;

import com.example.demo.Lista.*;
import jakarta.persistence.Id;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.demo.Lista.Status.EM_ANDAMENTO;
import static com.example.demo.Lista.Status.PENDENTE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListControllerTest {
    @Mock
    private ListaRepository repository;

    @InjectMocks
    private ListController listController;

    @Captor
    private ArgumentCaptor<ListaJPA> argumentCaptor;
    @Nested
    class adicionar{
        @Test
        @DisplayName("Should create a task")
        void shouldAddTask()
        {
            var tarefa = new ListaJPA(new DadosCadastroTarefa("tarefa", "nova tarefa", PENDENTE));

            doReturn(tarefa).when(repository).save(any(ListaJPA.class));

            var novaTarefa = new DadosCadastroTarefa("titulo", "descrição", PENDENTE);

            var output = repository.save(new ListaJPA(novaTarefa));

            assertNotNull(output);
        }

        @Test
        @DisplayName("Should return error and verify the methods of tarefa data")
        void shouldThrowExceptionWhenErrorOccurs()
        {
            var novaTarefa = new DadosCadastroTarefa("titulo", "descrição", PENDENTE);
            var tarefa = new ListaJPA(novaTarefa);

            doThrow(new RuntimeException()).when(repository).save(any(ListaJPA.class));

            assertThrows(RuntimeException.class, () -> repository.save(tarefa));

            verify(repository).save(argumentCaptor.capture());

            var tarefaCapturada = argumentCaptor.getValue();
            assertNotNull(tarefaCapturada);
            assertEquals(novaTarefa.titulo(), tarefaCapturada.getTitulo());
        }
    }

    @Nested
    class deletar{
        @Test
        @DisplayName("Should delete a task by your id")
        void shouldDeleteTaskById()
        {
            var tarefa = new ListaJPA(new DadosCadastroTarefa("tarefa", "nova tarefa", PENDENTE));
            var Idtarefa = repository.getReferenceById(tarefa.getId());

            doNothing().when(repository).delete(Idtarefa);

            var tarefaCriada = repository.save(tarefa);

            repository.delete(Idtarefa);

            assertNull(tarefaCriada);
        }

        @Test
        @DisplayName("Should Delete all tasks")
        void shouldDeleteAllTasks()
        {
            var tarefa = new ListaJPA(new DadosCadastroTarefa("tarefa", "nova tarefa", PENDENTE));

            doNothing().when(repository).deleteAll();

            var tarefaCriada = repository.save(tarefa);

            repository.deleteAll();

            assertNull(tarefaCriada);
        }
    }

    @Nested
    class listALl{
        @Test
        @DisplayName("Should return all tasks")
        void shouldReturnAllTasks()
        {
            var tarefa = new ListaJPA(new DadosCadastroTarefa("tarefa", "nova tarefa", PENDENTE));

            doReturn(List.of(tarefa)).when(repository).findAll();

            var output = repository.findAll();
            assertNotNull(output);
        }
    }

    @Nested
    class Edit{
        @Test
        @DisplayName("Should edit a task by id")
        void shouldEditTaskById()
        {
            var tarefa = new ListaJPA(new DadosCadastroTarefa("tarefa", "nova tarefa", PENDENTE));
            tarefa.setId(1L);

            var novaTarefa = new DadosAtualizarTarefa(tarefa.getId(),"novo titulo", "nova descrição", EM_ANDAMENTO);

            doReturn(tarefa).when(repository).save(any(ListaJPA.class));
            doReturn(Optional.of(tarefa)).when(repository).findById(novaTarefa.id());

            Optional<ListaJPA> optionalTarefa = repository.findById(novaTarefa.id());

            if (optionalTarefa.isPresent()) {
                ListaJPA tarefaExistente = optionalTarefa.get();
                tarefaExistente.setTitulo(novaTarefa.titulo());
                tarefaExistente.setDescricao(novaTarefa.descricao());
                tarefaExistente.setStatus(novaTarefa.status());
                repository.save(tarefaExistente);
            }

            verify(repository).save(argumentCaptor.capture());
            var tarefasCapttor = argumentCaptor.getValue();

            assertEquals(novaTarefa.id(), tarefasCapttor.getId());
            assertEquals(novaTarefa.titulo(), tarefasCapttor.getTitulo());
            assertEquals(novaTarefa.descricao(), tarefasCapttor.getDescricao());
            assertEquals(novaTarefa.status(), tarefasCapttor.getStatus());

            verify(repository, times(1)).findById(tarefasCapttor.getId());
            verify(repository, times(1)).save(tarefa);

        }
    }
}