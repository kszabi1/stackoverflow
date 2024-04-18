import React, { useEffect, useState } from 'react'
import QuestionList from '../components/QuestionList.jsx'

async function fetchQuestions() {
  return fetch("/api/question/all").then((res) => res.json())
}

const Questions = () => {
  const [questions, setQuestions] = useState(null)

  useEffect(() => {
    fetchQuestions().then((questions) => setQuestions(questions))

  }, [])


  if (questions) {
    return <QuestionList questions={questions} />
  }
  return "Loading..."
}

export default Questions