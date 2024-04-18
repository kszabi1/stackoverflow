import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";

import Navbar from "./Navbar";
import Home from "./pages/Home";
import Questions from "./pages/Questions";
import Question from "./components/Question";
import Ask from "./pages/Ask";

const ProtectedRoute = ({ Component, ...rest }) => {
  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/" />;
  }

  return <Component {...rest} />;
};

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
            <Route
                path="/ask"
                element={<ProtectedRoute Component={Ask} />}
            />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;