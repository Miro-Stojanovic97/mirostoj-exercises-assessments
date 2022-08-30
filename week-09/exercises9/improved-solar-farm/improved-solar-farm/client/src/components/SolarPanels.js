import { useEffect, useState } from 'react';

const SOLAR_PANEL_DEFAULT = {
  section: '',
  row: 0,
  column: 0,
  yearInstalled: 0,
  material: 'POLY_SI',
  tracking: false
};

function SolarPanels() {
  // Define our state variables.
  // We use destructuring to get the individual values that are returned from the useState function call.
  const [solarPanels, setSolarPanels] = useState([]);
  const [solarPanel, setSolarPanel] = useState(SOLAR_PANEL_DEFAULT);
  const [editSolarPanelId, setEditSolarPanelId] = useState(0);
  const [currentView, setCurrentView] = useState('List'); // Add, Edit
  const [errors, setErrors] = useState([]);

  // // useState returns an array with two elements... the first elements is the "variable" itself (i.e. the value) and the second element is a "setter"
  // const solarPanelsArray = useState(SOLAR_PANELS_DATA);
  // const solarPanels = solarPanelsArray[0];
  // const setSolarPanels = solarPanelsArray[1];

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

  const handleEditPanel = (solarPanelId) => {
    // Update the solarPanelId state variable to the solarPanelId that we need to edit.
    setEditSolarPanelId(solarPanelId);

    // Find the panel in the array of panels for the solarPanelId that we need to edit.
    const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

    // Create a copy of the solar panel to edit.
    const editSolarPanel = { ...solarPanel };

    // Update the solarPanel state variable with the solar panel object that we need to edit.
    setSolarPanel(editSolarPanel);

    // Update the current view to display the form.
    setCurrentView('Edit');
  };

  const handleDeletePanel = (solarPanelId) => {
    const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

    if (window.confirm(`Delete solar panel ${solarPanel.section}-${solarPanel.row}-${solarPanel.column}?`)) {
      const init = {
        method: 'DELETE'
      };

      fetch(`http://localhost:8080/api/solarpanel/${solarPanelId}`, init)
        .then(response => {
          if (response.status === 204) {
            // create a copy of the solar panels array
            // remove the solar panel that we need to delete
            const newSolarPanels = solarPanels.filter(solarPanel => solarPanel.id !== solarPanelId);

            // update the solar panels state variable
            setSolarPanels(newSolarPanels);

            resetState();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .catch(console.log);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (editSolarPanelId === 0) {
      addSolarPanel();
    } else {
      updateSolarPanel();
    }
  };

  const addSolarPanel = () => {
    const init = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
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

          // create a copy of the solar panels array
          const newSolarPanels = [...solarPanels];

          // add the new solar panel
          newSolarPanels.push(data);

          // This is an option to the previous two statements.
          // const newSolarPanels = [...solarPanels, solarPanel];

          // update the solar panels state variable
          setSolarPanels(newSolarPanels);

          resetState();
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
    // assign an ID
    solarPanel.id = editSolarPanelId;

    const init = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(solarPanel)
    };
  
    fetch(`http://localhost:8080/api/solarpanel/${editSolarPanelId}`, init)
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
          // create a copy of the solar panels array
          const newSolarPanels = [...solarPanels];

          // we need to determine the index of the solar panel that we are editing
          const indexToUpdate = newSolarPanels.findIndex(solarPanel => solarPanel.id === editSolarPanelId);

          // we need to update the solar panel at that index
          newSolarPanels[indexToUpdate] = solarPanel;

          // update the solar panels state variable
          setSolarPanels(newSolarPanels);

          resetState();
        } else {
          setErrors(data);
        }
      })
      .catch(console.log);
  };

  const resetState = () => {
    setSolarPanel(SOLAR_PANEL_DEFAULT);
    setEditSolarPanelId(0);
    setCurrentView('List');
    setErrors([]);
  };

  return (
    <>

      {(currentView === 'Add' || currentView === 'Edit') && (
        <>
          <h2 className="mb-4">{editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}</h2>

          {errors.length > 0 && (
            <div className="alert alert-danger">
              <p>The following errors were found:</p>
              <ul>
                {errors.map(error => (
                  <li key={error}>{error}</li>
                ))}
              </ul>
            </div>
          )}

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
                <i className="bi bi-file-earmark-check"></i> {editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}
              </button>
              <button className="btn btn-warning" type="button" onClick={resetState}>
                <i className="bi bi-stoplights"></i> Cancel
              </button>
            </div>
          </form>
        </>
      )}

      {currentView === 'List' && (
        <>
          <h2 className="mb-4">Solar Panels</h2>
          <button className="btn btn-primary my-4" onClick={() => setCurrentView('Add')}>
            <i className="bi bi-plus-circle"></i> Add Solar Panel
          </button>
          <table className="table table-striped table-hover table-sm">
            <thead className="thead-dark">
              <tr>
                <th>Section</th>
                <th>Row-Column</th>
                <th>Year Installed</th>
                <th>Material</th>
                <th>Is Tracking?</th>
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
                  <td>
                    <div className="float-right mr-2">
                      <button className="btn btn-primary btn-sm mr-2" onClick={() => handleEditPanel(solarPanel.id)}>
                        <i className="bi bi-pencil-square"></i> Edit
                      </button>
                      <button className="btn btn-danger btn-sm" onClick={() => handleDeletePanel(solarPanel.id)}>
                        <i className="bi bi-trash"></i> Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}

    </>
  );
}

export default SolarPanels;
