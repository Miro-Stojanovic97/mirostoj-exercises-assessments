import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

function Form({ solarPanels, 
                setSolarPanels, 
                currentSolarPanel, 
                showMessages
            }) {

    const navigate = useNavigate();

    const currentDate = new Date();

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        mode: "onChange"
    });

    const findAndReplacePanel = (currentPanel) => {
        const foundPanelsArray = solarPanels.filter(panel => panel.id !== currentPanel.id);
        setSolarPanels([...foundPanelsArray, currentPanel]);
    }

    const addEditPanelValuesToForm = () => {
        setValue("section", currentSolarPanel.section);
        setValue("row", currentSolarPanel.row);
        setValue("column", currentSolarPanel.column);
        setValue("yearInstalled", currentSolarPanel.yearInstalled);
        setValue("material", currentSolarPanel.material);
        setValue("tracking", currentSolarPanel.tracking);
    }

    const responseToMessage = (response) => {
        const firstNum = response.status.toString().charAt(0);

        switch(firstNum) {
            case "2": 
                showMessages(`Solar Panel has been updated!`);
                break;
            case "4": 
                showMessages("400 Error: Something went wrong!");
                break;
            case "5":
                showMessages("500 Error: Server not responding!");
                break;
        }

        if (response.status == 201) {
            return response.json();
        } else {
            return response;
        }
    }

    useEffect(() => {
        addEditPanelValuesToForm();
    }, []);

    const onSubmit = (solarPanelObj) => {
        if (currentSolarPanel.id) {
            const editSolarPanelObj = solarPanelObj;
            editSolarPanelObj.id = currentSolarPanel.id;

            fetch("http://localhost:8080/api/solarpanel/" + currentSolarPanel.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(editSolarPanelObj)
            })
            .then((response) => responseToMessage(response))
            .then(() => findAndReplacePanel(editSolarPanelObj))
            .then(() => navigate("/solarpanels"))
            .catch((error) => showMessages(error.message));
        }
        else {
            fetch("http://localhost:8080/api/solarpanel", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(solarPanelObj)
            })
            .then(response => responseToMessage(response))
            .then(newSolarPanel => setSolarPanels([...solarPanels, newSolarPanel]))
            .catch((error) => showMessages(error.message));
        }
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)} id="sp-form">
            <div className="mb-3">
                <label htmlFor="sp-section" className="form-label">Section</label>
                <input 
                    type="text" 
                    className="form-control" 
                    id="sp-section" 
                    {...register("section", { required: "Section value is required" })} 
                />
                <p className="form-error-message">{errors.section?.message}</p>
            </div>
            <div className="mb-3">
                <label htmlFor="sp-row" className="form-label">Row</label>
                <input 
                    type="number" 
                    className="form-control" 
                    id="sp-row" 
                    {...register("row", {   required: "Row value is required.", 
                                            min: {value: 1, message: "Must be greater than 1."},
                                            max: {value: 250, message: "Must be no greater than 250. "}
                })} />
                <p className="form-error-message">{errors.row?.message}</p>
            </div>
            <div className="mb-3">
                <label htmlFor="sp-column" className="form-label">Column</label>
                <input 
                    type="number" 
                    className="form-control" 
                    id="sp-column" 
                    {...register("column", {    required: "Column value is required.", 
                                                min: {value: 1, message: "Must be greater than 1."},
                                                max: {value: 250, message: "Must be no greater than 250. "}
                })} />
                <p className="form-error-message">{errors.column?.message}</p>
            </div>
            <div className="mb-3">
                <label htmlFor="sp-year" className="form-label">Year Installed</label>
                <input 
                    type="number" 
                    min="1883" 
                    max={currentDate.getFullYear()} 
                    className="form-control" 
                    id="sp-year" {...register("yearInstalled", {    required: "Year required (YYYY)",
                                                                    min: {value: 1883, message: "Must be no earlier than 1883."},
                                                                    max: {value: currentDate.getFullYear(), message: "Must be no later than the current year."}
                })} />
                <p className="form-error-message">{errors.yearInstalled?.message}</p>
            </div>
            <div className="mb-3">
                <label htmlFor="sp-material" className="form-label">Material</label>
                <select className="form-select" aria-label="Material Selection" id="sp-material" {...register("material", {required: "Must select a material"})}>
                    <option>Select One</option>
                    <option value="POLY_SI">Multicrystalline Silicon</option>
                    <option value="MONO_SI">Monocrystalline Silicon</option>
                    <option value="A_SI">Amorphous Silicon</option>
                    <option value="CD_TE">Cadmium Telluride</option>
                    <option value="CIGS">Copper Indium Gallium Selenide</option>
                </select>
                <p className="form-error-message">{errors.material?.message}</p>
            </div>
            <div className="mb-3 form-check">
                <input type="checkbox" className="form-check-input" id="sp-tracking" {...register("tracking")} />
                <label className="form-check-label" htmlFor="sp-tracking">Tracking</label>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
        </form>
    )
}

export default Form;