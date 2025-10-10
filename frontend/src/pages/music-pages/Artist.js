import './Artist.css'
import {useNavigate, useParams} from "react-router-dom";
import { useQuery } from "react-query";
import {useEffect, useState} from "react";

export function Artist(){
    const {id} = useParams();
    const navigate = useNavigate();
    async function fetchArtist(){
        const response = await fetch(`http://localhost:8081/artist/${id}`);
        return response.json()
    }

    const{ data, status } = useQuery( {
        queryKey: ['artist', id],
        queryFn: () => fetchArtist(id),
        enabled: !!id,
    })

    const [artistImage, setArtistImage] = useState();

    useEffect( () => {
        if(data){
        if(`${data.url}` == null){
            setArtistImage(`https://coverartarchive.org/release-group/${data["release-groups"]?.[0]?.id}/front`)
        }
        else setArtistImage(`${data.url}`);
    }}, [data])

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
                 src = {artistImage}
                 onError={() =>
                setArtistImage(
                    `https://coverartarchive.org/release-group/${data["release-groups"]?.[0]?.id}/front`
                )}
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