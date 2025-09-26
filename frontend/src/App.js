import './App.css'
import { HashRouter as Router, Routes, Route} from 'react-router-dom' // Allows for routing through different page files
import { Home } from './pages/Home'
import { Movies } from './pages/Movies'
import { Music } from './pages/Music'
import { TV } from './pages/TV'
import { Layout } from './Layout'
import {useState} from "react";
import {SearchBar} from "./components/SearchBar";
import {SearchResult} from "./components/SearchResult";

function App(){
    //employ the navigation bar outside of routes so it does not hide the other components
  return(
    <Router>
      <Layout/>
      <Routes>
          <Route path = "/" element = {<Home/>}/>
          <Route path = "/Music" element = {<Music/>}/>
          <Route path = "/Movies" element = {<Movies/>}/>
          <Route path = "/TV" element = {<TV/>}/>
      </Routes>
    </Router>
  )
  
}

export default App