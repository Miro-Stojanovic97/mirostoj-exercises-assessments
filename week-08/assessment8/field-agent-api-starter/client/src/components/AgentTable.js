import Agent from './Agent';

function AgentTable({ agents, editAgent, deleteAgent }) {

  return (
    <>
      <hr></hr>
      <h2>View Agents</h2>
      <table className="table table-striped table-light table-hover">
        <thead>
          <tr>
            <th>Agent ID</th>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>DOB</th>
            <th>Height (in)</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          {agents.map(agent => <Agent key={agent.agentId} agent={agent} editAgent={editAgent} deleteAgent={deleteAgent} />)}
        </tbody>
      </table>
    </>
  )
}

export default AgentTable;