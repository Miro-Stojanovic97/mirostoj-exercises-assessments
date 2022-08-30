
## React Security Walk Through

### 1. Login Component

* Add a `Login` component and an accompanying `/login` route to your React project
* Prompt the user for their username and password
* Redirect the user to the "Home" page (i.e. `/`) after they submit the form

---

```js
import React, { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';

import Errors from './Errors';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]);

  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault();

    // TODO Call API to authenticate and get token.

    history.push('/');
  };

  return (
    <div>
      <h2>Login</h2>
      <Errors errors={errors} />
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input type="text" onChange={(event) => setUsername(event.target.value)} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" onChange={(event) => setPassword(event.target.value)} />
        </div>
        <div>
          <button type="submit">Login</button>
          <Link to="/register">I don't have an account</Link>
        </div>
      </form>
    </div>
  );
}
```

```js
function Errors({ errors }) {
  if (!errors || errors.length === 0) {
    return null;
  }

  return (
    <div className="alert alert-danger">
      <p>The following errors were found:</p>
      <ul>
        {errors.map(error => (
          <li key={error}>{error}</li>
        ))}
      </ul>
    </div>
  );
}

export default Errors;
```

### 2. Navbar Component

* Add a `Navbar` component to your React project (if it's not already defined)
* Include links to the "Home", "Solar Panels", "About", and "Contact" pages
* Within the component, define a `user` variable and initialize it to `null`
* If `user` is `null`, then display links to the "Login" and "Register" pages
* If `user` is not `null`, then display their username and a "Logout" button

---

```js
import { Link } from 'react-router-dom';

export default function NavBar() {
  const user = null;

  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/logentries">Log Entries</Link>
        </li>
        <li>
          <Link to="/profile">Profile</Link>
        </li>
        {!user && (
          <>
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/register">Register</Link>
            </li>
          </>
        )}
      </ul>
      {user && (
        <div>
          <p>Hello {user.username}!</p>
          <button>Logout</button>
        </div>
      )}
    </nav>
  );
}
```

### 3. Global State and Props

* Add a global `user` state property to the `App` component
* Define `login()` and `logout()` functions that update the `user` state property
* Pass an `auth` object literal containing `user`, `login`, and `logout` to the `Login` and `NavBar` components
* Update the `Login` and `NavBar` components to call the `login` and `logout` methods (respectively)

---

#### Review prop drilling

* Introduce a Header component
* Show how we need to pass the `auth` prop through the Header component... this is "prop drilling"

### 4. Protecting Routes

* Use conditional rendering to protect all of the solar panel related routes (`/solarpanels/add`, `/solarpanels/edit/:id`, and `/solarpanels/delete/:id` if defined):

```js
<Route path={['/solarpanels/add','/solarpanels/edit/:id']}>
  {auth.user ? (
    <SolarPanelForm />
  ) : (
    <Redirect to="/login" />
  )}
</Route>
```

> **You now have a semi-working solution!** The "Solar Panels" page is protected from unauthenticated users. You can login (albeit without actually authenticating the user using the API), see the user's status in the nav bar updated to reflect their login status, view the "Solar Panels" page (though the request to API to retrieve the solar panels will fail), and logout.

> **Next you'll refactor your code to use the Context API.**

---

### 5. Context API

* Leverage the Context API to manage global state
* Create a context object in its own module (so it can be imported into any module that needs access to the global state)

---

```js
import React from 'react';

const AuthContext = React.createContext();

export default AuthContext;
```

Now that you have a context, you need to update the `App` component so that the context can provide its `value` to any component that needs access to the global state:

* Import `AuthContext`
* Wrap `Router` in `AuthContext.Provider`
* Set the `AuthContext.Provider` component's `value` property to the `auth` object
* Remove `auth` props from all other components

Now within individual components, you can use the `useContext` Hook to listening for changes to the global state.

```js
import AuthContext from './AuthContext';

// snip!

function App() {

  // snip!
  
  return (
    <AuthContext.Provider value={auth}>
      <Router>
        <div>
          <h1>Calorie Tracker</h1>

          <NavBar />

          <hr />

          <Switch>
           {/* snip! */}
          </Switch>
        </div>
      </Router>
    </AuthContext.Provider>
  );
}
```

```js
import React, { useContext } from 'react';
import { Link } from 'react-router-dom';

import AuthContext from './AuthContext';

export default function NavBar() {
  const auth = useContext(AuthContext);

  // snip!
}
```

### 6. Getting a Token

- Update the `Login` component to use the secured Solar Farm API to authenticate the user
- `POST` the `username` and `password` values to the API's `/authenticate` endpoint
- On a successful response (`200 OK`), get the JWT token from the response body and pass it to the `auth.login()` method
- Redirect the user to the default route (`/`)
- On an unsuccessful response (`403 Forbidden`) display a "Login failed" message

---

```js
const authAttempt = {
  username,
  password
};

const init = {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(authAttempt)
};

fetch('http://localhost:8080/api/authenticate', init);
  .then(response => {
    if (response.status === 200) {
      return response.json();
    } else if (response.status === 403) {
      return null;
    } else {
      return Promise.reject(`Unexpected status code: ${response.status}`);
    }
  })
  .then(data => {
    if (data) {
      // {
      //   "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjYWxvcmllLXRyYWNrZXIiLCJzdWIiOiJzbWFzaGRldjUiLCJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsImV4cCI6MTYwNTIzNDczNH0.nwWJtPYhD1WlZA9mGo4n5U0UQ3rEW_kulilO2dEg7jo"
      // }
      auth.login(data.jwt_token);
      history.push('/');
    } else {
      // we have error messages
      setErrors(['login failure']);
    }
  })
  .catch(console.log);
```

### 7. Parsing the Token (`jwt-decode`)

Install the `jwt-decode` npm package:

```
npm install jwt-decode
```

Then use it to decode the token within the `App` component's `login()` function. The encoded token from the server will look similar to this:

```javascript
{
  "iss": "solar-farm-api",
  "sub": "john@smith.com",
  "roles": "ADMIN",
  "exp": 1620495306
}
```

You could decode and destructure like this: `const { sub: username, roles } = jwt_decode(token);`

---

```js
const login = (token) => {
  // {
  //   "iss": "solar-farm-api",
  //   "sub": "sally@jones.com",
  //   "authorities": "ROLE_USER",
  //   "exp": 1605235902
  // }

  const { sub: username, authorities } = jwt_decode(token);

  // Split the authorities into an array of roles.
  const roles = authorities.split(',');

  const user = {
    username,
    roles,
    token,
    hasRole(role) {
      return this.roles.includes(role);
    }
  };

  console.log(user);

  setUser(user);

  return user;
};
```

### 8. Passing the Token when Making HTTP Requests

- Now you need to set the `Authorization` header on your Fetch calls
  - If you don't add the JWT token to the request, the server will return a response with a `403 Forbidden` HTTP status code
- Here's an example of what the raw HTTP request needs to look like:

```
POST http://localhost:8080/api/solarpanels HTTP/1.1
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0b2RvcyIsInN1YiI6InNtYXNoZGV2IiwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJleHAiOjE2MDUyMTE0NDV9.G7kZls2bnrCgZD5hZk0uWU7yziA-YWF3OphKdARCLnw

{
  "section": "The Ridge",
  "row": 202,
  "column": 201,
  "yearInstalled": 2000,
  "material": "MONO_SI",
  "tracking": true
}
```

And here's an example of an updated Fetch API call:

```js
const newSolarPanel = {
  section: 'The Ridge',
  row: 202,
  column: 201,
  yearInstalled: 2000,
  material: 'MONO_SI',
  tracking: true
};

const init = {
  method: 'POST', // GET by default
  headers: {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${auth.user.token}`, // NEW
  },
  body: JSON.stringify(newToDo),
};

