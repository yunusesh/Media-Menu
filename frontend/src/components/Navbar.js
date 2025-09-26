import { Link } from "react-router-dom"
import "./Navbar.css"
export function Navbar(){
    return(
        <div className="container">
        <nav className="navbar">
            <ul className="nav--list">
                <Link to="/" className = "item">Home</Link>
                <Link to="/Music" className = "item">Music</Link>
                <Link to="/Movies" className = "item">Movies</Link>
                <Link to="/TV" className = "item">TV</Link>
            </ul>
        </nav>
        </div>

    )
}