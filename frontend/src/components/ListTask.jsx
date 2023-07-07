import React, { Component } from "react";
import "./style.css";
import { Link } from "react-router-dom";
import TodoService from "../services/TodoService";
import todoService from "../services/TodoService";

class ListTask extends Component {
  constructor(props) {
    super(props);

    // State
    this.state = {
      todoDto: {},
      todoList: [],
      id: this.props.match.params.id,
      selectedItems: [],
      task:null
    };
  }
  // Component Did Mount
  componentDidMount() {
    TodoService.todoServiceList()
      .then((response) => {
        this.setState({
          todoList: response.data,
        });
      })
      .catch((error) => {
        console.error("List Failed: " + error);
      });

    const selectedItems = window.localStorage.getItem("selectedItems");
    if (selectedItems) {
      this.setState({ selectedItems: JSON.parse(selectedItems) });
    }
  }

  // Function to handle the change of isRead checkbox
  onChangeIsRead = (id) => {
    this.setState((prevState) => {
      const selectedItems = prevState.selectedItems.includes(id)
          ? prevState.selectedItems.filter((item) => item !== id)
          : [...prevState.selectedItems, id];

      localStorage.setItem("selectedItems", JSON.stringify(selectedItems));

      return { selectedItems };
    });
  };

  // Function to select/deselect all done tasks
  selectAllDone() {
    this.setState((prevState) => {
      const { todoList, selectedItems } = prevState;
      const newSelectedItems = selectedItems.length === todoList.length ? [] : todoList.map((_, index) => index);
      localStorage.setItem("selectedItems", JSON.stringify(newSelectedItems));
      return { selectedItems: newSelectedItems };
    });
  }

  // Function to delete all done tasks
  deleteAllDoneTasks() {
    const { todoList, selectedItems } = this.state;

    console.log(todoList)

    Promise.all(
        selectedItems.map((index) => TodoService.todoServiceDeleteById(todoList[index].id))
    )
        .then(() => this.setState({
          todoList: todoList.filter((_, index) => !selectedItems.includes(index)),
          selectedItems: [],
        }))
        .finally(() => window.localStorage.removeItem("selectedItems"));
  }

  // Function to delete all tasks
  deleteAll() {
    TodoService.allDeleteService()
      .then((response) => {
        this.setState({
          todoList: this.state.todoList,
        });
        window.location.reload(true);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  // Function to delete a task by ID
  delete(id) {
    TodoService.todoServiceDeleteById(id)
      .then((response) => {
        this.setState({
          todoList: this.state.todoList.filter((todoList) => todoList.id !== id),
        });
      })
      .catch((error) => {
        console.error("Todo Delete Failed: " + error);
      });
  }

  // Function to navigate to the update page
  update(id) {
    let task = prompt('Please enter your new task:');

    const todoDto = { task, id };

    // Update todo using TodoService
    TodoService.todoServiceUpdateById(id, todoDto)
        .then((response) => {
          if (response.status === 200) {
            this.props.history.push("/");
          }
        })
        .catch((err) => console.error(err));

    window.location.reload();
  }

  // Render method
  render() {
    // Destructure state variables for easier usage
    const { todoList, selectedItems } = this.state;

    // Return the JSX
    return (
      <>
        <h2 className="headerText" style={{ textAlign: "center" }}>
          Todo List
        </h2>
        <div className="col">
          {todoList.map((todo, index) => (
            <div className={`card ${selectedItems.includes(index) ? "text-decoration-line-through" : ""}`} key={index}>
              <div className="card-body">
                <div className="row">
                  <div className="col">{todo.task}</div>
                  <div className="col-auto">
                    <div className="row">
                      <div className="col">
                        <input
                          className="form-check-input"
                          style={{ marginRight: "20px", width: "20px", height: "20px" }}
                          type="checkbox"
                          id="isRead"
                          name="isRead"
                          onChange={() => this.onChangeIsRead(index)}
                          checked={selectedItems.includes(index)}
                        />
                        <button
                            style={{ marginRight: "20px", fontSize: "20px", background: "none", border: "none", cursor: "pointer" }}
                            className="text-warning"
                            onClick={() => {
                              this.update(todo.id);
                            }}
                        >
                          <i className="fas fa-pencil-alt"></i>
                        </button>
                        <button
                            style={{ marginRight: "20px", fontSize: "20px", background: "none", border: "none", cursor: "pointer" }}
                            className="text-danger"
                            onClick={() => {
                              if (window.confirm("Delete this task? =>" + todo.task)) this.delete(todo.id);
                            }}
                        >
                          <i className="fas fa-trash"></i>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          ))}

          <div id="btnCard" className="card">
            <div className="card-body">
              <button
                type="submit"
                className="btn btn-danger"
                onClick={() => {
                  if (window.confirm("Delete all tasks?")) this.deleteAll();
                }}
              >
                Delete All Tasks
              </button>
              <button type="button" className="btn btn-primary" onClick={() => this.selectAllDone()}>
                Select All Done
              </button>
              <button
                type="submit"
                className="btn btn-danger"
                onClick={() => {
                  if (window.confirm("Delete all done tasks?")) this.deleteAllDoneTasks();
                }}
              >
                Delete Done Tasks
              </button>
            </div>
          </div>
        </div>
      </>
    );
  }
}
export default ListTask;
