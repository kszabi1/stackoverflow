import React, {useState} from 'react';
import axios from 'axios';
import '../css/login.css';

export default function LoginPopup({ onClose, ref }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('/api/login', {
            username,
            password,
        }).then((response) => {
            localStorage.setItem('token', response.data.id);
            onClose();
        });
    };

    return (
        <div className="login-popup" ref={ref}>
            <form onSubmit={handleSubmit} className="login-form">
                <label className="login-label">
                    <p className="login-label-text">Username</p>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="login-input" />
                </label>
                <label className="login-label">
                    <p className="login-label-text">Password</p>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="login-input" />
                </label>
                <div className="login-button-container">
                    <button type="submit" className="login-button">Submit</button>
                </div>
            </form>
        </div>
    );
}