import { useState, useCallback } from 'react';
import Form from './Form';

function FormContainer({ editing, updateAgent, setEditing, currentAgent, setCurrentAgent, addAgent, setErrors }) {

  const DEFAULT_AGENT = {
    firstName: '',
    middleName: '',
    lastName: '',
    dob: '',
    heightInInches: ''
  };


  const handleSubmit = editing ? (event) => {
    event.preventDefault();
    updateAgent( currentAgent );
  } :
    (event) => {
      event.preventDefault();
      addAgent( currentAgent );
    };


  const resetForm = useCallback(() => {
    setEditing(false);
    setCurrentAgent(DEFAULT_AGENT);
  })

  const formTitle = editing ? "Edit Agent" : "Add Agent";
  return (
    <>
      <h2>{formTitle}</h2>
      <Form editing={editing} handleSubmit={handleSubmit} setCurrentAgent={setCurrentAgent} resetForm={resetForm} agent={currentAgent} addAgent={addAgent} handleSubmit={handleSubmit} setErrors={setErrors} />
    </>
  )
}

export default FormContainer;