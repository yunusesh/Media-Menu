import './Artist.css'
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import { useQuery } from "react-query";

export function Artist(){
    const {id} = useParams();

    const [artist, setArtist] = useState("");

    async function fetchArtist(){
        const response = await fetchArtist(`http://localhost:8081/artist/${id}`);
        return response.json()
    }

    return(

    <div className = "artist-page">
        <div className = "artist-name">
            {artist.name}
        </div>
    </div>
)
}