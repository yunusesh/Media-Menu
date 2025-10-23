import './App.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom' // Allows for routing through different page files
import { Home } from './pages/Home'
import { Movies } from './pages/Movies'
import { Music } from './pages/Music'
import { TV } from './pages/TV'
import { Layout } from './Layout';
import {Artist} from "./pages/music-pages/Artist";
import {Album} from "./pages/music-pages/Album";
import {Track} from "./pages/music-pages/Track";
import {QueryClient, QueryClientProvider} from "react-query";
import {ReactQueryDevtools} from "react-query/devtools";
import {Profile} from "./pages/Profile";

function App(){
    const queryClient = new QueryClient();

  return(
      <QueryClientProvider client = {queryClient}>
    <Router>
      <Layout/>
      <Routes>
          <Route path = "/" element = {<Home/>}/>
          <Route path = "/music" element = {<Music/>}/>
          <Route path = "/movies" element = {<Movies/>}/>
          <Route path = "/tv" element = {<TV/>}/>
          <Route path = "/music/artist/:id" element = {<Artist/>}/>
          <Route path = "/music/album/:id" element = {<Album/>}/>
          <Route path = "/music/track/:id" element = {<Track/>}/>
          <Route path = "/user" element = {<Profile/>}/>
      </Routes>
    </Router>
          <ReactQueryDevtools/>
      </QueryClientProvider>
  )
  
}

export default App