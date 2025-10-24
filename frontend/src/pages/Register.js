import "./Register.css"
import axios from "axios";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export function Register() {
    const navigate = useNavigate();
    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");
    const[email, setEmail] = useState("");

    const handleSubmit = async() =>{
        try{
            const response = await axios.post("http://localhost:8081/auth/signup",{
                username: username,
                password: password,
                email: email
            })
            console.log(response.data)
            navigate('/login')
        } catch(error){
            console.log("Error registering user", error)
        }
    }

    return (
        <div className="register-page">
            <div className="register">
                <h1 className="signup-header">
                    Create an account
                </h1>
                <div className="email-wrapper">
                    <input
                        className="email"
                        placeholder="Enter an email"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="username-wrapper">
                    <input
                        className="username"
                        placeholder="Enter a username"
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="password-wrapper">
                    <input
                        className="password"
                        placeholder="Enter a password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                    <button
                        className="register-button"
                        onClick={handleSubmit}
                    >
                        Register
                    </button>
            </div>
        </div>
    )
}