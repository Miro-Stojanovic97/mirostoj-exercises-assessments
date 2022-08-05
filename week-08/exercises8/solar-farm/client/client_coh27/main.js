// Global Variables
const tableBody = document.getElementById('solar-panels-table-body');
let solarPanels = [];

// Functions
const addToSolarPanelsArray = (data) => {
    data.forEach(da => solarPanels.push(da));
}

// editHandler
// deleteHandler

const createSPButtons = (spId) => {
    // If I need edit and delete a solar panel, need ID...
    // Create TD to hold buttons
    // Create 2 buttons, each one will need a unique identifier...

    // Add onclick functions to trigger fetch-DELETE and fetch-PUT requests
    // Add buttons to TD, then to TR, then page...

    let td = document.createElement('td');
    let editButton = document.createElement('button');
    let deleteButton = document.createElement('button');

    editButton.innerText = "Edit";
    deleteButton.innerText = "Delete";

    editButton.setAttribute('class', 'btn btn-sm btn-primary me-2');
    deleteButton.setAttribute('class', 'btn btn-sm btn-danger');

    editButton.onclick = editHandler;
    deleteButton.onclick = deleteHandler;

    editButton.id = "edit" + spId;
    deleteButton.id = "delete" + spId;

    td.appendChild(editButton);
    td.appendChild(deleteButton);

    return td;
}

const deserializeSolarPanels = () => {
    // Create <tr> tags for each object
    // Create <td> tags for each object's data values
    // Insert data into <td>
    // Insert <td> into the <tr>
    // Insert the <tr> into #solar-panels-table-body
    
    solarPanels.forEach(sp => {
        let tr = document.createElement('tr');
        let spValues = Object.values(sp);
        spValues.forEach(val => {
            let td = document.createElement('td');
            td.innerText = val;
            tr.appendChild(td);
        })
        let spButtons = createSPButtons(sp.id);
        tr.appendChild(spButtons);
        // createSPButtons(spId)...?
        tableBody.appendChild(tr);

    })
}

const fetchSolarPanels = () => {   
    fetch('http://localhost:8080/api/solarpanel')
    .then((response) => response.json())
    .then((data) => addToSolarPanelsArray(data))
    .then(() => deserializeSolarPanels());
}

const newSP = {
    section: 'Super Duper Section',
    row: 2,
    column: 2,
    yearInstalled: 2015,
    material: 'CIGS',
    tracking: true
}

async function postSolarPanel(solarPanelObj) {
    const response = await fetch('http://localhost:8080/api/solarpanel/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanelObj)
    });

    return response.json();
}

// Events & Calls
Window.onload = fetchSolarPanels();

// postSolarPanel(newSP)
// .then((data) => console.log(data));