import {MusicSearchBar} from "../../components/MusicSearchBar";
import './Artist.css'
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

export function Artist(){
    const {id} = useParams();

    const [artist, setArtist] = useState("");
    useEffect(() => {
        fetch(`http://localhost:8081/artist/${id}`)
            .then((response) => response.json())
            .then((json) => {
                setArtist(json)
            })
    }, []);

    return(

    <div className = "artist-page">
        <div className="search-bar">
            <MusicSearchBar></MusicSearchBar>
        </div>

        <div className = "artist-name">
            {artist.name}
        </div>
    </div>
)
}