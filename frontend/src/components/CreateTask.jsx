import React, { Component } from "react";
import "./style.css";
import TodoService from "../services/TodoService";

class CreateTask extends Component {
  constructor(props) {
    super(props);

    // State
    this.state = {
      todoDto: {},
      todoList: []
    };
  }
  // Component Did Mount
  componentDidMount() {
    TodoService.todoServiceList()
      .then((response) => {
        this.setState({
          todoList: response.data,
        }); // end setState
      })
      .catch((error) => {
        console.error("List Failed: " + error);
      });
  }

  // Function to handle input value change
  onChangeInputValue = (event) => {
    const { name, value } = event.target;

    // Update state
    this.setState({
      [name]: value,
    });
  };

  // Function to handle todo create submission
  todoCreateSubmit = async (event) => {
    event.preventDefault();

    const { task } = this.state;
    const todoDto = { task };

    TodoService.todoServiceCreate(todoDto)
      .then((response) => {
        if (response.status === 200) {
          window.location.reload(true);
        }
      })
      .catch((err) => console.error(err));
  };

  // Render method
  render() {
    // Return the JSX
    return (
      <>
        <h2 className="headerText" style={{ textAlign: "center" }}>
          Todo Input
        </h2>
        <form id="newtodo" action="#" method="post" autoComplete="on">
          <div className="form-group mb-3">
            <input
              type="text"
              id="task"
              name="task"
              className="form-control"
              placeholder="New Todo"
              onChange={this.onChangeInputValue}
              required
              autoFocus
            />
          </div>
          <div className="form-group">
            <button type="submit" className="btn" onClick={this.todoCreateSubmit}>
              Add new task
            </button>
          </div>
        </form>
      </>
    );
  }
}
export default CreateTask;
