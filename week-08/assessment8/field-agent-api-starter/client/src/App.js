import Agents from './components/Agents'

function App() {
  return (
    <>
      <div className="jumbotron">
        <h1 className="display-4">Field Agent Manager</h1>
        <p className="lead">Department of Centralized Intelligence.</p>
        <hr className="my-4"></hr>
      </div> 
      <Agents />
    </>
  );
}

export default App;
