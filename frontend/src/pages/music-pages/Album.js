import './Album.css'
import {useLocation, useParams} from "react-router-dom";
import { useQuery } from "react-query"

export function Album(){
    const {id} = useParams();
    // grab state of releaseGroupId from search query b/c it is has the most general album cover (not specific to release)
    const { state } = useLocation();

    async function fetchAlbum(){
        const response = await fetch(`http://localhost:8081/album/${id}`);
        return response.json()
    }

    const { data, status} = useQuery({
        queryKey: ['album', id],
        queryFn: () => fetchAlbum(id),
        enabled: !!id, //refetch and update page when key (id) changes (we are now on a new album page)
    })

    if(status === 'loading'){
        return <p>Loading...</p>
    }

    if(status === 'error'){
        return <p>Error!</p>
    }

    return(

        <div className = "album-page">
            <div className="links-under-img">
                <img className = "img" src = {`https://coverartarchive.org/release-group/${state.releaseGroupId}/front`} alt = "placeholder.jpg"/>
                <div className = "album-links">
                    <h5>www.youtube.com/album</h5>
                    <h5>www.spotify.com/album</h5>
                    <h5>www.applemusic.com/album</h5>
                </div>
            </div>
            <div className = "list-under-title">
                <div className = "album-title">
                    <h1 className = "title">{data.title}</h1>
                    <h3 className = "date"> {data.date} by </h3>
                    <h2 className = "artist">{data["artist-credit"]?.[0]?.name}</h2>
                </div>
                <div className = "tracklist">
                    {data.tracks?.map(track => (
                        <h4 className = "track-items" key = {track.id}>{track.title}</h4>
                        //we have position and it resets the # per album side, find a way to make this show side A, B etc.
                    ))}
                </div>
            </div>
        </div>

    )
}