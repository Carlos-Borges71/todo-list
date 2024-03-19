package repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{

}
