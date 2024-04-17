import { useEffect, useState } from "react"


async function fetchQuestion(id){
    fetch(`/api/question/${id}`).then((res) => res.json())
}

const Question = () => {
    const [question, setQuestion] = useState(null);

    

    useEffect(() => {
      fetchQuestion().then((question) => setQuestion(question))
    }, [])
    

  return (
    <div>{question.title}</div>
  )
}

export default Question