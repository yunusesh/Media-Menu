import "./Track.css"
import {useParams} from "react-router-dom";
import {useQuery} from "react-query";

export function Track(){
    const {id} = useParams();

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
        <div className = "track">
            <h3>{data?.title}</h3>
           <img className = "img"
                src = {`https://coverartarchive.org/release-group/${data?.release.id}/front`}
                alt = 'placeholder.jpg'
                />
        </div>

    )
}