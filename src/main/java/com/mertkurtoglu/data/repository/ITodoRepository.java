package com.mertkurtoglu.data.repository;

import com.mertkurtoglu.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITodoRepository extends CrudRepository<TodoEntity, Long> {
    @Query("select t from TodoEntity t")
    List<TodoEntity> myTodoList();

}
