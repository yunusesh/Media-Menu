import React from 'react'
import "./SearchResult.css"
import {useNavigate} from "react-router-dom";

export const SearchResult = ({ result, searchType } ) => {
    const navigate = useNavigate();

    return(
        <div className="search-result"
             onClick={(e) => {
                 if (searchType === "artists")
                    {navigate(`/music/artist/${result.id}`)}
                 else if (searchType === "releases")
                    {navigate(`/music/album/${result.id}`)}
                    //grab id and reroute then refresh page so changes take place (maybe this isnt best practice idk)
                 else alert('Invalid search type')
             }
        }
        >
            {
                searchType === "artists"
                ? result.name
                : searchType === "releases"
                    ? `${result.title}  - ${result["artist-credit"][0]?.name}`
                        : "Invalid search type"
            }
        </div>
    )
}