import WelcomePage from "./pages/WelcomePage";
import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import Layout from "./pages/Layout";
import Bikes from "./pages/Bikes";
import Cyclists from "./pages/Cyclists";
import Trips from './pages/Trips';
import { GoogleOAuthProvider } from "@react-oauth/google";
import TripDetail from "./pages/TripDetail";

function App() {
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (response) => {
    // Example: Decoding the JWT to extract user info (optional)
    const token = response.credential;
    const userInfo = parseJwt(token); // Decodes the token to get user info
    setUser(userInfo); // Set the user state
    console.log("User Info:", userInfo);
  };

  const parseJwt = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map((c) => `%${`00${c.charCodeAt(0).toString(16)}`.slice(-2)}`)
        .join('')
    );

    return JSON.parse(jsonPayload);
  };

  return (
    <GoogleOAuthProvider clientId={import.meta.env.VITE_OAUTH_CLIENT_ID}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout user={user} />}>
            <Route path="/" element={<WelcomePage />}/>
            <Route path="/bikes" element={<Bikes />}/>
            <Route path="/cyclists" element={<Cyclists />}/>
            <Route path="/trips" element={<Trips />} />
            <Route path="/trips/:id" element={<TripDetail />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GoogleOAuthProvider>
  )
}

export default App
