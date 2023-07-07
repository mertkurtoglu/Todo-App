package com.mertkurtoglu.business.service.impl;

import com.mertkurtoglu.bean.ModelMapperBean;
import com.mertkurtoglu.business.dto.TodoDto;
import com.mertkurtoglu.business.service.ITodoGenericsService;
import com.mertkurtoglu.data.entity.TodoEntity;
import com.mertkurtoglu.data.repository.ITodoRepository;
import com.mertkurtoglu.exception.BadRequestException;
import com.mertkurtoglu.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
public class TodoServiceImpl implements ITodoGenericsService<TodoDto, TodoEntity> {

    private final ModelMapperBean modelMapperBean;
    private final ITodoRepository iTodoRepository;

    @Override
    public TodoDto EntityToDto(TodoEntity todoEntity) {
        return modelMapperBean.modelMapperMethod().map(todoEntity, TodoDto.class);
    }

    @Override
    public TodoEntity DtoToEntity(TodoDto todoDto) {
        return modelMapperBean.modelMapperMethod().map(todoDto, TodoEntity.class);
    }

    //--- CRUD ---//
    //Create
    @Transactional
    @Override
    public TodoDto todoServiceCreate(TodoDto todoDto) {
        if (todoDto != null){
            TodoEntity todoEntityModel = DtoToEntity(todoDto);
            TodoEntity todoEntity = iTodoRepository.save(todoEntityModel);
            todoDto.setId(todoEntity.getId());
            todoDto.setSystemDate(todoEntity.getSystemDate());
        }else if(todoDto==null){
            throw new NotFoundException("TodoDto not found");
        }
        return todoDto;
    }

    //List
    @Override
    public List<TodoDto> todoServiceList() {
        Iterable<TodoEntity> todoEntityIterable= iTodoRepository.findAll();
        List<TodoDto> list = new ArrayList<>();
        for (TodoEntity entity: todoEntityIterable){
            TodoDto todoDto = EntityToDto(entity);
            list.add(todoDto);
        }
        return list;
    }

    //Find
    @Override
    public TodoDto todoServiceFindById(Long id) {
        TodoEntity todoEntity = null;
        if (id != null) {
            todoEntity = iTodoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        } else if (id == null)
            throw new BadRequestException(id + "Todo Dto is null"); // 400
        return EntityToDto(todoEntity);
    }

    //Update
    @Transactional // Create, Delete, Update
    @Override
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        TodoEntity todoEntity = DtoToEntity(todoServiceFindById(id));
        if (todoEntity != null) {
            todoEntity.setId(id);
            todoEntity.setTask(todoDto.getTask());
            iTodoRepository.save(todoEntity);
        }
        return EntityToDto(todoEntity);
    }

    //Delete
    @Transactional
    @Override
    public TodoDto todoServiceDeleteById(Long id) {
        TodoDto todoDtoDeleteFind = todoServiceFindById(id);
        TodoEntity todoEntity = DtoToEntity(todoDtoDeleteFind);
        iTodoRepository.delete(todoEntity);
        return todoDtoDeleteFind;
    }

    //Multiple Data Delete
    @Override
    public String allDeleteService() {
        iTodoRepository.deleteAll();
        log.info("Deleted");
        return "Deleted ";
    }
}
