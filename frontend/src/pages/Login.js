import {useContext, useState} from "react";
import "./Login.css"
import axios from "axios";
import {AuthContext} from "../AuthContext"
import {useNavigate} from "react-router-dom";

export function Login(){
    const { login } = useContext(AuthContext)
    const { user } = useContext(AuthContext)
    const[username, setUsername] = useState("")
    const[password, setPassword] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async() =>{
        try{
            const response = await axios.post("http://localhost:8081/auth/login", {
                username: username,
                password: password
            })
            login(response.data.token)
            navigate(`/user/${user.username}`)


        } catch(error){
            console.log("Error fetching data:", error)
        }
    }


    return(
        <div className = "profile-page">
            <h1 className = "account-header"> You aren't signed in yet</h1>
            <div className = "auth">
                <div className = "login">
                    <h3 className = "login-header">If you have an account, sign in</h3>
                    <div className = "username-wrapper">
                        <input
                            className = "username"
                            id = "username"
                            onChange={(e) => {
                                setUsername(e.target.value)
                            }}
                        />
                    </div>
                    <div className = "password-wrapper">
                        <input
                            className = "password"
                            id = "password"
                            onChange={(e) => {
                                setPassword(e.target.value)
                            }}
                        />
                    </div>

                    <button className = "login-button" onClick={handleSubmit}>
                        Login</button>
                </div>
                <div className = "register">
                    <h3 className = "register-header">Don't have an account?</h3>
                    <button className = "register-button">Create Account</button>
                </div>
            </div>
        </div>
    )
}