const CAPSULE_COUNT = 100;

function init() {
    const capsuleContainer = document.getElementById("capsules");
    let html = "";
    for (let i = 0; i < CAPSULE_COUNT; i++) {
        html += `<div>
            <span id="capsuleLabel${i + 1}" class="badge badge-pill badge-success">Capsule #${i + 1}</span>
            &nbsp;<span id="guest${i + 1}">Unoccupied</span>
        </div>`
    }
    capsuleContainer.innerHTML = html;
}

init();

//Plan
//
//Entering a guests name in a field
//must capture guests name
//Entering a capsule number in a field
//Must capture capsule number                 
//On click of the submit button
    //find the correct capsule in the list of generated capsules
    //"unoccupied" must change to a guests name on the correct capsule 
    // color of capsule badge must change to red 

//Elements 
const guest = document.getElementById("guest");
const bookingCapsule = decument.getElementById("bookingCapsule");
const checkInForm = document.getElementById("chech-in-form");

//Global variables
let guestName = "";
let capsuleNumber = 0;

//Functions
const inputDataCatcher = (event) => {
    return event.target.value;
}

const guestHandler = () => {
    guestName = inputDataCatcher();
}

const capsuleHandler = () => {
    capsuleNumber = inputDataCatcher();
}

const checkInHandler = () => {
    event.preventDefault();
    
    //find correct capsule badge
    document.getElementById("capsuleLabel" + capsuleNumber);
    
    // find corect capsule name
    const guestNameSpace = document.getElementById("guest" + capsuleNumber);
    pill.setAttribute("class", "badge badge-pill badge-danger");
    guestNameSpace.innerText = guestName;
}

//Events
guest.onchange = guestHandler;
bookingCapsule.onchange = capsuleHandler;
// guest.addEventListener('Change', inputDataCatcher)