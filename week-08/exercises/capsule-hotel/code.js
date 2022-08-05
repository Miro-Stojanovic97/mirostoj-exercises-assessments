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



// ==============================
// Plan

// Entering a guest's name in a field
// Must capture guest's name
// Entering a capsule number in a field
// Must capture capsule number
// On click of the submit button
    // Find the correct capsule in the list of generated capsules
    // "Unoccupied" must change to the guest's name on the correct capsule
    // Color of capsule badge must change to red

// Elements
const guest = document.getElementById("guest");
const bookingCapsule = document.getElementById("bookingCapsule");
const checkInForm = document.getElementById("check-in-form");

// Global Variables
let guestName = "";
let capsuleNumber = 0;

// Functions

// When Input Changes, save content to a variable
const inputDataCatcher = (event) => {
    return event.target.value;
}

const guestHandler = (event) => {
    guestName = inputDataCatcher(event);
}

const capsuleHandler = (event) => {
    capsuleNumber = inputDataCatcher(event);
}

const checkinHandler = (event) => {
    event.preventDefault();

    console.log("Guest's name: ", guestName);
    console.log("Capsule number: ", capsuleNumber);

    // Find correct capsule badge
    const pill = document.getElementById("capsuleLabel" + capsuleNumber);

    // Find correct capsule name
    const guestNameSpace = document.getElementById("guest" + capsuleNumber);

    // Change capsule class to be the red one
    pill.setAttribute("class", "badge badge-pill badge-danger");

    // Update guest's name space from `Unoccupied` to `Guest Name`
    guestNameSpace.innerText = guestName;
}

// Events
guest.onchange = guestHandler;
bookingCapsule.onchange = capsuleHandler;
checkInForm.onsubmit = checkinHandler;