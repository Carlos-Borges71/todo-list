package br.com.carlosborges;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.carlosborges.entity.Todo;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {
	
	@Autowired(required=true)
	private WebTestClient webTestClient;
	
	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc 1", false, 1);
		
		webTestClient.get()
			
			.uri("/todos")
			
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(35)
			.jsonPath("$[0].id").isEqualTo(3)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
			
	}
	
	@Test
	void testCreateTodofailure() {
	}
}
