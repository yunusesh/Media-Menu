import {useEffect, useState} from "react";
import {useQuery} from "react-query";
import {useNavigate, useParams} from "react-router-dom";
import "./UserActivity.css"
export function UserActivity(){
    const {username} = useParams()
    const navigate = useNavigate()
    const [listened, setListened] = useState([])

    async function fetchUser() {
        const response = await fetch(`http://localhost:8081/user/${username}`)
        return response.json()
    }

    const {data: userData} = useQuery({
        queryKey: ["user", username],
        queryFn: () => fetchUser(),
        enabled: !!username,
    })

    async function fetchUserListens() {
        const response = await fetch(`http://localhost:8081/api/scrobble/user/${userData?.id}`)
        return response.json()
    }

    const {data: userListens} = useQuery({
        queryKey: ["userListens", userData?.id],
        queryFn: () => fetchUserListens(),
        enabled: !!userData?.id
    })

    useEffect(() => {
        if (userListens) {
            setListened(userListens.reverse().map(listen => listen))
        }
    }, [userListens])

    return(
        <div className = "user-activity-page">
            <div className = "user-activity">
                        {listened.map(listen => (
                            <div className="user-activity-items" key={listen.trackMbid}>
                                <img className="activity-item-img"
                                     src={`https://coverartarchive.org/release-group/${listen.releaseMbid}/front`}
                                     alt="placeholder.png"
                                     onClick={() => {
                                         navigate(`/music/track/${listen.trackMbid}`)
                                     }}
                                />
                                <div className="activity-track-info">
                                    <h4 className="activity-track-title"
                                        key={listen.trackId}
                                        onClick={() => {
                                            navigate(`/music/track/${listen.trackMbid}`)
                                        }}> {listen.trackTitle}</h4>
                                    <h4 className="activity-track-artist"
                                        key={listen.artistName}
                                        onClick={() => {
                                            navigate(`/music/artist/${listen.artistMbid}`)
                                        }}
                                    >{listen.artistName}</h4>
                                </div>
                            </div>
                        ))}
            </div>
        </div>
    )
}