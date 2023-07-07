import React, { Component } from "react";
import ListTask from "./ListTask";
import CreateTask from "./CreateTask";
import { BrowserRouter as Router, Route } from "react-router-dom";

export default class RouterMain extends Component {
  render() {
    return (
      <React.Fragment>
        <Router>
          {/* Route for the CreateTask component */}
          <Route path="/" exact component={CreateTask} />

          {/* Route for the ListTask component */}
          <Route path="/" exact component={ListTask} />
        </Router>
      </React.Fragment>
    );
  }
}
