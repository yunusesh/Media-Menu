import './Album.css'
import {useLocation, useNavigate, useParams} from "react-router-dom";
import { useQuery } from "react-query"
import {indexOf} from "lodash/array";

export function Album(){
    const {id} = useParams();
    // grab state of releaseGroupId from search query b/c it is has the most general album cover (not specific to release)
    const navigate = useNavigate();
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
                <img className = "img" src = {`https://coverartarchive.org/release-group/${data.id}/front`} alt = "placeholder.jpg"/>
                <div className = "album-links">
                    <h5>www.youtube.com/album</h5>
                    <h5>www.spotify.com/album</h5>
                    <h5>www.applemusic.com/album</h5>
                </div>
            </div>
            <div className = "list-under-title">
                <div className = "title-date-artist">
                    <div className = "title-box">
                    <h1 className = "title">{data.title}</h1>
                    </div>
                    <div className = "date-artist">
                    <h3 className = "date-format"> {data["first-release-date"].substring(0, 4)} • {data["primary-type"]} by </h3>
                        {/*(0,4) grabs start of date to end of date i.e. 2014-10-12 becomes 2012) */}
                    <h2 className = "artist" onClick={() => {
                        navigate(`/music/artist/${data["artist-credit"]?.[0]?.id}`)
                    }}
                        //0 grabs first release/artist
                    >{data["artist-credit"]?.[0]?.name}</h2>
                    </div>
                </div>
                <div className = "tracklist">
                    {data.tracklist?.map(track =>(
                        <h3 className = "tracklist-items"
                            key = {track.id}
                            onClick={() => {
                                navigate(`/music/track/${track?.recording?.id}`)
                            }}

                        >{track.title}</h3>
                    ))}
                    <h1></h1>
                </div>
            </div>
        </div>

    )
}