// Use `await` or `then` and handle the errors based on the response's `status`, `error` and `error.message`.
fetch("http://localhost:8080/api/solarpanels", init);
```

---

### 9. Conditionally Render Buttons

* Anonymous users can view panels
* Adding and updating requires the user to login
* Deleting requires an admin

_As a strategy, we can hide actions from users if they don't apply given their current state..._

* Hide the "Add Solar Panel" button if no logged in user
* Hide the "Edit" buttons if no logged in user
* Hide the "Delete" buttons if the logged in user isn't an ROLE_ADMIN

## Stretch Goals

### Persisting the Login State

- Update the `App` component's `login()` function to persist the token to `localStorage`
- Update the `App` component's `logout()` function to remove the token from `localStorage`

---

```js
import { useEffect, useState } from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

// snip!

const TOKEN_KEY = 'user-api-token';

function App() {
  // is someone logged in?
  // null || an object
  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);

  // interacting with local storage is a side effect...
  useEffect(() => {
    const token = localStorage.getItem(TOKEN_KEY);

    if (token) {
      login(token);
    }

    setInitialized(true);
  }, []); 
  // the empty array for the dependency list...
  // this side effect will run only once when the component is loading

  const login = (token) => {
    console.log(token);

    // store the token away to persist the user's login
    localStorage.setItem(TOKEN_KEY, token);

    // example of token payload:

    // {
    //   "iss": "todos",
    //   "sub": "john@smith.com",
    //   "roles": "ADMIN",
    //   "exp": 1631829962
    // }

    // decode the token string into a JavaScript object
    const tokenObj = jwt_decode(token);
    console.log(tokenObj);

    // long form...
    // const username = tokenObj.sub;
    // const rolesString = tokenObj.roles;

    // short form using destructuring...
    const { sub: username, roles: rolesString } = jwt_decode(token);

    // Split the roles string into an array of roles.
    const roles = rolesString.split(',');

    // create the "user" object
    const user = {
      username,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    console.log(user);

    // update the user state
    setUser(user);

    return user;
  };

  const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
    setUser(null);
  };

  // collect all of the auth related stuff into a single object
  const auth = {
    user: user ? {...user} : null,
    login,
    logout
  };

  // prevent routing until we've prevented restoring the user's login state...
  if (!initialized) {
    return null;
  }

  return (
    // passing the auth object using the context's value property
    <AuthContext.Provider value={auth}>

      <Router>

        <Header />

        <Switch>
          {/* snip! */}
        </Switch>

      </Router>

    </AuthContext.Provider>
  );
}

export default App;
```

### Bootstrap Styles

Update the navbar and Login component with Bootstrap styles

### Register Component

- Add a `Register` component and an accompanying `/register` route to your React project
- The `Register` component is similar in form and function to the `Login` component
- The biggest difference is that you need to make two Fetch calls when the user submits the form
  - Use Fetch to create the account
  - If you get a `201` (i.e. "Success") then use Fetch to authenticate and get the token
  - After receiving the token from the server, pass the token to the `auth.login()` function to login the newly created user
