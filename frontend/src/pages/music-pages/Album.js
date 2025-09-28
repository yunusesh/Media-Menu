import {useState} from "react";
import {SearchBar} from "../../components/SearchBar";
import {SearchResultsList} from "../../components/SearchResultsList";
import './Album.css'

export function Album(){
    const [results, setResults] = useState([]);
    const [searchType, setSearchType] = useState("artists");

    return(

        <div className = "album-page">
            <div className = "artist-title">
                <h1>Title year by Artist </h1>
            </div>
            <div className = "artist-image">
                Image
            </div>
            <div className = "album-links">
                links
            </div>
            <div>
                tracklist
            </div>
            <div className="search-bar-container">
                <SearchBar setResults={setResults} setSearchType={setSearchType} searchType={searchType}/>
                <SearchResultsList results={results} searchType={searchType}/>
            </div>
        </div>

    )
}