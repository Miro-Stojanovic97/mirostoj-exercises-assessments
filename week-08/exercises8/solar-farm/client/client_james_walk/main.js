
fetch('http://localhost:8080/api/solarpanel') //returns a promise
    .then(response => {
        return response.json(); 
    })
    .then(data => {
        console.log(data);

    const tableBodyElement = document.getElementById('tableRows');
    tableBodyElement.innerHTML = `
        <tr>
            <td>section</td>
            <td>row-column</td>
            <td>year installed</td>
            <td>material</td>
            <td>is tracking</td>
            <td>some stuff</td>
        </tr>'
    `;
    
        
    
});