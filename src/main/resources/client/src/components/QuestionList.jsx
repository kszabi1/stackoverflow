import { Link } from 'react-router-dom'
import "../css/QuestionList.css"
import {useEffect, useState} from "react";

const QuestionList = ({ questions }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        if (localStorage.getItem('token') !== null) {
            setIsLoggedIn(true);
        }
    }, []);

    return (
        <div className='questionList'>
            {isLoggedIn ? (<Link to="/ask">
            <button className='askButton'>
            <h3>Ask a question!</h3>
            </button>
            </Link>): null}
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