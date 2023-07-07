package com.mertkurtoglu.business.service;

import java.util.List;
public interface ITodoGenericsService<D,E>{

    //--- ModelMapper ---//
    public D EntityToDto(E e);
    public E DtoToEntity(D d);

    //--- CRUD ---//
    //Create
    public D todoServiceCreate(D d);

    //List
    public List<D> todoServiceList();

    // Find
    public D todoServiceFindById(Long id);

    //Update
    public D todoServiceUpdateById(Long id, D d);

    //Delete
    public D todoServiceDeleteById(Long id);

    // Delete all data
    public String allDeleteService();
}





