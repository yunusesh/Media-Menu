import { Link } from "react-router-dom"
import "./Navbar.css"
import {useLocation} from "react-router";
export function Navbar(){
    return(
        <div className="container">
            <nav className="navbar">
                <ul className="nav--list">
                    <Link to="/" className={`item ${useLocation().pathname === "/" ? "active" : ""}`}
                    >Home</Link>
                    <Link to="/music" className={`item ${useLocation().pathname.includes("/music") ? "active" : ""}`}
                    >Music</Link>
                    <Link to="/movies" className={`item ${useLocation().pathname.includes("/movies") ? "active" : ""}`}
                    >Movies</Link>
                    <Link to="/tv" className={`item ${useLocation().pathname.includes("/tv") ? "active" : ""}`}
                    >TV</Link>
                </ul>
            </nav>
        </div>

    )
}