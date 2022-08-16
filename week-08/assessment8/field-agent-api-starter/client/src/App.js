import Agents from './components/Agents'
import { Router, Route } from "react-router-dom";
import Nav from "./components/Nav";
import Home from "./components/Home";
import Form from "./components/Form";

function App() {
  return (
    <>
      <div className="jumbotron">
        <h1 className="display-4">Field Agents React API</h1>
        <p className="lead">By Miro Stojanovic --- Welcome!</p>
        <hr className="my-4"></hr>
      </div> 
      <Router>
      <Nav />
        <Route exact path="/">
          <Home />
        </Route>
          <Route path="/agents" />
          <Route path="/form"/>
      </Router>
    </>
  );
}

export default App;
