import { useState, useEffect } from "react";
import MagicCards from "./MagicCards";

function App() {

  const [cards, setCards] = useState([]);
  // const cards = [];
  // const setCards = (cardsInput) => {
  //   cards = cardsInput;
  // }

  const fetchCards = () => {
    fetch("http://localhost:3333/cards")
    .then(response => response.json())
    .then(data => setCards(data));
  }

  useEffect(() => {
    fetchCards();
  }, []);

  return (
    <div className="container">
      <h1>Magic the Gathering: The React Demo</h1>
      <MagicCards cardsProp={cards} />
    </div>
  );
}

export default App;