import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useQuery} from "react-query";
import "./User.css"

export function User() {
    const navigate = useNavigate()
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const {username} = useParams()
    const [ratings, setRatings] = useState([])

    async function fetchUser(){
        const response = await fetch(`http://localhost:8081/user/${username}`)
        return response.json()
    }

    const { data:userData } = useQuery({
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
            <div className="top-5">
                <h1>Top 5</h1>
            </div>
            <div className="top-year">
                <h1>Top of {currentYear}</h1>
            </div>
            <div className="recently-listened">
                <h1>Recently Listened</h1>
            </div>
            <div className="ratings">
                <h1>Ratings</h1>
                {ratings.map(rating => (
                    <div className="rating" key={rating.mbid}>
                        <img className="rating-img"
                             src={`https://coverartarchive.org/release-group/${rating.mbid}/front`}
                             alt="placeholder.png"
                             onClick={() =>{
                                 navigate(`/music/album/${rating.mbid}`)
                             }}
                        />
                        <h3 className="rating-name">
                            {rating.rating}/10
                        </h3>
                    </div>
                ))}
            </div>

        </div>
    )
}