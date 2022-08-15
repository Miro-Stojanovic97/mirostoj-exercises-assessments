import useState from 'react';

function Form({ editing, resetForm, agent, setCurrentAgent, addAgent, handleSubmit, setErrors }) {

  const onChangeHandler = (event) => {
    setErrors([]);
    const updatedAgent = {...agent};
    updatedAgent[event.target.name] = event.target.value;
    setCurrentAgent(updatedAgent);
  };


  return (
    <form onSubmit={handleSubmit}>
      <div className="row my-3">
        <div className="col-sm">
          <label htmlFor="firstName" className="form-label">First Name:</label>
          <input id="firstName" name="firstName" type="text" className="form-control"
            onChange={onChangeHandler}
            value={agent.firstName} required />
        </div>
        <div className="col-sm">
          <label htmlFor="middleName" className="form-label">Middle Name:</label>
          <input id="middleName" name="middleName" type="text" className="form-control"
            onChange={onChangeHandler}
            value={agent.middleName} />
        </div>
        <div className="col-sm">
          <label htmlFor="lastName" className="form-label">Last Name:</label>
          <input id="lastName" name="lastName" type="text" className="form-control"
            onChange={onChangeHandler}
            value={agent.lastName} required />
        </div>
      </div>
      <div className="row my-3">
        <div className="col-sm">
          <label htmlFor="Date of Birth" className="form-label">Date of Birth:</label>
          <input id="dob" name="dob" type="date" className="form-control"
            onChange={onChangeHandler}
            value={agent.dob} required />
        </div>
        <div className="col-sm">
          <label htmlFor="heightInInches" className="form-label">Height in Inches:</label>
          <input id="heightInInches" name="heightInInches" type="number" className="form-control"
            onChange={onChangeHandler}
            value={agent.heightInInches}
            min="36" max="96" required />
        </div>
      </div>
      {editing ? (
        <div>
          <div className="my-3">
            <button className="btn btn-success" type="submit">Update Agent</button>
            <button
              onClick={resetForm}
              className="btn btn-secondary mx-3">Cancel</button>
          </div>
        </div>
      ) : (
          <div>
            <div className="my-3">
              <button className="btn btn-success" type="submit">Add Agent</button>
            </div>
          </div>
        )}
    </form>
  )
}

export default Form;