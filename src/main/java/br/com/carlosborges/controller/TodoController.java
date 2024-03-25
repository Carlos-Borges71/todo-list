package br.com.carlosborges.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	private TodoService todoService;
	
		
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@PostMapping
	public ResponseEntity<List<Todo>> create(@RequestBody Todo obj){		
		todoService.create(obj);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Todo>> findAll(){
		List<Todo> todo = todoService.findAll();
		return ResponseEntity.ok().body(todo);
	}
	@GetMapping(value="/{id}")
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
