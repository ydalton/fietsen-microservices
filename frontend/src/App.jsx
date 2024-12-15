import WelcomePage from "./pages/WelcomePage";
import { BrowserRouter, Routes, Route } from "react-router";
import Layout from "./pages/Layout";
import Bikes from "./pages/Bikes";
import Cyclists from "./pages/Cyclists";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<WelcomePage />}/>
          <Route path="/bikes" element={<Bikes />}/>
          <Route path="/cyclists" element={<Cyclists />}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
