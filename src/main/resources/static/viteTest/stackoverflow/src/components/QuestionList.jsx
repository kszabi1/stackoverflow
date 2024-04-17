import React from 'react'

const QuestionList = ({ questions }) => {
    return (
        <div className='questionList'>
            <h1>Questions</h1>
            <table>
                <tbody>
                    <tr>
                        <th>Question</th>
                        <th>Description</th>
                    </tr>
                    {questions.map((question) => {
                        return (
                            <tr key={question.id}>
                                <th>{question.title}</th>
                                <th>{question.description}</th>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default QuestionList