import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Answers from './Answers'
import '../css/questionPage.css'

const Question = () => {
    const [question, setQuestion] = useState(null);
    const [answer, setAnswer] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const { id } = useParams()

    useEffect(() => {
        fetchQuestion(id).then((question) => setQuestion(question))
        if (localStorage.getItem('token') !== null) {
            setIsLoggedIn(true);
        }
    }, [id])

    const handleSubmit =  (e) => {
        e.preventDefault();
        fetch(`/api/answer/${id}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                question_id: id,
                message: answer,
                user_id: localStorage.getItem('token'),
            }),
        }).then((res) => res.json())
        window.location.reload();
    }

    if(!question) {
        return "Loading question...";
    }

    return (
        <div className="question-page">
            <div className="question-container">
                <h1>{question.title}</h1>
                <h3>{question.description}</h3>
            </div>
            <div className="answers-container">
                <Answers questionId={id}/>
            </div>
            {isLoggedIn && (
                <form className="comment-box" onSubmit={handleSubmit}>
                    <textarea className='input-answer' placeholder='Write a answer' value={answer} onChange={(event) => setAnswer(event.target.value)}/>
                    <button className='button' type="submit">Submit answer</button>
                </form>
            )}
        </div>
    )
}

async function fetchQuestion(id) {
    return fetch(`/api/question/${id}`).then((res) => res.json())
}

export default Question;