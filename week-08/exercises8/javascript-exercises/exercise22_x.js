const assert = require("assert");
// EXTRACT NAMES
// Create a function named `extractNames` that accepts on array of objects.
// Transform the array into a new array that only contains strings.
// Strings are drawn from the objects' `name` property, 
// but only if it exists.
// Return the result.
// Hint: the `map` method is useful here.


// Execute this exercise.
// If you see the message "success!", all tests pass.

//using map function on array of objects. Transforming an array into a new array with desired values
function extractNames(objectsArray) {

    //setting default value " " to name in order to ignore nulls
    objectsArray = objectsArray.map(({name = " "}) => {
        return name
    });

    //filtering out the " " results from the array
    const final = objectsArray.filter(o => !o.includes(" "));
    return final;
}


let input = [
    { a: 1, name: "a", c: true },
    { name: "b" },
    { firstName: "first", name: "c" }
];
let expected = ["a", "b", "c"]
assert.deepStrictEqual(extractNames(input), expected);

input = [
    { a: 1, name: "a", c: true },
    { firstName: "b" },
    { firstName: "first", name: "c" }
];
expected = ["a", "c"]
assert.deepStrictEqual(extractNames(input), expected);

input = [];
expected = []
assert.deepStrictEqual(extractNames(input), expected);

input = [{ firstName: "first", lastName: "last", name: "name" }];
expected = ["name"]
assert.deepStrictEqual(extractNames(input), expected);

console.log("success!");