import { useEffect, useState } from "react"

const Answers = ({ questionId }) => {
    const [answers, setAnswers] = useState(null);
    const [users, setUsers] = useState({});

    useEffect(() => {
        fetchAnswers(questionId).then((answers) => {
            setAnswers(answers);
            fetchUsers(answers.map((answer) => answer.user_id)).then((fetchedUsers) => {
                setUsers(fetchedUsers.reduce((acc, user) => ({...acc, [user.id]: user }), {}));
            });
        });
    }, [questionId])

    if(!answers) {
        return "Loading answers...";
    }

    if(!Array.isArray(answers) || answers.length === 0) {
        return "No answers yet.";
    }

    return (
        <div>
            {answers.map((answer) => {
                const { id, message, time, user_id } = answer;

                if(user_id in users) {
                    const user = users[user_id];

                    return (
                        <div key={id}>
                            <div className="answer-box">
                                <div className="user-info">
                                    <span className="username">User: {user.username} | </span>
                                    <span className="post-date">{new Date(time).toLocaleString()}</span>
                                </div>
                                <div className="answer-text">
                                    <h3>{message}</h3>
                                </div>
                            </div>
                        </div>);
                }

                return null;
            })}
        </div>
    )
}

async function fetchAnswers(id){
    return fetch(`/api/answer/all/${id}`).then((res) => res.json())
}

async function fetchUsers(ids){
    return fetch(`/api/user/by/${ids.join(',')}`).then((res) => res.json())
}

export default Answers;