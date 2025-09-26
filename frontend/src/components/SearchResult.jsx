import React from 'react'
import "./SearchResult.css"

export const SearchResult = ({ result, searchType } ) => {
    return(
        <div className="search-result"
             onClick={(e) => {
                 if (searchType === "artists")
                     alert(`You clicked on ${result.name}`);
                 else if (searchType === "releases")
                     alert(`You clicked on ${result.title} by ${result["artist-credit"][0]?.name}`);
                 else alert('Invalid search type')
             }
        }

        >
            {
                searchType === "artists"
                ? result.name
                : searchType === "releases"
                    ? `${result.title} - ${result["artist-credit"][0]?.name}`
                        : "Invalid search type"
            }
        </div>
    )
}