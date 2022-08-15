// 1. The paper we want to track and update.
let paper = {
    title: "The Benefits of Flipping Your Pillow to the Cold Side",
    authors: "Wnt. Snooze, M. Sleepy",
    abstract: `Flipping your pillow to the cold side is shown have many benefits.
It's nice.
You should try it.`,
    discipline: "philosophy",
    urgency: "8",
    categories: ["daring", "original"]
};

// 2. Create a function to populate the form with any paper.
function bindPaper(p) {
    // 3. use .value for many controls
    document.getElementById("title").value = p.title;
    document.getElementById("authors").value = p.authors;
    document.getElementById("abstract").value = p.abstract;
    document.getElementById("discipline").value = p.discipline;

    // 4. radios take an extra step
    const radioButton =
        document.querySelector(`input[name="urgency"][value="${p.urgency}"]`);

    if (radioButton) {
        radioButton.checked = true;
    }

    // 5. checkboxes as well
    for (const checkBox of document.querySelectorAll(`input[name="categories"]`)) {
        checkBox.checked = p.categories.includes(checkBox.value);
    }

}

// Call the function at the end.
bindPaper(paper);
