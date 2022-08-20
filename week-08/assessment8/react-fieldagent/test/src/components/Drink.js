import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

function Drink() {
    const { idDrink } = useParams();
    const [drink, setDrink] = useState(null);

    useEffect(() => {
        fetch("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + idDrink)
        .then((response) => response.json())
        .then((data) => setDrink(data.drinks[0]))
        .catch((error) => console.error(error));
    }, []);

    const renderDrinkData = () => {
        return (
            <div className="drink-tile text-center rounded p-5">
                <h2>
                    {drink.strDrink}
                    <br />
                    <small>ID: {idDrink}</small>
                </h2>
                <img className="img-fluid rounded mx-auto d-block mb-5" src={drink.strDrinkThumb} alt={drink.strDrink} />
            </div>
        )
    }
    
    return (
        <>
            {drink ? renderDrinkData() : ""}
        </>
    )

}

export default Drink;