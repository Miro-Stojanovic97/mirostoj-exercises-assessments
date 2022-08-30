import { Link } from "react-router-dom";

function Drinks({ theDrinks }) {

    const listDrinks = () => {
        return theDrinks.map((drink) => {
            return (
                <li key={drink.idDrink}>
                    <Link className="text-success" to={`/drink/${drink.idDrink}`}>{drink.strDrink}</Link>
                </li>
            )
        })
    }

    return (
        <>
            <h2>Drinks</h2>
            {listDrinks()}
        </>
    )
}

export default Drinks;