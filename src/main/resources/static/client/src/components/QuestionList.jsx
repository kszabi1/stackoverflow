import React from 'react'
import { Link } from 'react-router-dom'
import "./QuestionList.css"

const QuestionList = ({ questions }) => {
    return (
        <div className='questionList'>
            <table>
                <tbody>
                    <tr>
                        <th className="titleText">Question</th>
                        <th className="titleText">Description</th>
                    </tr>
                    {questions.map((question) => {
                        return (
                            <tr key={question.id}>
                                <td>
                                    <Link to={`/question/${question.id}`}>
                                        {question.title}
                                    </Link>
                                </td>
                                <td>{question.description}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default QuestionList