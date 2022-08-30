import { useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import UserContext from '../contexts/UserContext';

function Navbar() {

  const auth = useContext(UserContext);

  const history = useHistory();

  const handleLogout = () => {
    auth.onLogout();
    history.push('/');
  };

  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/solarpanels">Solar Panels</Link></li>
        <li><Link to="/about">About</Link></li>
        <li><Link to="/contact">Contact</Link></li>
        {
          auth.user.username 
            ? <li>
              <span>{auth.user.username}</span>
              <button onClick={handleLogout}>Log out</button>
            </li>
            : <li><Link to="/login">Login</Link> </li>
        }
      </ul>
    </nav>
  );
}

export default Navbar;
