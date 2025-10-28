import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useQuery} from "react-query";
import "./User.css"
import {FaStar} from "react-icons/fa";

export function User() {
    const navigate = useNavigate()
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const {username} = useParams()
    const [ratings, setRatings] = useState([])
    const [topOfYear, setTopOfYear] = useState([])

    async function fetchUser() {
        const response = await fetch(`http://localhost:8081/user/${username}`)
        return response.json()
    }

    const {data: userData} = useQuery({
        queryKey: ["user", username],
        queryFn: () => fetchUser(),
        enabled: !!username,
    })

    async function fetchUserRatings() {
        const response = await fetch(`http://localhost:8081/api/release-rating/user/${userData?.id}`)
        return response.json()
    }

    const {data: userRatings, status} = useQuery({
        queryKey: ["userRatings", userData?.id],
        queryFn: () => fetchUserRatings(),
        enabled: !!userData?.id,
    })

    useEffect(() => {
        if (userRatings) {
            setRatings(userRatings.map(rating => rating))
        }
    }, [userRatings]);

    if (status === 'loading') {
        return <p>Loading...</p>
    }

    if (status === 'error') {
        return <p>Error!</p>
    }

    return (
        <div className="user-page">
            <div className="profile">
                <div className="profile-categories">
                    <h1 className="category">Top 5</h1>
                </div>
                <div className="profile-categories">
                    <h1 className="category">Top of {currentYear}</h1>
                    <div className = "category-releases">

                    </div>
                </div>
                <div className="profile-categories">
                    <h1 className="category">Recently Listened</h1>
                </div>
                <div className="profile-categories">
                    <h1 className="category">Recently Rated</h1>
                    <div className="category-releases">
                        {ratings.map(rating => (
                            <div className="releaseGroup-items" key={rating.mbid}>
                                <img className="rating-img"
                                     src={`https://coverartarchive.org/release-group/${rating.releaseMbid}/front`}
                                     alt="placeholder.png"
                                     onClick={() => {
                                         navigate(`/music/album/${rating.releaseMbid}`)
                                     }}
                                />
                                <h3 className="rating-title"
                                    key={rating.title}
                                    onClick={() => {
                                        navigate(`/music/album/${rating.releaseMbid}`)
                                    }}
                                >{rating.title} </h3>
                                <h5 className = "format" key = {rating.format}>
                                    • {rating.format.charAt(0).toUpperCase() + rating.format.slice(1)}
                                </h5>
                                <h4 className="rating-artist"
                                    key={rating.artistName}
                                    onClick={() => {
                                        navigate(`/music/artist/${rating.artistMbid}`)
                                    }}
                                >{rating.artistName}</h4>
                                <div className="rating-value">
                                    <FaStar className = "star-profile"/>
                                    <h4 className= {
                                        rating.rating == 10 ? "rating-value-ten" :
                                            rating.rating >= 8 && rating.rating <= 9? "rating-value-high" :
                                                rating.rating >= 6 && rating.rating <= 7 ? "rating-value-med" :
                                                    rating.rating >= 4 && rating.rating <= 5 ? "rating-value-medlow" :
                                                            rating.rating >= 1 && rating.rating <= 3 ? "rating-value-low" :
                                                                "rating-value-zero"

                                    } key = {rating.id}>
                                        {rating.rating}/10
                                    </h4>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}