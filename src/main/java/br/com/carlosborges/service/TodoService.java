package br.com.carlosborges.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.repository.TodoRepository;
import br.com.carlosborges.service.exception.ObjectNotFoundException;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo){
		todoRepository.save(todo);
		return findAll();
	}
	
	public List<Todo> findAll(){
		//Sort sort = Sort.by(Direction.DESC,"prioridade").and(
			//Sort.by(Direction.ASC, "nome"));
		return todoRepository.findAll();
	}
	
	public Todo findById(Long id){
		Optional<Todo> todo = todoRepository.findById(id);
		return todo.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	
	public Todo update(Todo todo){
		Todo newTodo = (Todo) findById(todo.getId());
		upDateData(newTodo, todo);
		return todoRepository.save(newTodo);
	}
	
	private void upDateData(Todo newTodo, Todo todo) {
		newTodo.setNome(todo.getNome());
		newTodo.setDescricao(todo.getDescricao());
		newTodo.setRealizado(todo.isRealizado());
		newTodo.setPrioridade(todo.getPrioridade());
	}

	public List<Todo> delete(Long id){
		todoRepository.deleteById(id);		
		return findAll();
	
	}
}

