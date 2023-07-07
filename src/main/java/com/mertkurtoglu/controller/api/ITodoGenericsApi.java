package com.mertkurtoglu.controller.api;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ITodoGenericsApi<D> {
    //Spring MVC
    public ResponseEntity<String> getRoot();

    //--- CRUD ---//
    //CREATE
    public ResponseEntity<?> todoServiceCreate(D d);

    //LIST
    public ResponseEntity<List<D>> todoServiceList();

    //FIND
    public ResponseEntity<?> todoServiceFindById(Long id);

    //Update
    public ResponseEntity<?>  todoServiceUpdateById(Long id, D d);

    //All Delete
    public ResponseEntity<String> allDeleteService();

    //DELETE
    public ResponseEntity<?> todoServiceDeleteById(Long id);

}
