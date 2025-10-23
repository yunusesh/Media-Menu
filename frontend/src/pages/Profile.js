import {useState} from "react";


export function Profile(){
    const[input, setInput] = useState("");


    return(
        <div className = "profile-page">
            <div className = "login">
                Login
            </div>
            <div className = "create-account">
                Create Account
            </div>
        </div>
    )
}