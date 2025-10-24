import './Album.css'
import {useNavigate, useParams} from "react-router-dom";
import {useQuery} from "react-query"
import { useEffect, useState} from "react";

export function Album() {
    const {id} = useParams();
    // grab state of releaseGroupId from search query b/c it is has the most general album cover (not specific to release)
    const navigate = useNavigate();
    const [visible, setVisible] = useState(false);
    const [albumImage, setAlbumImage] = useState("")
    const [albumTitle, setAlbumTitle] = useState("")
    const [albumDate, setAlbumDate] = useState("")
    const [albumReissue, setAlbumReissue] = useState("")
    const [reissueIds, setReissueIds] = useState([])
    const [reissuesList, setReissuesList] = useState([])

    async function fetchAlbum() {
        const response = await fetch(`http://localhost:8081/album/${id}`)
        return response.json()
    }

    const {data, status} = useQuery({
        queryKey: ['album', id],
        queryFn: () => fetchAlbum(id),
        enabled: !!id //refetch and update page when key (id) changes (we are now on a new album page)
    })

    //this only exists to dynamically update page contents based on what reissue is selected, default to releaseGroup
    useEffect(() => {
        if (data) {
            setAlbumImage(`https://coverartarchive.org/release-group/${data.id}/front`)
            setAlbumTitle(data.title)
            setAlbumDate(data["first-release-date"].substring(0, 4))
            setAlbumReissue(data)

            const ids = data.releases.map(release => release.id)
            setReissueIds(ids)
        }
    }, [data])

    useEffect(() => {
        const fetchReissues = async () => {
            try {
                const responses = await Promise.all(reissueIds.map(reissueId => fetch(`http://localhost:8081/reissue/${reissueId}`)))
                const reissueData = await Promise.all(responses.map(response => response.json()))
                setReissuesList(reissueData)
            } catch (err) {
                console.error("Failed to fetch releases", err)
            }
        }
        fetchReissues()
    }, [reissueIds])


    if (status === 'loading') {
        return <p>Loading...</p>
    }

    if (status === 'error') {
        return <p>Error!</p>
    }

    return (

        <div className="album-page">
            <div className="album-info">
                <div className="links-under-img">
                    <img className="img" src={albumImage} alt="placeholder.jpg"/>
                    <div className="album-links">
                        <h5>www.youtube.com/album</h5>
                        <h5>www.spotify.com/album</h5>
                        <h5>www.applemusic.com/album</h5>
                    </div>
                </div>
                <div className="list-under-title">
                    <div className="title-date-artist">
                        <div className="title-box">
                            <h1 className="title">{albumTitle}</h1>
                        </div>
                        <div className="date-artist">
                            <h3 className="date-format"> {albumDate} • {data["primary-type"]} by </h3>
                            {/*(0,4) grabs start of date to end of date i.e. 2014-10-12 becomes 2012) */}
                            <h2 className="artist" onClick={() => {
                                navigate(`/music/artist/${data["artist-credit"]?.[0]?.id}`)
                            }}
                                //0 grabs first release/artist
                            >{data["artist-credit"]?.[0]?.name}</h2>
                        </div>
                        <div className="reissues-container">
                            {visible && (
                                <div className="reissues-list">
                                    {reissuesList.map(reissue => (
                                        <div className="reissues-result"
                                             key={reissue.id}
                                             onClick={() => {
                                                 //if a new reissue then do all the changes else go back to defaults
                                                 if (reissue.disambiguation !== "") {
                                                     setAlbumImage(`https://coverartarchive.org/release/${reissue.id}/front`)
                                                     setAlbumTitle(`${reissue.title} (${reissue.disambiguation})`)
                                                     setAlbumReissue(reissue)
                                                     if (reissue.date !== "") {
                                                         setAlbumDate(reissue.date.substring(0, 4))
                                                     }
                                                 } else {
                                                     setAlbumImage(`https://coverartarchive.org/release-group/${data.id}/front`)
                                                     setAlbumTitle(data.title)
                                                     setAlbumDate(data["first-release-date"].substring(0, 4))
                                                     setAlbumReissue(data)
                                                 }
                                             }}>
                                            {reissue.title}{reissue.disambiguation !== "" ?
                                            ` (${reissue.disambiguation.charAt(0).toUpperCase() + reissue.disambiguation.slice(1)})` :
                                            ""}
                                        </div>
                                    ))}

                                </div>
                            )}
                            <button className="reissues" onClick={() => {
                                setVisible(!visible);

                            }}> Reissues ({data.releases?.length})
                            </button>
                        </div>
                    </div>
                    <div className="tracklist">
                        {albumReissue.tracklist?.map(track => (
                            <h3 className="tracklist-items"
                                key={track.id}
                                onClick={() => {
                                    navigate(`/music/track/${track?.recording?.id}`)
                                }}

                            >{track.title}</h3>
                        ))}
                    </div>
                </div>
            </div>
            <div className="rating">

            </div>
        </div>
    )
}