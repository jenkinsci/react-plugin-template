/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from "react";

import "react-datepicker/dist/react-datepicker.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "./style/index.css";
import {apiGetTodos, apiSetTodos} from "../../api";

export default class App extends React.Component {
  constructor() {
    super();
    this.state = {
      todos: [
        "asd",
        "asdasd"
      ],
    };
  }

  componentDidMount() {
    apiGetTodos().then(res => {
      this.setState({
        todos: res.data.data,
        newTodoName:undefined,
      })
    })
  }

  handleNewTodoNameChange = (e)=>{
    this.setState({
      newTodoName:e.target.value
    })
  }

  addNewTodo = (e)=>{
    let todos = this.state.todos;
    todos.push({name:this.state.newTodoName});
    apiSetTodos(todos).then(res=>{
      this.setState({
        todos:res.data.data,
        newTodoName:"",
      })
    })
  }

  itemDone = (index)=>{
    let todos = this.state.todos;
    todos.splice(index,1)
    apiSetTodos(todos).then(res=>{
      this.setState({
        todos:res.data.data,
      })
    })
  }

  handleInputKeyPressed = (event)=>{
    if (event.key === 'Enter') {
      this.addNewTodo();
    }
  }


  render() {
    const todos = this.state.todos;
    return (
      <div>
        <h3>
          React Boilerplate for Jenkins Plugin
        </h3>
        <h4>Todos</h4>
        {todos&&todos.map((item,index)=>
        <div key={item.index} className={"todo-item"}>
          <p className={"todo-title"}>{item.name}</p>
          <button className={"btn-done"} onClick={this.itemDone.bind(this,index)}>DONE</button>
        </div>)}
        {todos.length===0&&<p>There is no todos.</p>}
        <p style={{marginTop:50}}>New Todo</p>
        <input onKeyPress={this.handleInputKeyPressed} className="input" type="text" value={this.state.newTodoName} onChange={this.handleNewTodoNameChange}/>
        <button className={"btn-gray"} onClick={this.addNewTodo}>ADD</button>
      </div>
    );
  }
}
