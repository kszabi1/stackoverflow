import AskForm from "../components/AskForm"
import { useNavigate } from "react-router-dom";
import "../css/ask.css"

function postQuestion(question) {
  return fetch("/api/question/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(question),
  }).then((res) => res.json())
}

const Ask = () => {

  const navigate = useNavigate();

  const handlePostQuestion = (question) => {
    postQuestion(question).then(() => navigate("/questions"))
  }

  return (
    <div className="askPage">
      <h2 className="title">Fill out the form with your question, and its descriprion.</h2>
      <AskForm onSave={handlePostQuestion} />
    </div>
  )
}

export default Ask