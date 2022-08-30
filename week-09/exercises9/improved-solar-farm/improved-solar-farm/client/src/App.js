import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

import Header from './components/Header';
import About from './components/About';
import Contact from './components/Contact';
import Home from './components/Home';
import SolarPanelList from './components/SolarPanelList';
import SolarPanelForm from './components/SolarPanelForm';
import NotFound from './components/NotFound';
import Login from './components/Login';
import AuthContext from './AuthContext';
import MySolarPanelList from './components/MySolarPanelList';
import Register from './components/Register';

const LOCAL_STORAGE_TOKEN_KEY = 'solarFarmToken';

function App() {
  // "null" means that we don't have a logged in user
  // anything other than null, means we have a logged in user
  const [user, setUser] = useState(null);
  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    if (token) {
      login(token);
    }
    setRestoreLoginAttemptCompleted(true);
  }, []);

  const login = (token) => {
    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

    const { sub: username, authorities, appUserId, phoneNumber } = jwt_decode(token);

    const roles = authorities.split(',');

    // create our user object
    const userToLogin = {
      appUserId,
      username,
      roles,
      phoneNumber,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    console.log(userToLogin);

    // update the global user state variable
    setUser(userToLogin);
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  };

  const auth = {
    user,
    login,
    logout
  };

  // If we haven't attempted to restore the login yet...
  // then don't render the App component.
  if (!restoreLoginAttemptCompleted) {
    return null;
  }

  return (
    <AuthContext.Provider value={auth}>

      <Router>

        <Header />

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
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          {/* <Route path="/solarpanels/add">
          <SolarPanelForm />
        </Route>
        <Route path="/solarpanels/edit/:id">
          <SolarPanelForm />
        </Route> */}
          <Route path={['/solarpanels/add', '/solarpanels/edit/:id']}>
            {auth.user ? (
              <SolarPanelForm />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/mysolarpanels">
            {auth.user ? (
              <MySolarPanelList />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/solarpanels">
            <SolarPanelList />
          </Route>
          <Route>
            <NotFound />
          </Route>
        </Switch>

      </Router>

    </AuthContext.Provider>
  );
}

export default App;
