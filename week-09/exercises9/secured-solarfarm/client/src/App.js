import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import About from './components/About';
import Contact from './components/Contact';
import Home from './components/Home';
import Navbar from './components/Navbar';
import SolarPanelList from './components/SolarPanelList';
import SolarPanelForm from './components/SolarPanelForm';
import NotFound from './components/NotFound';

function App() {
  return (
    <Router>
      <Navbar />

      <h1 className="my-4">Solar Farm</h1>

      <Switch>
        <Route path="/" exact>
          <Home />
        </Route>
        <Route path="/about">
          <About />
        </Route>
        <Route path="/contact">
          <Contact />
        </Route>
        <Route path={['/solarpanels/add','/solarpanels/edit/:id']}>
          <SolarPanelForm />
        </Route>
        <Route path="/solarpanels">
          <SolarPanelList />
        </Route>
        <Route>
          <NotFound />
        </Route>
      </Switch>

    </Router>
  );
}

export default App;
