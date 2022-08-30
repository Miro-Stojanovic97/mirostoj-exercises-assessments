import { useEffect, useState } from "react";
import { Routes, Route } from "react-router-dom";
import Nav from "./Nav";
import Message from "./Message";
import Home from "./Home";
import SolarPanels from "./SolarPanels";
import Form from "./Form";

function App() {

  const [solarPanels, setSolarPanels] = useState([]);
  const [currentSolarPanel, setCurrentSolarPanel] = useState({});
  const [darkMode, setDarkMode] = useState(true);
  const [messages, setMessages] = useState([]);

  const showMessages = (newMessage) => {
    setMessages([...messages, newMessage]);
    const messageAlert = document.getElementById('messages');
    messageAlert.setAttribute('class', 'alert alert-secondary alert-dismissible fade show');
  }

  useEffect(() => {
    const body = document.getElementsByTagName("body")[0];
    if (darkMode) {
      body.setAttribute("class", "dark-mode");
    } else {
      body.setAttribute("class", "light-mode");
    }
  }, [darkMode])

  return (
    <>
      <Nav setCurrentSolarPanel={setCurrentSolarPanel} />
      <div className="container mt-5">
        <Message messages={messages} setMessages={setMessages} />
        <div className="row">
          <div className="col-11">
            <h1>Solar Farm</h1>
          </div>
          <div className="col">
            <button className="mode-toggle h2" onClick={() => setDarkMode(!darkMode)}>{darkMode ? "🌝" : "🌚"}</button>
          </div>
        </div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/solarpanels" element={<SolarPanels 
            solarPanels={solarPanels} 
            setSolarPanels={setSolarPanels} 
            setCurrentSolarPanel={setCurrentSolarPanel}
            showMessages={showMessages}
            />} 
          />
          <Route path="/form" element={<Form 
            solarPanels={solarPanels} 
            setSolarPanels={setSolarPanels} 
            currentSolarPanel={currentSolarPanel} 
            messages={messages}
            setMessages={setMessages}
            showMessages={showMessages}
            />} 
          />
        </Routes>
      </div>
    </>
  );
}

export default App;
