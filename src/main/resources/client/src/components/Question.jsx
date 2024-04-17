import { useEffect, useState } from "react"
import {useParams} from "react-router-dom"


async function fetchQuestion(id){
    return fetch(`/api/question/${id}`).then((res) => res.json())
}

const Question = () => {
    const [question, setQuestion] = useState(null);

    const {id} = useParams()

    useEffect(() => {
      fetchQuestion(id).then((question) => setQuestion(question))
      console.log(question)
    }, [])
    
if(question){
  return(
    <div>
      <h1>{question.title}</h1>
      <h3>{question.description}</h3>
    </div>
  )
}
 return "Loading..."
}

export default Question