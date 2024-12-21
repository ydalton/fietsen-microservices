import WelcomePage from "./pages/WelcomePage";
import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import Layout from "./pages/Layout";
import Bikes from "./pages/Bikes";
import Cyclists from "./pages/Cyclists";
import Trips from './pages/Trips';
import { GoogleOAuthProvider } from "@react-oauth/google";
import TripDetail from "./pages/TripDetail";
import { RecoilRoot } from "recoil";
import 'leaflet/dist/leaflet.css';

function App() {

  return (
    <RecoilRoot>
      <GoogleOAuthProvider clientId={import.meta.env.VITE_OAUTH_CLIENT_ID}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route path="/" element={<WelcomePage />}/>
              <Route path="/bikes" element={<Bikes />}/>
              <Route path="/cyclists" element={<Cyclists />}/>
              <Route path="/trips" element={<Trips />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </GoogleOAuthProvider>
    </RecoilRoot>
  )
}

export default App
