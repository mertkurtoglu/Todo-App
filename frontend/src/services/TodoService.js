// Axios
import axios from "axios";

// Persist URL
const todo_URL = "/todo/api/v1";

// Class
class TodoService {
  // Create
  // localhost:2222/todo/api/v1/create
  todoServiceCreate(todoDto) {
    return axios.post(`${todo_URL}/create`, todoDto);
  }

  // List
  // localhost:2222/todo/api/v1/list
  todoServiceList() {
    return axios.get(`${todo_URL}/list`);
  }

  // Find
  // localhost:2222/todo/api/v1/find/1
  todoServiceFindById(id) {
    return axios.get(`${todo_URL}/find/${id}`);
  }

  // Update
  // localhost:2222/todo/api/v1/update/1
  todoServiceUpdateById(id, todoDto) {
    return axios.put(`${todo_URL}/update/${id}`, todoDto);
  }

  //Delete All
  allDeleteService() {
    return axios.get(`${todo_URL}/all/delete`);
  }

  // Delete
  // localhost:2222/todo/api/v1/delete/1
  todoServiceDeleteById(id) {
    return axios.delete(`${todo_URL}/delete/${id}`);
  }
}

export default new TodoService();
