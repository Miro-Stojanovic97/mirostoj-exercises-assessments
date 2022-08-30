import { useEffect, useState, useContext } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';

import AuthContext from '../AuthContext';
import Errors from './Errors';

const SOLAR_PANEL_DEFAULT = {
  section: '',
  row: 0,
  column: 0,
  yearInstalled: 0,
  material: 'POLY_SI',
  tracking: false
};

function SolarPanelForm() {
  const [solarPanel, setSolarPanel] = useState(SOLAR_PANEL_DEFAULT);
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  // Not using destructuring...
  // const params = useParams();
  // const id = params.id;

  // Using destructuring...
  const { id } = useParams();

  useEffect(() => {
    // Make sure that we have an "id" value...
    if (id) {
      fetch(`http://localhost:8080/api/solarpanel/${id}`)
        .then(response => {
          if (response.status === 200) {
            return response.json();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .then(data => setSolarPanel(data))
        .catch(console.log);
    }
  }, [id]); // Hey React... please call my arrow function every time the "id" route parameter changes value

  const handleChange = (event) => {
    // Make a copy of the object.
    const newSolarPanel = { ...solarPanel };

    // Update the value of the property that just changed.
    // We can "index" into the object using square brackets (just like we can do with arrays).
    if (event.target.type === 'checkbox') {
      newSolarPanel[event.target.name] = event.target.checked;
    } else {
      newSolarPanel[event.target.name] = event.target.value;
    }

    setSolarPanel(newSolarPanel);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (id) {
      updateSolarPanel();
    } else {
      addSolarPanel();
    }
  };

  const addSolarPanel = () => {
    const init = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${auth.user.token}`
      },
      body: JSON.stringify(solarPanel)
    };

    fetch('http://localhost:8080/api/solarpanel', init)
      .then(response => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then(data => {
        if (data.id) {
          /*

          On the happy path, "data" is an object that looks this:

          {
            "id": 30,
            "section": "The Ridge",
            "row": 202,
            "column": 201,
            "yearInstalled": 2000,
            "material": "MONO_SI",
            "tracking": true
          }

          */

          // Send the user back to the list route.
          history.push('/solarpanels');
        } else {
          /*

          On the unhappy path, "data" is an array that looks this:

          [
            "SolarPanel `section` is required.",
            "SolarPanel `row` must be a positive number less than or equal to 250.",
            "SolarPanel `column` must be a positive number less than or equal to 250.",
            "SolarPanel `material` is required."
          ]

          */

          setErrors(data);
        }
      })
      .catch(console.log);
  };

  const updateSolarPanel = () => {
    // assign an ID (this is probably needed anymore)
    solarPanel.id = id;

    const init = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${auth.user.token}`
      },
      body: JSON.stringify(solarPanel)
    };
  
    fetch(`http://localhost:8080/api/solarpanel/${id}`, init)
      .then(response => {
        if (response.status === 204) {
          return null;
        } else if (response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then(data => {
        if (!data) {
          // Send the user back to the list route.
          history.push('/solarpanels');
        } else {
          setErrors(data);
        }
      })
      .catch(console.log);
  };

  return (
    <>
      <h2 className="mb-4">{id ? 'Update Solar Panel' : 'Add Solar Panel'}</h2>

      <Errors errors={errors} />

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="section">Section:</label>
          <input id="section" name="section" type="text" className="form-control"
            value={solarPanel.section} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="row">Row:</label>
          <input id="row" name="row" type="number" className="form-control"
            value={solarPanel.row} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="column">Column:</label>
          <input id="column" name="column" type="number" className="form-control"
            value={solarPanel.column} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="yearInstalled">Year Installed:</label>
          <input id="yearInstalled" name="yearInstalled" type="number" className="form-control"
            value={solarPanel.yearInstalled} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="material">Material:</label>
          <select id="material" name="material" className="form-control"
            value={solarPanel.material} onChange={handleChange}>
            <option>POLY_SI</option>
            <option>MONO_SI</option>
            <option>A_SI</option>
            <option>CD_TE</option>
            <option>CIGS</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="tracking">Is Tracking?
            <input id="tracking" name="tracking" type="checkbox"
              checked={solarPanel.tracking} onChange={handleChange} />
          </label>
        </div>
        <div className="mt-4">
          <button className="btn btn-success mr-2" type="submit">
            <i className="bi bi-file-earmark-check"></i> {id ? 'Update Solar Panel' : 'Add Solar Panel'}
          </button>
          <Link className="btn btn-warning" to="/solarpanels">
            <i className="bi bi-stoplights"></i> Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default SolarPanelForm;
