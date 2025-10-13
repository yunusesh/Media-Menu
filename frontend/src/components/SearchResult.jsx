import React from 'react'
import "./SearchResult.css"
import {useNavigate} from "react-router-dom";

export const SearchResult = ({ result, searchType } ) => {
    const navigate = useNavigate();
    return(
        <div className="search-result"
             onClick={() => {
                 if (searchType === "artists") {
                     navigate(`/music/artist/${result.id}`)
                 }
                 else if (searchType === "releases") {
                     navigate(`/music/album/${result.id}`)
                 }
                 else if (searchType === "tracks") {
                     navigate(`/music/track/${result.id}`)
                 }
                 else alert('Invalid search type')
             }
        }
        >
            {
                searchType === "artists"
                ? result.name
                : searchType === "releases"
                    ? `${result.title}  - ${result["artist-credit"][0]?.name}`
                    : searchType === "tracks"
                    ? `${result.title} - ${result["artist-credit"][0]?.name}`
                        : "Invalid search type"
            }
        </div>
    )
}