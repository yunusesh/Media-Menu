import { SearchBar } from '../components/SearchBar'
import {SearchResultsList} from "../components/SearchResultsList";
import {useState} from "react";
import {SearchResult} from "../components/SearchResult";

export function Music(){

    const [results, setResults] = useState([]);
    const [searchType, setSearchType] = useState("artists");
    //search results list is the list of elements under the search
    return(
        <div>
            <h1>Music</h1>
    <div className="search-bar-container">
        <SearchBar setResults={setResults} setSearchType={setSearchType} searchType={searchType}/>
        <SearchResultsList results={results} searchType={searchType}/>

    </div>
        </div>
    )
}