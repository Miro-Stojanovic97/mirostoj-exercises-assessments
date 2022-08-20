import { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";

import Nav from './components/Nav';
import Home from './components/Home';
import Drinks from './components/Drinks';
import Drink from './components/Drink';
import NotFound from './components/NotFound';

function App() {

  const [drinks, setDrinks] = useState([]);

  useEffect(() => {
    fetch("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail")
    .then((response) => response.json())
    .then((data) => setDrinks(data.drinks))
    .catch((error) => console.error(error));
  }, []);

  return (
    <>
      <Nav />
      <div className="container mt-5">  
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/drinks" element={<Drinks theDrinks={drinks} />} />
          <Route path="/drink/:idDrink" element={<Drink />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
