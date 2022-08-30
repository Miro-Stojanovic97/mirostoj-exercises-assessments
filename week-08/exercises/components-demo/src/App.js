import Heading from "./Heading";
import Numbers from "./Numbers";
import Movies from "./Movies";

function App() {
  return (
    <div className="container">
      <h1 className="h1">This is the Piggy Bank App</h1>
      <Heading greeting={"Hey there 💃"} />
      <Heading greeting={"Salutations 🧙‍♂️"} />
      <Heading greeting={"Howdy 🤠"} />
      <Heading />
      <hr />
      <Numbers numbers={[1, 2, 3]} />
      <Movies />
    </div>
  );
}

export default App;
