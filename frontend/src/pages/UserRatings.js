import {useNavigate, useParams} from "react-router-dom";
import {useQuery} from "react-query";
import {useEffect, useState} from "react";
import {FaStar} from "react-icons/fa";
import "./UserRatings.css"

export function UserRatings(){
    const { username } = useParams()
    const navigate = useNavigate()
    const [ratings, setRatings] = useState([])

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
            setRatings(userRatings.reverse().map(rating => rating))
        }
    }, [userRatings]);

    return(
        <div className = "user-ratings-page">
            <div className = "user-ratings">
                {ratings.map(rating => (
                    <div className="ratings-page-item" key={rating.mbid}>
                        <img className="ratings-item-img"
                             src={`https://coverartarchive.org/release-group/${rating.releaseMbid}/front`}
                             alt="placeholder.png"
                             onClick={() => {
                                 navigate(`/music/album/${rating.releaseMbid}`)
                             }}
                        />
                        <div className="rating-value">
                            <h4 className={
                                rating.rating == 10 ? "rating-value-ten" :
                                    rating.rating >= 8 && rating.rating <= 9 ? "rating-value-high" :
                                        rating.rating >= 6 && rating.rating <= 7 ? "rating-value-med" :
                                            rating.rating >= 4 && rating.rating <= 5 ? "rating-value-medlow" :
                                                rating.rating >= 1 && rating.rating <= 3 ? "rating-value-low" :
                                                    "rating-value-zero"

                            } key={rating.id}>
                                <FaStar className="star-profile"/>
                                {rating.rating}/10
                            </h4>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}