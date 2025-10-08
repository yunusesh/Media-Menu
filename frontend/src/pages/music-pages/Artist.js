import './Artist.css'
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import { useQuery } from "react-query";

export function Artist(){
    const {id} = useParams();
    const navigate = useNavigate();
    const [artist, setArtist] = useState("");
    async function fetchArtist(){
        const response = await fetch(`http://localhost:8081/artist/${id}`);
        return response.json()
    }

    const{ data, status } = useQuery( {
        queryKey: ['artist', id],
        queryFn: () => fetchArtist(id),
        enabled: !!id,
    })

    if(status === 'loading'){
        return <p>Loading...</p>
    }

    if(status === 'error'){
        return <p>Error!</p>
    }
    return(

    <div className = "artist-page">
        <div className = "name-over-img">
            <h1 className = "artist-name">{data.name}</h1>
            <img className = "artist-img"
                 src = {data.url}
                 alt = "placeholder.png"/>
        </div>
        <div className = "releases">
            {data["release-groups"]?.map(releaseGroup =>(
                <div className = "releaseGroup-items" key = {releaseGroup.id}
                onClick={() => {
                    navigate(`/music/album/${releaseGroup.id}`)
                }}
                >
                    <img className = "releaseGroup-img"
                         src = {`https://coverartarchive.org/release-group/${releaseGroup.id}/front`}
                         alt = "placeholder.jpg"/>
                <h4 className = "releaseGroup-title">{releaseGroup.title}</h4>
                </div>

            ))}
        </div>
    </div>
)
}