import { useState, useEffect } from 'react';
import AgentTable from './AgentTable';
import FormContainer from './FormContainer';
import Errors from './Errors';

function Agents() {

  const DEFAULT_AGENT = {
    firstName: '',
    middleName: '',
    lastName: '',
    dob: '',
    heightInInches: ''
  };

  const [agents, setAgents] = useState([]);
  const [currentAgent, setCurrentAgent] = useState(DEFAULT_AGENT);
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

  const addAgent = async (agent) => {
    const newAgent = {
      "firstName": agent.firstName,
      "middleName": agent.middleName,
      "lastName": agent.lastName,
      "dob": agent.dob,
      "heightInInches": agent.heightInInches
    }

    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(newAgent)
    }

    const response = await fetch('http://localhost:8080/api/agent', init);
    if (response.status === 201 || response.status === 400) {
      const data = await response.json();
      if (data.agentId) {
        setAgents([...agents, data])
        setCurrentAgent(DEFAULT_AGENT);
        setErrors([]);
      } else {
        setErrors(data);
      }
    } else {
      throw new Error("Shoot! Something went wrong!");
    }
  };

  const deleteAgent = async (agentId) => {
    const init = {
      method: "DELETE"
    };
    const response = await fetch(`http://localhost:8080/api/agent/${agentId}`, init);
    if (response.status === 204) {
      const newAgents = agents.filter(agent => agent.agentId !== agentId);
      setAgents(newAgents);
    } else if (response.status === 404) {
      return Promise.reject("Response is 404.")
    } else {
      const data = await response.json();
      setErrors(Object.values(data))
      return Promise.reject("Something went wrong.")
    }
  }

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
      setEditing(false);

    } else if (response.status === 400) {
      const data = await response.json();
      setErrors(data);

    } else if (response.status === 404) {
      // todo: response
      return Promise.reject("Response is 404.")

    } else {
      // todo: response
      return Promise.reject("Something went wrong.")
    }
  }

  return (
    <>
      <Errors errors={errors} />
      <FormContainer updateAgent={updateAgent} setEditing={setEditing} editing={editing} currentAgent={currentAgent} setCurrentAgent={setCurrentAgent} addAgent={addAgent} setErrors={setErrors} />
      {/* <AgentTable agents={agents} editAgent={editAgent} deleteAgent={deleteAgent} /> */}
    </>
  )
}

export default Agents;