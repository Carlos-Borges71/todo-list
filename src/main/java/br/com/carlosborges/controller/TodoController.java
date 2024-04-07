package br.com.carlosborges.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.service.TodoService;
import br.com.carlosborges.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
		
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	public TodoController() {		
	}

	@PostMapping
	public ResponseEntity<List<Todo>> create(@RequestBody @Valid Todo obj){	
		
		if (obj.getNome() == null) {
			throw new ObjectNotFoundException("Nulo");
		}
		todoService.create(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Todo>> findAll(){
		List<Todo> todo = todoService.findAll();
		return ResponseEntity.ok().body(todo);
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Todo> findById(@PathVariable Long id){	
		Todo obj = todoService.findById(id);			
		return ResponseEntity.ok().body(obj); 
	}
	
	
		
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Todo obj, @PathVariable Long id){
		obj.setId(id);
		obj = (Todo) todoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Long id){
		todoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
