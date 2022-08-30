import { useEffect, useState, useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';

import AuthContext from '../AuthContext';

function SolarPanelList() {
  const [solarPanels, setSolarPanels] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  useEffect(() => {
    fetch('http://localhost:8080/api/solarpanel')
      .then(response => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then(data => setSolarPanels(data))
      .catch(console.log);
  }, []); // An empty dependency array tells to run our side effect once when the component is initially loaded.    

  const handleDeletePanel = (solarPanelId) => {
    const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

    if (window.confirm(`Delete solar panel ${solarPanel.section}-${solarPanel.row}-${solarPanel.column}?`)) {
      const init = {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${auth.user.token}`
        },
      };

      fetch(`http://localhost:8080/api/solarpanel/${solarPanelId}`, init)
        .then(response => {
          if (response.status === 204) {
            // create a copy of the solar panels array
            // remove the solar panel that we need to delete
            const newSolarPanels = solarPanels.filter(solarPanel => solarPanel.id !== solarPanelId);

            // update the solar panels state variable
            setSolarPanels(newSolarPanels);
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .catch(console.log);
    }
  };

  return (
    <>
      <h2 className="mb-4">Solar Panels</h2>
      <button className="btn btn-primary my-4" onClick={() => history.push('/solarpanels/add')}>
        <i className="bi bi-plus-circle"></i> Add Solar Panel
      </button>
      {/* <Link className="btn btn-primary my-4" to="/solarpanels/add">
        <i className="bi bi-plus-circle"></i> Add Solar Panel
      </Link> */}
      <table className="table table-striped table-hover table-sm">
        <thead className="thead-dark">
          <tr>
            <th>Section</th>
            <th>Row-Column</th>
            <th>Year Installed</th>
            <th>Material</th>
            <th>Is Tracking?</th>
            <th>Username</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          {solarPanels.map(solarPanel => (
            <tr key={solarPanel.id}>
              <td>{solarPanel.section}</td>
              <td>{solarPanel.row}-{solarPanel.column}</td>
              <td>{solarPanel.yearInstalled}</td>
              <td>{solarPanel.material}</td>
              <td>{solarPanel.tracking ? 'Yes' : 'No'}</td>
              <td>{solarPanel.appUser.username}</td>
              <td>
                <div className="float-right mr-2">
                  {auth.user && auth.user.appUserId === solarPanel.appUser.appUserId && (
                    <Link className="btn btn-primary btn-sm mr-2" to={`/solarpanels/edit/${solarPanel.id}`}>
                      <i className="bi bi-pencil-square"></i> Edit
                    </Link>
                  )}
                  {auth.user && auth.user.hasRole('ROLE_ADMIN') && (
                    <button className="btn btn-danger btn-sm" onClick={() => handleDeletePanel(solarPanel.id)}>
                      <i className="bi bi-trash"></i> Delete
                    </button>
                  )}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default SolarPanelList;
