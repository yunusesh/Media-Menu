import { SearchBar } from '../../components/SearchBar'
import { SearchResultsList } from '../../components/SearchResultsList'
import {useState} from "react";
import './Artist.css'

export function Artist(){

    const [results, setResults] = useState([]);
    const [searchType, setSearchType] = useState("artists");

    return(

    <div className="search-bar-container">
        <SearchBar setResults={setResults} setSearchType={setSearchType} searchType={searchType}/>
        <SearchResultsList results={results} searchType={searchType}/>
    </div>
)
}