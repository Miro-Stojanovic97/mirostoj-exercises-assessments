import { Link } from "react-router-dom";

function Nav({setCurrentSolarPanel}) {

    const clearForm = () => {
        setCurrentSolarPanel({});
        document.getElementById("sp-form").reset();
    }

    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="/">Solar Panels</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/solarpanels">Solar Panels</Link>
                        </li>
                        <li className="nav-item">
                            <Link onClick={clearForm} className="nav-link" to="/form">New Solar Panel</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Nav;