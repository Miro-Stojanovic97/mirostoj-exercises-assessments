import { useState, useEffect } from 'react';
import AgentTable from './AgentTable';
import FormContainer from './FormContainer';
import Errors from './Errors';
import {useHistory, useParams} from 'react-router-dom';


function EditAgent() {

  let {agentId} = useParams();

  const DEFAULT_AGENT = {
    firstName: '',
    middleName: '',
    lastName: '',
    dob: '',
    heightInInches: ''
  };

  const [agents, setAgents] = useState([]);
  const [currentAgent, setCurrentAgent] = DEFAULT_AGENT;
  const [editing, setEditing] = useState(false);
  const [errors, setErrors] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/agent');
        const data = await response.json();
        setAgents(data);
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, []);

  const editAgent = (updateAgent) => {
    setErrors([])
    setCurrentAgent({
      agentId: updateAgent.agentId,
      firstName: updateAgent.firstName,
      middleName: updateAgent.middleName,
      lastName: updateAgent.lastName,
      dob: updateAgent.dob,
      heightInInches: updateAgent.heightInInches
    });
    setEditing(true)
  }

  const updateAgent = async (updateAgent) => {
    
    const init = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(updateAgent)
    }

    const response = await fetch(`http://localhost:8080/api/agent/${updateAgent.agentId}`, init);
    console.log(response.status)
    if (response.status === 204) {
      const newAgents = [...agents]
      const agentIndexToUpdate = agents.findIndex(agent => agent.agentId === updateAgent.agentId)
      newAgents[agentIndexToUpdate] = updateAgent;
      setAgents(newAgents);
      setCurrentAgent(DEFAULT_AGENT);
      setEditing(true);

    } else if (response.status === 400) {
      const data = await response.json();
      setErrors(data);

    } else if (response.status === 404) {
      return Promise.reject("Response is 404.")

    } else {
      return Promise.reject("Something went wrong.")
    }
  }

  return (
    <>
      <Errors errors={errors} />
      <FormContainer updateAgent={updateAgent} setEditing={setEditing} editing={editing} currentAgent={currentAgent} setCurrentAgent={setCurrentAgent} setErrors={setErrors} />
      <AgentTable agents={agents} editAgent={editAgent} />
    </>
  )
}

export default EditAgent;