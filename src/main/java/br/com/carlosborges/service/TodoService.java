package br.com.carlosborges.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.repository.TodoRepository;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo obj){
		todoRepository.save(obj);
		return findAll();
	}
	
	public List<Todo> findAll(){	
		return todoRepository.findAll();
	}
	 
	public Todo findById(Long id){		
		Optional<Todo> obj = todoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado", obj));		
	}
	
	public Todo update(Todo obj){
		Todo newObj = (Todo) findById(obj.getId());
		upDateData(newObj, obj);
		return todoRepository.save(newObj);
	}
	
	private void upDateData(Todo newObj, Todo obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		newObj.setRealizado(obj.isRealizado());
		newObj.setPrioridade(obj.getPrioridade());
	}

	public List<Todo> delete(Long id){
		todoRepository.deleteById(id);		
		return findAll();	
	}
}

