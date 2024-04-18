import React, { useState } from 'react'

const AskForm = ({ onSave }) => {
    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("");

    async function onSubmit(e) {
        e.preventDefault();
        return onSave({
            title,
            description
        })
    }



    return (
        <div className='askForm'>
            <form onSubmit={onSubmit}>
                <label>
                    Question: <br />
                    <textarea name="postContent" rows={4} cols={40} onChange={(e) => setTitle(e.target.value)} />
                </label> <br />
                <label>
                    Description: <br />
                    <textarea name="postContent" rows={4} cols={40} onChange={(e) => setDescription(e.target.value)} />
                </label> <br />
                <button type='submit'>Post question</button>
            </form>
        </div>
    )
}

export default AskForm