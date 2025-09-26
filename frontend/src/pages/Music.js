import { SearchBar } from '../components/SearchBar'
import {SearchResultsList} from "../components/SearchResultsList";
import {useState} from "react";

export function Music(){

    const [results, setResults] = useState([]);
    //search results list is the list of elements under the search
    return(
        <div>
            <h1>Music</h1>
    <div className="search-bar-container">
        <SearchBar setResults={setResults}/>
        <SearchResultsList results={results}/>
    </div>
        </div>
    )
}