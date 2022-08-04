// Global Variables
const tableBody = document.getElementById('solar-panels-table-body');
let solarPanels = [];

// Functions
const addToSolarPanelsArray = (data) => {
    data.forEach(da => solarPanels.push(da));
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
        spValues.shift();
        
        spValues.forEach(val => {
            let td = document.createElement('td');
            td.innerText = val;
            tr.appendChild(td);
        })
        
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
    row: 3,
    column: 3,
    yearInstalled: 2015,
    material: 'CIGS',
    tracking: true
}

async function postSolarPanel(solarPanel) {
    const response = await fetch('http://localhost:8080/api/solarpanel', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanel)
    });

    return response.json();
}

// Events & Calls
Window.onload = fetchSolarPanels();

// postSolarPanel(newSP)
// .then((data) => console.log(data));