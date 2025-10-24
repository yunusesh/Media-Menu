import {useParams} from "react-router-dom";
import {AuthContext} from "../AuthContext";
import {useContext, useEffect, useState} from "react";
import {useQuery} from "react-query";

export function User() {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const { username } = useParams()
    const { user } = useContext(AuthContext)
    const [ratings, setRatings] = useState([])

    async function fetchUserRatings(){
        console.log(user)
        const response = await fetch(`http://localhost:8081/api/release-rating/user/${user.id}`)
        return response.json()
    }

    const { data, status } = useQuery({
        queryKey: ["user", user],
        queryFn: () => fetchUserRatings(),
        enabled: !!user
    })

    useEffect(() => {
        if(data){
            setRatings(data.map(rating => rating.rating))
        }
    }, []);

    if(status === 'loading'){
        return <p1>Loading...</p1>
    }

    if(status === 'error'){
        return <p1>Error!</p1>
    }

    return(
        <div className="user-page">
            <div className = "top-5">
                <h1>Top 5</h1>
            </div>
            <div className = "top-year">
                <h1>Top of {currentYear}</h1>
            </div>
            <div className = "recently-listened">
                <h1>Recently Listened</h1>
            </div>
            <div className = "Ratings">
                <h1>Ratings</h1>
                {ratings.map(rating => (
                    <div className = "rating" id = "rating.mbid">
                        {rating}
                    </div>
                    ))}
            </div>

        </div>
    )
}