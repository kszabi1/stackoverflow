import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"


async function fetchQuestion(id) {
  return fetch(`/api/question/${id}`).then((res) => res.json())
}

async function fetchUser(id) {
  return fetch(`/api/user/${id}`).then((res) => res.json())
}

const Question = () => {
  const [question, setQuestion] = useState(null);
  const [user, setUser] = useState(null)

  const { id } = useParams()

  useEffect(() => {
    fetchQuestion(id)
      .then((q) => {
        setQuestion(q);
        return fetchUser(q.user_id);
      })
      .then((usr) => {
        setUser(usr);
      });
  }, []);

  if (question && user) {
    return (
      <div>
        <p>Asked by: {user.username}</p>
        <h1>{question.title}</h1>
        <h3>{question.description}</h3>
      </div>
    )
  }
  return "Loading..."
}

export default Question