import React, { useState, useRef } from 'react'
import "./SearchBar.css"
import { FaSearch } from "react-icons/fa"

export const SearchBar = ({ setResults }) => {
    const [input, setInput] = useState("");
    const debounceTimeout = useRef(null); // store timeout ID

    const fetchData = (value) => {
        if (!value || value.trim() === "") {
            setResults([]); //set this to recently searched later!
            return;
        }

        fetch(`http://localhost:8081/artists/${value}`)
            .then((response) => response.json())
            .then((json) => {
                if (json.artists) {
                    setResults(json.artists);
                } else {
                    setResults([]);
                }
            })
            .catch(() => setResults([]));
    };

    const handleChange = (value) => {
        setInput(value);

        // Clear any previous timeout before setting a new one
        if (debounceTimeout.current) {
            clearTimeout(debounceTimeout.current);
        }

        // Wait 400ms after typing stops before calling fetch
        debounceTimeout.current = setTimeout(() => {
            fetchData(value);
        }, 400);
    };

    return (
        <div className="input-wrapper">
            <FaSearch id="search-icon" />
            <input
                placeholder="Type to search..."
                value={input}
                onChange={(e) => handleChange(e.target.value)}
            />
        </div>
    );
}