import React, { useEffect, useState } from 'react'
import QuestionList from '../components/QuestionList.jsx'
import "../css/questions.css"

async function fetchQuestions() {
  return fetch("/api/question/all").then((res) => res.json())
}

async function fetchQuestionsFilteredBySearchPhrase(phrase) {
  return fetch(`/api/question/search/${phrase}`).then((res) => res.json())
}

const Questions = () => {
  const [questions, setQuestions] = useState(null)
  const [searchPhrase, setSearchPhrase] = useState("")


  useEffect(() => {
    if (!searchPhrase) {
      fetchQuestions().then((questions) => setQuestions(questions))
    } else {
      fetchQuestionsFilteredBySearchPhrase(searchPhrase).then((questions) => setQuestions(questions))
    }
  }, [searchPhrase])

  if (questions === null) {
    return "Loading..."
  }

  return (
    <div>
      <div className='searchBar'>
        <form className='searchForm' onSubmit={e => {
          e.preventDefault();
          setSearchPhrase("");
        }}>
          <label>Search:
            <input type="text" value={searchPhrase} onChange={e => setSearchPhrase(e.target.value)} />
          </label>
          <button type='submit'>clear</button>
        </form>
      </div>
      <div className='qList'>
        <QuestionList questions={questions} />
      </div>
    </div>
  )
}

export default Questions