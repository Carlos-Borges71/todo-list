package br.com.carlosborges.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.carlosborges.entity.Todo;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class TodoRepositoryTest {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	TodoRepository todoRepository;
	
	
	
	@Test
	@DisplayName("Should get Todo successfully from DB")
	void findAllSuccess() {
		Todo obj = new Todo("Todo1","Desc Todo1", true, 1);
		this.createrTodo(obj);		
		
	
		
		
	}
	
	private Todo createrTodo(Todo obj) {
		Todo newObj = new Todo(obj);
		this.entityManager.persist(newObj);
		return newObj;		
	}
	
	
	
}
