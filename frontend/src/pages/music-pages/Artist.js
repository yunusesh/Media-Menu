import './Artist.css'
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import { useQuery } from "react-query";

export function Artist(){
    const {id} = useParams();

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
            {data["release-groups"]?.map(release =>(
                <div className = "release-items">
                <h4 className = "release-title" key = {release.id}>{release.title}</h4>
                    <img className = "release-img"
                         key = {release.id}
                         src = {`https://coverartarchive.org/release-group/${release.id}/front`}
                         alt = "placeholder.jpg"/>
                </div>

            ))}
        </div>
    </div>
)
}