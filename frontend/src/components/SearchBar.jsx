import React, {useState, useRef, useEffect} from 'react'
import "./SearchBar.css"
import { FaSearch } from "react-icons/fa"

export const SearchBar = ({ setResults, searchType, setSearchType}) => {
    const [input, setInput] = useState("");
    const debounceTimeout = useRef(null); // store timeout ID

    const fetchData = (value) => {
        if (!value || value.trim() === "") {
            setResults([]); //set this to recently searched later!
            return;
        }
        fetch(`http://localhost:8081/${searchType}/${value}`)
            .then((response) => response.json())
            .then((json) => {
                if (json.artists) {
                    setResults(json.artists);
                }
                else if(json.releases){
                    setResults(json.releases);
                }
                else{
                    setResults([]);
                }
            })
            .catch(() => setResults([]));
    };

    const handleChange = (value) => {
        setInput(value);

        // Clear any previous timeout before setting a new one
        if (debounceTimeout.current) {
            clearTimeout(debounceTimeout.current);
        }

        // Wait 400ms after typing stops before calling fetch
        debounceTimeout.current = setTimeout(() => {
            fetchData(value);
        }, 200);
    };

    const handleSearchTypeChange = (type) =>{
        setInput("")
        setSearchType(type)
        setResults([])
    }

    const[visible, setVisible] = useState(false); // on search icon click we toggle searchbar visibility
    return (
        <div className="search-wrapper">
            <FaSearch
                id="search-icon"
                onClick={() =>
                    setVisible(!visible)
                }
            />

            <div className="artist-search">
               <button className = "artist-button" onClick={() =>
                   handleSearchTypeChange("artists")}
                       style={{ backgroundColor: searchType === "artists" ? "#00ffd5" : "#1d6fa3"}}
                       //if artist button is clicked we highlight by changing color
                >
                   Artists
               </button>
            </div>

            <div className="release-search">
                <button className = "release-button" onClick={() =>
                    handleSearchTypeChange("releases")}
                    style={{ backgroundColor: searchType === "releases" ? "#00ffd5" : "#1d6fa3"}}

                >
                    Releases
                </button>
            </div>

            {visible && (
                <div className="input-wrapper">
                    <input
                        placeholder="Type to search..."
                        value={input}
                        onChange={(e) => handleChange(e.target.value)}
                    />
                </div>
            )}
        </div>
    );
}