import Agents from './components/Agents'
import Home from './components/Home'
import Nav from './components/Nav'
import Form from './components/Form'
import AgentTable from './components/AgentTable'
import NewAgent from './components/NewAgent'
import NotFound from './components/NotFound'
import EditAgent from './components/EditAgent'
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";




function App() {
  return (
      <Router>
        <Nav />
        <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route exact path="/agents">
              <Agents />
            </Route>
            <Route exact path="/agents/add">
              <NewAgent />
            </Route>
            <Route exact path="/agents/edit/:agentId">
              <EditAgent />
            </Route>
            <Route path="*">
              <NotFound />
            </Route>
        </Switch>
      </Router> 
  );
}

export default App;
