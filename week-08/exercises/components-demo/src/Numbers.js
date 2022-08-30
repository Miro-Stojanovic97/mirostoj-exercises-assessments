// Take in an array of numbers as props
// Iterate through the array and create an <li> tag for each number
// Insert the number into the <li> tag
// Insert all the <li> tags I create into a <ul>
import Number from "./Number";

function Numbers({numbers}) {


    const liFactory = () => {
        return numbers.map(num => <Number key={num + "-key"} numberProp={num} />);
    }

    return (
        <ul>
            {liFactory()}
        </ul>
    )
}

export default Numbers;