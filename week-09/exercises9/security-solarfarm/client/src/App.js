import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import About from './components/About';
import Contact from './components/Contact';
import Home from './components/Home';
import Login from './components/Login';
import Navbar from './components/Navbar';
import NotFound from './components/NotFound';
import SolarPanelList from './components/SolarPanelList';
import SolarPanelForm from './components/SolarPanelForm';
import UserContext from './contexts/UserContext';

import { logout, refreshToken } from './services/authApi';

const EMPTY_USER = {
  username: '',
  roles: []
};

const REFRESH_TIMER = 14 * 60 * 1000;

function App() {

  const [user, setUser] = useState(EMPTY_USER);

  const auth = {
    user: user,
    onAuthenticated(authenticatedUser) {
      const nextUser = {...user};
      nextUser.username = authenticatedUser.username;
      nextUser.roles = authenticatedUser.roles;
      setUser(nextUser);
      setTimeout(refresh, REFRESH_TIMER);
    },
    onLogout() {
      setUser(EMPTY_USER);
      logout();
    }
  }

  const refresh = () => {
    refreshToken()
      .then(data => {
        auth.onAuthenticated(data);
        setTimeout(refresh, REFRESH_TIMER);
      });
  }

  useEffect(() => {
    refresh();
  }, []);

  return (
    <UserContext.Provider value={auth} >
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
          <Route path={['/solarpanels/add', '/solarpanels/edit/:id']}>
            { user.username ? <SolarPanelForm /> : <Login /> }
          </Route>
          <Route path="/solarpanels">
            <SolarPanelList />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route>
            <NotFound />
          </Route>
        </Switch>

      </Router>
    </UserContext.Provider>
  );
}

export default App;
