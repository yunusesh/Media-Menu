import './Album.css'
import {MusicSearchBar} from "../../components/MusicSearchBar";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

export function Album(){
    const {id} = useParams();

    const [album, setAlbum] = useState("");
    useEffect(() => {
        fetch(`http://localhost:8081/album/${id}`)
            .then((response) => response.json())
            .then((json) => {
                setAlbum(json)
        })
    }, []);

    return(

        <div className = "album-page">
            <div className="search-bar">
                <MusicSearchBar></MusicSearchBar>
            </div>
            <div className = "album-title">
                <h1>{album.title} - {album.date} by {album["artist-credit"]?.[0]?.name}</h1>
            </div>
            <div className = "artist-image">
                Image
            </div>
            <div className = "album-links">
                links
            </div>
            <div>
                tracklist
            </div>
        </div>

    )
}