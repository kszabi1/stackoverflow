import { BrowserRouter, Route, Routes } from "react-router-dom";

import Navbar from "./Navbar";
import Home from "./pages/Home";
import Questions from "./pages/Questions";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <div className="pages">
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/questions" element={<Questions/>}/>
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;