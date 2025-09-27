import { Link } from "react-router-dom"
import "./Navbar.css"
import {useLocation} from "react-router";
export function Navbar(){
    return(
        <div className="container">
        <nav className="navbar">
            <ul className="nav--list">
                <Link to="/" className = "item"
               /* style={{ transform: useLocation().pathname === "/" ? "translateZ(0) rotate(0deg) skew(10deg) translate(0, 0)"
                    : "translateZ(0) rotate(-30deg) skew(25deg) translate(0, 0)" }}*/
                    >Home</Link>
                <Link to="/Music" className = "item"
                      style={{ transform: useLocation().pathname === "/Music" ? "translateZ(0) rotate(0deg) skew(10deg) translate(0, 0)"
                              : "translateZ(0) rotate(-30deg) skew(25deg) translate(0, 0)" }}
                >Music</Link>
                <Link to="/Movies" className = "item"
                      style={{ transform: useLocation().pathname === "/Movies" ? "translateZ(0) rotate(0deg) skew(10deg) translate(0, 0)"
                              : "translateZ(0) rotate(-30deg) skew(25deg) translate(0, 0)" }}
                >Movies</Link>
                <Link to="/TV" className = "item"
                      style={{ transform: useLocation().pathname === "/TV" ? "translateZ(0) rotate(0deg) skew(10deg) translate(0, 0)"
                              : "translateZ(0) rotate(-30deg) skew(25deg) translate(0, 0)" }}
                >TV</Link>
            </ul>
        </nav>
        </div>

    )
}