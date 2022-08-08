// Global Variables

const url = "http://localhost:8080/api/agent/";
let fieldAgents;
let tempFirstName;
let tempMiddleName;
let tempLastName;
let tempHeight;
let tempDob;
let tempAgentId = null;

const fieldAgentCardContainer = document.getElementById('field-agent-cards');
const addAgentButton = document.getElementById('add-agent');
const agentForm = document.getElementById('agent-form');
const deleteForm = document.getElementById('delete-confirm');
const deleteYes = document.getElementById('yes');
const deleteNo = document.getElementById('no');

const firstNameInput = document.getElementById('first-name');
const middleNameInput = document.getElementById('middle-name');
const lastNameInput = document.getElementById('last-name');
const heightInput = document.getElementById('height');
const dobInput = document.getElementById('dob');



// Functions

const getFieldAgentsOnEvent = () => {
    fetch(url)
    .then(response => response.json())
    .then(data => fieldAgents = data)
    .then(() => fieldAgentCardFactory(fieldAgents));
}

const inputOnChangeHandler = (field) => {
    switch(field) {
        case "firstName":
            tempFirstName = firstNameInput.value;
            break;
        case "middleName":
            tempMiddleName = middleNameInput.value;
            break;
        case "lastName":
            tempLastName = lastNameInput.value;
            break;
        case "height":
            tempHeight = heightInput.value;
            break;
        case "dob":
            tempDob = dobInput.value;
            break;
        default:
            break;
    }
}

const addAgentButtonClickHandler = (event) => {
    event.preventDefault();
    
    tempFirstName = "";
    tempMiddleName = "";
    tempLastName = "";
    tempHeight = "";
    tempDob = "";
    tempAgentId = null;

    firstNameInput.value = "";
    middleNameInput.value = "";
    lastNameInput.value = "";
    heightInput.value = "";
    dobInput.value = "";

    agentForm.style.display = "block";
    
    let currentFieldAgent = null;

    tempFirstName = firstNameInput.value;
    tempMiddleName = middleNameInput.value;
    tempLastName = lastNameInput.value;
    tempHeight = heightInput.value;
    tempDob = dobInput.value;
}

const editButtonClickHandler = (event) => {
    const agentIdArray = event.target.id.split("-");
    tempAgentId = agentIdArray[1]; //creates agent id associated with button click id
    agentForm.style.display = "block";

    const currentFieldAgent = fieldAgents.find(agent => agent.agentId == tempAgentId);

    firstNameInput.value = currentFieldAgent.firstName;
    middleNameInput.value = currentFieldAgent.middleName;
    lastNameInput.value = currentFieldAgent.lastName;
    heightInput.value = currentFieldAgent.heightInInches;
    dobInput.value = currentFieldAgent.dob;

    tempFirstName = currentFieldAgent.firstName;
    tempMiddleName = currentFieldAgent.middleName;
    tempLastName = currentFieldAgent.lastName;
    tempHeight = currentFieldAgent.heightInInches;
    tempDob = currentFieldAgent.dob;
}

const deleteButtonClickHandler = (event) => {
    event.preventDefault();
    
    const agentIdArray = event.target.id.split("-");
    tempAgentId = agentIdArray[1]; //creates agent id associated with button click id
    deleteForm.style.display = "block";
    
}

const deleteYesConfirmHandler = (event) => {
    event.preventDefault();

    let deleteAgentObj = {};
    deleteAgentObj.agentId = tempAgentId;
    deleteAgentObj.firstName = tempFirstName;
    deleteAgentObj.middleName = tempMiddleName;
    deleteAgentObj.lastName = tempLastName;
    deleteAgentObj.heightInInches = tempHeight;
    deleteAgentObj.dob = tempDob;

    fetch(url + tempAgentId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }, 
        body: JSON.stringify(deleteAgentObj)
    })
    .then(response => console.log(response.status))
    .then(() => window.location.reload())
    .catch(error => console.error(error));
}

const deleteNoConfirmHandler = (event) => {
    event.preventDefault();

    deleteForm.style.display = "none";
}

const formSubmitHandler = (event) => {
    event.preventDefault();

    let newAgentObj = {};
    newAgentObj.agentId = tempAgentId;
    newAgentObj.firstName = tempFirstName;
    newAgentObj.middleName = tempMiddleName;
    newAgentObj.lastName = tempLastName;
    newAgentObj.heightInInches = tempHeight;
    newAgentObj.dob = tempDob;

    if(tempAgentId !== null) {
        fetch(url + tempAgentId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newAgentObj)   
        })
        .then(response => console.log(response.status))
        .then(() => window.location.reload())
        .catch(error => console.error(error));
    } else {
        // POST Fetch
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(newAgentObj)
        })
        .then(response => console.log(response.status))
        .then(() => window.location.reload())
        .catch(error => console.error(error));
    }
}

