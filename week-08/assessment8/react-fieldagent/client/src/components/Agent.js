import { Link, useParams } from "react-router-dom";

function Agent({editAgent, deleteAgent, agent, useParams}) {
    const handleDelete = (agent) => {
        const windowPrompt =`
        Are you sure you want to delete 
        Agent ID: ${agent.agentId}
        First Name: ${agent.firstName}
        Last Name: ${agent.lastName}
        `
        if(window.confirm(windowPrompt)) {
            deleteAgent(agent.agentId)
        }
    }

    // let params = useParams();
    // let agentId = params;
    
    return (
        <tr>
            <td>{agent.agentId}</td>
            <td>{agent.firstName}</td>
            <td>{agent.middleName}</td>
            <td>{agent.lastName}</td>
            <td>{agent.dob}</td>
            <td>{agent.heightInInches}</td>
            <td>
                <button className="btn btn-primary btn-sm" onClick={() => editAgent(agent)}>Edit</button>
                {/* <Link to="/agents/edit/${agent.agentId}" className="btn btn-primary btn-sm">Edit</Link> */}
                <button className="btn btn-danger btn-sm" onClick={() => handleDelete(agent)}>Delete</button>
            </td>
        </tr>
    )
}

export default Agent;