import { useEffect } from "react";
import SolarPanel from "./SolarPanel";

function SolarPanels({  solarPanels, 
                        setSolarPanels, 
                        setShowForm, 
                        showForm,  
                        setCurrentSolarPanel,
                        showMessages
                    }) {

    useEffect(() => {
        getSolarPanels()
    }, []);

    const getSolarPanels = () => {
        fetch("http://localhost:8080/api/solarpanel")
        .then((response) => response.json())
        .then((data) => setSolarPanels(data))
        .catch((error) => showMessages(error.message))
    }

    const solarPanelFactory = () => {
        return solarPanels.map(sp => {
            return (<SolarPanel 
                key={sp.id + "-key"} 
                panel={sp}
                setShowForm={setShowForm}
                showForm={showForm}
                setCurrentSolarPanel={setCurrentSolarPanel}
                solarPanels={solarPanels}
                setSolarPanels={setSolarPanels}
                showMessages={showMessages}
            />)
        })
    }

    return (
        <>
            <h3>List of Solar Panels:</h3>
            <table className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Section</th>
                        <th scope="col">Row</th>
                        <th scope="col">Column</th>
                        <th scope="col">Year Installed</th>
                        <th scope="col">Material</th>
                        <th scope="col">Tracking</th>
                        <th scope="col">Controls</th>
                    </tr>
                </thead>
                <tbody>
                    {solarPanelFactory()}
                </tbody>
            </table>
        </>
    )
}

export default SolarPanels;