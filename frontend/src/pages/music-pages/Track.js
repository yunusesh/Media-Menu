import "./Track.css"
import {useNavigate, useParams} from "react-router-dom";
import {useQuery} from "react-query";

export function Track(){
    const {id} = useParams();
    const navigate = useNavigate();
    async function fetchTrack(){
        const response = await fetch(`http://localhost:8081/track/${id}`)

        return response.json();
    }

    const { data, status } = useQuery({
        queryKey: [`track`, id],
        queryFn: () => fetchTrack(id),
        enabled: !!id
    })

    if(status === `loading`){
        <p>Loading...</p>
    }

    if(status === `error`){
        <p>Error!</p>
    }

    return(
        <div className = "track-page">
            <img className = "img"
                 src = {`https://coverartarchive.org/release-group/${data?.release.id}/front`}
                 alt = 'placeholder.jpg'
            />
            <div className = "title-above-release">
                <div className = "title-date-artist">
                    <h1 className = "title">{data?.title}</h1>
                    <h3 className = "date">{data?.["first-release-date"]?.substring(0,4)} • Track by </h3>
                    <h2 className = "artist"
                        onClick={() => navigate(`/music/artist/${data?.release["artist-credit"]?.[0]?.artist.id}`)}
                    >{data?.release["artist-credit"]?.[0]?.name}</h2>
                </div>
            <h3 className = "release">Appears on</h3>
                <h2 className = "release-title"
                    onClick={() => navigate(`/music/album/${data.release.id}`)}
                >{data?.release?.title}</h2>
            </div>
        </div>

    )
}