package br.com.carlosborges.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.repository.TodoRepository;
import br.com.carlosborges.service.exception.ObjectNotFoundException;

@Service
public class TodoService {
	
	
	@Autowired
	private TodoRepository todoRepository;
	
	

	public List<Todo> create(Todo obj){				
		if(obj.getNome()==null) {
			throw new ObjectNotFoundException(null);
		}
		
		todoRepository.save(obj);
		return findAll();
	}
	
	public List<Todo> findAll(){	
		return todoRepository.findAll(Sort.by(Sort.Direction.ASC,"nome")
				.and(Sort.by(Sort.Direction.ASC,"descricao")));
	}
	 
	public Todo findById(Long id ) {
		Optional<Todo> obj = todoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
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
		todoRepository.deleteById(id);;		
		return findAll();	
	}
}

