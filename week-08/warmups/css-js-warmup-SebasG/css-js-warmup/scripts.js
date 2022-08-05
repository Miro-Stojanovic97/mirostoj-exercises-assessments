// Page Elements
const button2 = document.getElementById("button2");
const button3 = document.getElementById("button3");
// Functions
const sendAlert = () => {
    alert("Hey!");
}

const sendAngryAlert = () => {
    alert("Thanks for your credit card info")
}

// Events
button2.onclick = sendAlert;
button3.onclick = sendAngryAlert;

const f = document.getElementById('foo');
document.addEventListener('click', function(ev){
    f.style.transform = 'translateY('+(ev.clientY-25)+'px)';
    f.style.transform += 'translateX('+(ev.clientX-25)+'px)';
},false);