//creating agent cards
const fieldAgentCardFactory = (fieldAgentArray) => {
    fieldAgentArray.forEach(fieldAgentObj => {

        const cardCol = document.createElement('div');
        cardCol.setAttribute('class', 'col-3 pb-2 pe-1')

        const card = document.createElement('div');
        card.setAttribute('class', 'card');

        const cardBody = document.createElement('div');
        cardBody.setAttribute('class', 'card-body');

        const nameRow = document.createElement('div');
        nameRow.setAttribute('class', 'row');
        
        const nameCol = document.createElement('div');
        nameCol.setAttribute('class', 'col');

        const nameHeading = document.createElement('h3');
        nameHeading.innerText = fieldAgentObj.firstName + " " + fieldAgentObj.middleName + ". " + fieldAgentObj.lastName;
        
        nameCol.appendChild(nameHeading);
        nameRow.appendChild(nameCol);

        const heightDobRow = document.createElement('div');
        heightDobRow.setAttribute('class', 'row height-dob-row');

        const heightCol = document.createElement('div');
        heightCol.setAttribute('class', 'col height-col');

        const dobCol = document.createElement('div');
        dobCol.setAttribute('class', 'col');

        const heightParagraph = document.createElement('div');

        if(fieldAgentObj.heightInInches === null) {
            heightParagraph.innerHTML = `<label>Height:</label><br /> <p>Unknown"</p>`;
        } else {
            heightParagraph.innerHTML = `<label>Height:</label><br /> <p>${fieldAgentObj.heightInInches}"</p>`
        }

        const dobParagraph = document.createElement('div');

        if(fieldAgentObj.dob === null) {
            dobParagraph.innerHTML = "<label>DOB:</label><br /> <p>Unknown</p>";
        } else {
            dobParagraph.innerHTML = `<label>DOB:</label><br /> <p>${fieldAgentObj.dob}</p>`
        }
        
        heightCol.appendChild(heightParagraph);
        dobCol.appendChild(dobParagraph);
        
        heightDobRow.appendChild(heightCol);
        heightDobRow.appendChild(dobCol);
        
        cardBody.appendChild(nameRow);
        cardBody.appendChild(heightDobRow);

        const buttonSection = document.createElement('div');
        buttonSection.setAttribute('class', 'agent-buttons');

        const editButton = document.createElement('button');
        editButton.setAttribute('class', 'btn btn-info btn-sm me-1');
        editButton.innerText = "Edit";
        editButton.id = "edit-" + fieldAgentObj.agentId; //buttons have ID of edit-fieldagentId
        editButton.onclick = editButtonClickHandler; 

        const deleteButton = document.createElement('button');
        deleteButton.setAttribute('class', 'btn btn-danger btn-sm');
        deleteButton.innerText = "Delete";
        deleteButton.id = "delete-" + fieldAgentObj.agentId;
        deleteButton.onclick = deleteButtonClickHandler; 

        buttonSection.appendChild(editButton);
        buttonSection.append(deleteButton);

        cardBody.appendChild(buttonSection);
        
        card.appendChild(cardBody);

        cardCol.appendChild(card);

        fieldAgentCardContainer.appendChild(cardCol);
    })
}



// Event Triggers

Window.onload = getFieldAgentsOnEvent();

firstNameInput.onchange = () => inputOnChangeHandler("firstName");
middleNameInput.onchange = () => inputOnChangeHandler("middleName");
lastNameInput.onchange = () => inputOnChangeHandler("lastName");
heightInput.onchange = () => inputOnChangeHandler("height");
dobInput.onchange = () => inputOnChangeHandler("dob");

addAgentButton.onclick = addAgentButtonClickHandler;

deleteYes.onclick = deleteYesConfirmHandler;
deleteNo.onclick = deleteNoConfirmHandler;
agentForm.onsubmit = formSubmitHandler;



//deleteButton.onclick = deleteSubmitHandler;



// Comments for Notes

// async function getFieldAgentsOnCall() {
//     const response = await fetch(url);

//     return response.json();
// }

// Call the method here immediately, and write as async/await
// getFieldAgentsOnCall().then(data => console.log("Get on Call: ", data));