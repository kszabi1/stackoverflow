import React, { useState, useRef } from 'react';
import axios from 'axios';
import '../css/login.css';

export default function LoginPopup({ onClose, onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginStatus, setLoginStatus] = useState('');
    const ref = useRef();

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('/api/user/login', {
            username,
            password,
        }).then((response) => {
            console.log(response);
            if (response.status === 200 && response.data !== undefined) {
                localStorage.setItem('token', response.data);
                onLogin();
                onClose();
            }
        }).catch(() => {
            setLoginStatus('Login failed. Please check your username and password.');
        });
    };

    const handleCreateAccount = (e) => {
        e.preventDefault();
        axios.post('/api/user/', {
            username,
            password,
        }).then((response) => {
            console.log(response);
            if (response.status === 200 && response.data !== undefined) {
                localStorage.setItem('token', response.data);
                onLogin();
                onClose();
            }
        }).catch(() => {
            setLoginStatus('Registration failed.');
        });
    }


    return (
        <div className="login-popup" ref={ref} onMouseEnter={() => ref.current.focus()} onMouseLeave={onClose}>
            <form onSubmit={handleSubmit} className="login-form">
                <label className="login-label">
                    <p className="login-label-text">Username</p>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} className="login-input" />
                </label>
                <label className="login-label">
                    <p className="login-label-text">Password</p>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="login-input" />
                </label>
                <div className="login-status">
                    <p className="login-status-txt">{loginStatus ? loginStatus :
                        <button type="button" onClick={handleCreateAccount} className="login-button">Create Account</button>}</p>
                </div>
                <div className="login-button-container">
                    <button type="submit" className="login-button">Login</button>
                </div>
            </form>
        </div>
    );
}
