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
    const [listened, setListened] = useState([])
    const [topOfYear, setTopOfYear] = useState([])
    const [last12Ratings, setLast12Ratings] = useState([])
    const [last6Listens, setLast6Listens] = useState([])

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

    async function fetchUserListens() {
        const response = await fetch(`http://localhost:8081/api/scrobble/user/${userData?.id}`)
        return response.json()
    }

    const {data: userListens} = useQuery({
        queryKey: ["userListens", userData?.id],
        queryFn: () => fetchUserListens(),
        enabled: !!userData?.id
    })

    useEffect(() => {
        if (userRatings) {
            setRatings(userRatings.reverse().map(rating => rating).slice(0, 12))
        }
    }, [userRatings]);

    useEffect(() => {
        if (userListens) {
            setListened(userListens.reverse().map(listen => listen).slice(0, 6))
        }
    }, [userListens])


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
                    <div className="category-releases">

                    </div>
                </div>
                <div className="profile-categories">
                    <h1 className="category">Top of {currentYear}</h1>
                    <div className="category-releases">

                    </div>
                </div>
                <div className="profile-categories">
                    <div className="category-header">
                        <h1 className="category">Recently Listened</h1>
                        <h3 className="see-more">SEE MORE</h3>
                    </div>
                    <div className="category-releases">
                        {listened.map(listen => (
                            <div className="releaseGroup-items" key={listen.trackMbid}>
                                <img className="profile-item-img"
                                     src={`https://coverartarchive.org/release-group/${listen.releaseMbid}/front`}
                                     alt="placeholder.png"
                                     onClick={() => {
                                         navigate(`/music/track/${listen.trackMbid}`)
                                     }}
                                />
                                <h3 className="profile-item-title"
                                    key={listen.trackId}
                                    onClick={() => {
                                        navigate(`/music/track/${listen.trackMbid}`)
                                    }}> {listen.trackTitle}</h3>
                                <h4 className="profile-item-artist"
                                    key={listen.artistName}
                                    onClick={() => {
                                        navigate(`/music/artist/${listen.artistMbid}`)
                                    }}
                                >{listen.artistName}</h4>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="profile-categories">
                    <div className="category-header">
                        <h1 className="category">Recently Rated</h1>
                        <h3 className="see-more">SEE MORE</h3>
                    </div>
                    <div className="category-releases">
                        {ratings.map(rating => (
                            <div className="releaseGroup-items" key={rating.mbid}>
                                <img className="profile-item-img"
                                     src={`https://coverartarchive.org/release-group/${rating.releaseMbid}/front`}
                                     alt="placeholder.png"
                                     onClick={() => {
                                         navigate(`/music/album/${rating.releaseMbid}`)
                                     }}
                                />
                                <div className="release-info">
                                    <h4 className="profile-item-title"
                                        key={rating.title}
                                        onClick={() => {
                                            navigate(`/music/album/${rating.releaseMbid}`)
                                        }}
                                    >{rating.title} </h4>
                                    <h5 className="format" key={rating.format}>
                                        {rating.format.charAt(0).toUpperCase() + rating.format.slice(1)}
                                    </h5>
                                    <h4 className="profile-item-artist"
                                        key={rating.artistName}
                                        onClick={() => {
                                            navigate(`/music/artist/${rating.artistMbid}`)
                                        }}
                                    >{rating.artistName}</h4>
                                </div>
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
            </div>
        </div>
    )
}