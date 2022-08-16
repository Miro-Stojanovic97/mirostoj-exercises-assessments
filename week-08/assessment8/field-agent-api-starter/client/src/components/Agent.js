import {useHistory} from 'react-router-dom';

function Agent({editAgent, deleteAgent, agent}) {

    const handleDelete = (agent) => {
        const windowPrompt =`
        Are you sure you want to delete 
        Agent ID: ${agent.agentId}
        First Name: ${agent.firstName}
        Last Name: ${agent.lastName}
        `
        if(window.confirm(windowPrompt)) {
            deleteAgent(agent.agentId)
            navigate("/form");
        }
    }

    const navigate = useHistory();

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
                <button className="btn btn-danger btn-sm ms-3" onClick={() => handleDelete(agent)}>Delete</button>
            </td>
        </tr>
    )
}

export default Agent;