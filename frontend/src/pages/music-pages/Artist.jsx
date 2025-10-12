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

    const [artistImage, setArtistImage] = useState(null);

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

    const releaseGroupsByFormat = {
        albums: [],
        mixtapes: [],
        eps: [],
        singles: [],
        live: [],
        compilations: [],
        others: [],
    };

    data["release-groups"]?.forEach(releaseGroup => {
        const format =
            releaseGroup["secondary-types"]?.includes("Mixtape/Street") ? "mixtapes" :
                releaseGroup["secondary-types"]?.includes("Compilation") ? "compilations" :
                    releaseGroup["secondary-types"]?.includes("Live") ? "live" :

                        ["Broadcast", "Spokenword",
                            "Interview", "Audiobook", "Audio drama",
                            "Remix", "DJ-mix", "Demo", "Field recording"]
                            .some(type => releaseGroup["secondary-types"]?.includes(type))
                            && releaseGroup["primary-type"]?.includes("Album") ? "others" :

                            releaseGroup["primary-type"]?.includes("Album") ? "albums" :
                            releaseGroup["primary-type"]?.includes("EP") ? "eps" :
                                releaseGroup["primary-type"]?.includes("Single") ? "singles" :
                                        "others";


        releaseGroupsByFormat[format].push(releaseGroup);
    });

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

                <div className= "releases">
                {Object.entries(releaseGroupsByFormat).map(([format, groups]) => (
                    <div className="categories">
                        {/* i.e. type albums becomes header Albums */}
                        <h3 className = "category">{format.charAt(0).toUpperCase() + format.slice(1)}</h3>
                        <div className = "category-releases">
                        {groups.map(releaseGroup => (
                            <div className="releaseGroup-items" key={releaseGroup.id}
                                 onClick={() => navigate(`/music/album/${releaseGroup.id}`)}>
                                <img className="releaseGroup-img"
                                     src={`https://coverartarchive.org/release-group/${releaseGroup.id}/front`}
                                     alt="placeholder.jpg"/>
                                <h4 className="releaseGroup-title">{releaseGroup.title}</h4>
                                {/* substring(0,4) 2012-10-15 -> 2012 */}
                                <h5 className = "releaseGroup-date"> {releaseGroup["first-release-date"]?.substring(0,4)}</h5>
                            </div>
                        ))}
                        </div>
                    </div>
                ))}
                </div>
        </div>
    )
}