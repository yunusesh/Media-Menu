import {SearchBar} from "./SearchBar";
import {SearchResultsList} from "./SearchResultsList";
import React, {useState} from "react";

import "./MusicSearchBar.css"
export function MusicSearchBar() {
    const [results, setResults] = useState([]);
    const [searchType, setSearchType] = useState("artists");
    const [input, setInput] = useState("");
    const handleSearchTypeChange = (type) =>{
        setInput("")
        setSearchType(type)
        setResults([])
    }

    return(
        <div className="search-bar-container">
            <SearchBar setResults={setResults} setSearchType={setSearchType} searchType={searchType}/>
            <SearchResultsList results={results} searchType={searchType}/>
            <div className="artist-search">
                <button className =  {`artist-button ${searchType === "artists" ? "active" : ""}`} onClick={() =>
                    handleSearchTypeChange("artists")}
                    //if artist button is clicked we highlight by changing color
                >
                    Artists
                </button>
            </div>

            <div className="release-search">
                <button className = {`release-button ${searchType === "releases" ? "active" : ""}`} onClick={() =>
                    handleSearchTypeChange("releases")}
                >
                    Releases
                </button>
            </div>
        </div>
    )
}