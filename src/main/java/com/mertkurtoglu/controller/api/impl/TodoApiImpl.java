package com.mertkurtoglu.controller.api.impl;

import com.mertkurtoglu.business.dto.TodoDto;
import com.mertkurtoglu.business.service.ITodoGenericsService;
import com.mertkurtoglu.controller.api.ITodoGenericsApi;
import com.mertkurtoglu.error.ApiResult;
import com.mertkurtoglu.util.FrontEndURL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@RestController
@CrossOrigin(origins = FrontEndURL.FRONTEND_URL)
@RequestMapping("todo/api/v1")
public class TodoApiImpl implements ITodoGenericsApi<TodoDto> {
    private final ITodoGenericsService iTodoGenericsService;

    private ApiResult apiResult;
    //--- ROOT ---//
    // localhost:2222
    // localhost:2222/index
    @Override
    @GetMapping({"/", "/index"})
    public ResponseEntity<String> getRoot() {
        return ResponseEntity.ok("index");
    }

    //--- SPEED ---//
    @Override
    @GetMapping("/all/delete")
    public ResponseEntity<String> allDeleteService(){
        return ResponseEntity.ok(iTodoGenericsService.allDeleteService());
    }

    //--- CRUD ---//
    //CREATE
    // localhost:2222/todo/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> todoServiceCreate(@Valid @RequestBody TodoDto todoDto){
        return ResponseEntity.ok(iTodoGenericsService.todoServiceCreate(todoDto));
    }

    //LIST
    // localhost:2222/todo/api/v1/list
    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<List<TodoDto>> todoServiceList() {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceList());
    }

    //FIND
    // localhost:2222/todo/api/v1/find
    // localhost:2222/todo/api/v1/find/0
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> todoServiceFindById(@PathVariable(name = "id", required = false) Long id){
        if (id == null) {
            log.error("Todo Api Null Pointer Exception");
            throw new NullPointerException(id + " Todo Api Null");
        }
        if (id == 0) {
            log.error("Todo Api BadRequest");
            //(int status, String error, String message, String path)
            apiResult = new ApiResult(400, "Bad Request", " Bad Request", "/todo/api/v1/find/0");
            return ResponseEntity.ok(apiResult);
        }
        return ResponseEntity.ok(iTodoGenericsService.todoServiceFindById(id));
    }

    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<?> todoServiceUpdateById(
            @PathVariable(name = "id", required = false) Long id,
            @Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceUpdateById(id, todoDto));
    }

    //DELETE
    // localhost:2222/todo/api/v1/delete/1
    @Override
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<?> todoServiceDeleteById(@PathVariable(name = "id", required = false) Long id){
        return ResponseEntity.ok(iTodoGenericsService.todoServiceDeleteById(id));
    }
}
