import { useNavigate } from 'react-router-dom';

function SolarPanel({   panel, 
                        setCurrentSolarPanel, 
                        solarPanels, 
                        setSolarPanels,
                        showMessages
                    }) {

    const navigate = useNavigate();

    const editFormHandler = () => {
        setCurrentSolarPanel(panel);
        navigate("/form");
    } 

    const filterOutDeletedPanel = () => {
        const filteredPanelsArray = solarPanels.filter(sp => sp.id !== panel.id)
        setSolarPanels(filteredPanelsArray);
    }

    const deletePanel = () => {
        fetch("http://localhost:8080/api/solarpanel/" + panel.id, {
            method: 'DELETE'
        })
        .then(() => filterOutDeletedPanel())
        .then(() => showMessages(`Solar Panel ${panel.section}: ${panel.row}, ${panel.column} has been deleted!`))
        .catch(error => showMessages(error.message));
    }

    return (
        <>
            <tr>
                <td>{panel.section}</td>
                <td>{panel.row}</td>
                <td>{panel.column}</td>
                <td>{panel.yearInstalled}</td>
                <td>{panel.material}</td>
                <td>{panel.tracking ? "Yes" : "No"}</td>
                <td>
                    <button onClick={editFormHandler} className="btn btn-info btn-sm me-2">Edit</button>
                    <button onClick={deletePanel} className="btn btn-danger btn-sm">Delete</button>
                </td>
            </tr>
        </>
    )
}

export default SolarPanel;