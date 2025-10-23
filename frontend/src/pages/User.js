import {useParams} from "react-router-dom";


export function User() {
    const { username } = useParams()
    return(
        <div className = "user-page">
            USER: {username}
        </div>
    )
}