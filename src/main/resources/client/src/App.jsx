import { BrowserRouter, Route, Routes } from "react-router-dom";

import Navbar from "./Navbar";
import Home from "./pages/Home";
import Questions from "./pages/Questions";
import Question from "./components/Question";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <div className="pages">
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/questions" element={<Questions/>}/>
            <Route path="/question/:id" element={<Question/>}/>
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;