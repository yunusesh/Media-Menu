import "./Music.css"

export function Music(){
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    return(
        <div className="music-page">
            <div className = "favorites">
                <h1>Favorites</h1>
            </div>
            <div className = "top-year">
                <h1>Top of {currentYear}</h1>
            </div>

        </div>
    )
}