import "./Track.css"
import {useNavigate, useParams} from "react-router-dom";
import {useQuery} from "react-query";
import {FaRegEdit} from "react-icons/fa";
import {useContext, useState} from "react";
import {AuthContext} from "../../AuthContext";

export function Track() {
    const {id} = useParams()
    const {user} = useContext(AuthContext)
    const navigate = useNavigate()
    const [isEditing, setIsEditing] = useState(false)

    async function fetchTrack() {
        const response = await fetch(`http://localhost:8081/track/${id}`)
        return response.json()
    }

    const {data, status} = useQuery({
        queryKey: [`track`, id],
        queryFn: () => fetchTrack(id),
        enabled: !!id
    })

    if (status === `loading`) {
        <p>Loading...</p>
    }

    if (status === `error`) {
        <p>Error!</p>
    }

    return (
        <div className="track-page">
            <div className="track-info">
                <img className="img"
                     src={`https://coverartarchive.org/release-group/${data?.releases[0].id}/front`}
                     alt='placeholder.jpg'
                />
                <div className="title-above-release">
                    <div className="title-date-artist">
                        <h1 className="title">{data?.title}</h1>
                        <h3 className="date">{data?.["first-release-date"]?.substring(0, 4)} • Track by </h3>
                        <h2 className="artist"
                            onClick={() => navigate(`/music/artist/${data?.releases[0]["artist-credit"]?.[0]?.artist.id}`)}
                        >{data?.releases[0]["artist-credit"]?.[0]?.name}</h2>
                    </div>
                    <h3 className="releases">Appears on</h3>
                    {data?.releases?.map(releaseGroup =>
                        <div className="release-format">
                            <h2 className="release" key={releaseGroup?.id}
                                onClick={() => navigate(`/music/album/${releaseGroup?.id}`)}
                            >{releaseGroup?.title} </h2>
                            <h4 className="format"> {releaseGroup["first-release-date"].substring(0, 4)} • {releaseGroup["primary-type"]} </h4>
                        </div>
                    )}
                </div>
            </div>
            <div className={isEditing ? "stats-editing" : "stats"}>
                Your Stats
                <FaRegEdit className="edit" onClick={() => {
                    user ? setIsEditing(!isEditing) : setIsEditing(false)
                }}/>
                <div className="activity">
                    <h4>500 Total Listens</h4>
                    <h4>400 Listens In 2025</h4>
                    <h4>200 Listens This Month</h4>
                    <h4>100 Listens Today</h4>
                </div>


                <div className="editing">

                </div>
            </div>
        </div>

    )
}