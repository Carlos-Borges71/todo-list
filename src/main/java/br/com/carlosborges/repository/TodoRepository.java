package br.com.carlosborges.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosborges.entity.Todo;


public interface TodoRepository extends JpaRepository<Todo,Long>{

}
