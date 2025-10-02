import './Album.css'
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
            <div className="links-under-img">
                <img className = "img" src = {`https://coverartarchive.org/release/${album.id}/front`}/>
                <div className = "album-links">
                    <h5>www.youtube.com/album</h5>
                    <h5>www.spotify.com/album</h5>
                    <h5>www.applemusic.com/album</h5>
                </div>
            </div>
            <div className = "list-under-title">
                <div className = "album-title">
                    <h1 className = "title">{album.title}</h1>
                    <h3 className = "date"> {album.date} by </h3>
                    <h2 className = "artist">{album["artist-credit"]?.[0]?.name}</h2>
                </div>
                <div className = "tracklist">
                    <h4>song 1</h4>
                    <h4>song 2</h4>
                    <h4>song 3</h4>
                    <h4>song 4</h4>
                    <h4>song 5</h4>
                    <h4>song 6</h4>
                </div>
            </div>
        </div>

    )
}