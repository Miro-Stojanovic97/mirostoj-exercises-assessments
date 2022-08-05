

fetch('http://localhost:8080/api/solarpanel') //returns a promise
    .then(response => {
        return response.json(); 
    })
    .then(jsone => {
        console.log(json);
    })
