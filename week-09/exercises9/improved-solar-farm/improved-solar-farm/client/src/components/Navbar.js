import { useContext } from 'react';
import { Link } from 'react-router-dom';

import AuthContext from '../AuthContext';

function Navbar() {
  const auth = useContext(AuthContext);

  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/mysolarpanels">My Solar Panels</Link></li>
        <li><Link to="/solarpanels">Solar Panels</Link></li>
        <li><Link to="/about">About</Link></li>
        <li><Link to="/contact">Contact</Link></li>
        {!auth.user && (
          <>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/register">Register</Link></li>
          </>
        )}
      </ul>
      {auth.user && (
        <div>
          Welcome {auth.user.username}!
          <button onClick={() => auth.logout()}>Logout</button>
        </div>
      )}
    </nav>
  );
}

export default Navbar;
