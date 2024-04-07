package br.com.carlosborges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.carlosborges.entity.Todo;
import br.com.carlosborges.repository.TodoRepository;


@DataJpaTest
@ActiveProfiles("test")
class TodoRepositoryTest {	
	
	private WebTestClient client;
	
	@Autowired
	private TodoRepository  repo;	
	
	
	@Test
	@DisplayName("Should get Todo successfully from DB")
	void createTodo_ReturnsTodos() {
		
		Todo obj = new Todo("Todo1","Decr1", true, 1);		
		
		repo.save(obj);
		List<Todo> todos = repo.findAll();
	
		assertEquals(1, todos.size());
		assertEquals(obj, todos.get(0));
		assertEquals(obj.getNome(), todos.get(0).getNome());
		assertEquals(obj.getDescricao(), todos.get(0).getDescricao());
		assertEquals(obj.isRealizado(), todos.get(0).isRealizado());
		assertEquals(obj.getPrioridade(), todos.get(0).getPrioridade());
	}
	
	@Test
	@DisplayName("Should get Todo Failure from DB")
	void createTodo_Failure() {
		
		client = WebTestClient.bindToController(repo).build();
			client
			.post()			 
			.uri("/todos")
			.bodyValue(new Todo("","",false,0))
			.exchange()
			.expectStatus().isNotFound();
				
				
				
		

			
	}
		
	
	
}
