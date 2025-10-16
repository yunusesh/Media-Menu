# Media Menu

A **work in progress** website that tracks digital **music**, **movie**, and **TV** consumption by integrating with multiple streaming service APIs.  
> Currently, only the **music** portion is functional.

---

##  Tech Stack

### Backend
- Java  
- Spring Boot  

### Frontend
- JavaScript  
- HTML / CSS  
- React.js  

---

##  How It Works

### Backend
- Connects to the **MusicBrainz API** to retrieve information about artists, albums, and tracks  
- Uses **Caffeine** to cache lookups on the backend for faster fetches from the frontend  
- Connects to the **Spotify API** to get user listening data  
- Sends this information to the frontend for display  
> **Flow:** MusicBrainz API → Backend → Frontend  

### Frontend
- Uses **React Router** for dynamic page rendering  
- Caches recently visited pages locally with **React Query**  

---

##  Installation Guide

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/media-menu.git
   cd media-menu
Install React dependencies

npm install react-dom@^19.1.1 \
            react-icons@^5.5.0 \
            react-query@^3.39.3 \
            react-router@^7.9.3 \
            react-router-dom@^7.8.2 \
            react-scripts@5.0.1
Set up PostgreSQL server

Configure environment

Fill in required fields in:

application.properties (backend)

.env (frontend/backend as needed)
