
function displayList() {
    getSolarPanels()
        .then(data => renderList(data));
}

function getSolarPanels() {
    return fetch('http://localhost:8080/api/solarpanel') //returns a promise
        .then(response => {
            return response.json();
        })
}

function handleSubmit(event) {
    event.preventDefault();

    const section = document.getElementById('section').value;
    const row = document.getElementById('row').value;
    const column = document.getElementById('column').value;
    const yearInstalled = document.getElementById('yearInstalled').value;
    const material = document.getElementById('material').value;
    const tracking = document.getElementById('tracking').value;

    const solarPanel = {
        section,
        row: row ? parseInt(row) : 0,
        column: column ? parseInt(column) : 0,
        yearInstalled: yearInstalled ? parseInt(yearInstalled) : 0,
        material,
        tracking: tracking ? true : false
    };

    //TODO: POST the data to the API
    const init = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanel)
    };

    fetch('http://localhost:8080/api/solarpanel', init)
        .then(response => {
            if (response.status === 201 || response.status === 400) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if (data.id) {
                //happy path
                displayList();
                resetErrors();
                //the event target is the form. Resets the form for better ui
                event.target.reset();
            } else {
                //unhappy path
                renderErrors(data);
            }
        })
        .catch(error => console.log(error));

}

function handleEditPanel(solarPanelId) {
    console.log('Editing panel id: ' + solarPanelId);
}

function handleDeletePanel(solarPanelId) {
    console.log('Deleting panel id: ' + solarPanelId);
}

function renderErrors(errors) {
    const errorsHtml = errors.map(error => `<li>${error}</li>`);
    const errorsHtmlString = `
    <p>The following errors were found:</p>
    <ul>
        ${errorsHtml.join('')}
    </ul>
    `;
    document.getElementById('errors').innerHTML = errorsHtmlString;


    //TODO talk about injection
}

function resetErrors() {
    document.getElementById('errors').innerHTML = '';
}

function renderList(solarPanels) {

    const solarpanelsHtml = solarPanels.map(solarPanel => {
        return `
        <tr>
            <td>${solarPanel.section}</td>
            <td>${solarPanel.row}-${solarPanel.column}</td>
            <td>${solarPanel.yearInstalled}</td>
            <td>${solarPanel.material}</td>
            <td>${solarPanel.tracking ? 'Yes' : 'No'}</td>
            <td>
                <button onclick="handleEditPanel(${solarPanel.id})">Edit</button>
                <button onclick="handleDeletePanel(${solarPanel.id})">Delete</button>
            </td>
        </tr>
        `;
    })

    //This format used above ? '' : '' can be used to change the value to something more UI friendly
    // <td>${solarPanel.tracking ? 'Yes' : 'No'}</td>

    const tableBodyElement = document.getElementById('tableRows');
    tableBodyElement.innerHTML = solarpanelsHtml.join('');
}

displayList();