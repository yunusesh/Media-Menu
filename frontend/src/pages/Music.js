import { MusicSearchBar } from '../components/MusicSearchBar'
import "./Music.css"

export function Music(){
    return(
        <div className="music-page">
            <div className="search-bar">
                <MusicSearchBar></MusicSearchBar>
            </div>
        </div>
    )
}