import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import LoginPopup from './components/LoginPopup';
import "./css/Navbar.css"

const Navbar = () => {
    const [showPopup, setShowPopup] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLoginClick = () => {
        setShowPopup(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLoggedIn(false);
    };

    const handleClosePopup = () => {
        setShowPopup(false);
    };


    useEffect(() => {
        if (localStorage.getItem('token') !== null) {
            setIsLoggedIn(true);
        }
    }, []);

    return (
        <div className='navbar'>
            <div className='navbarTitle'>
                <h3 className='pageTitle'>QoverFlow</h3>
            </div>
            <Link to="/" className="navbarText">Home</Link>
            <Link to="/questions" className="navbarText">Questions</Link>
            {isLoggedIn? (
                <button onClick={handleLogout} className="navbarText login-button">
                    Logout
                </button>
            ) : (
                <button onClick={handleLoginClick} className="navbarText login-button">
                    Login / Sign Up
                </button>
            )}
            {showPopup && <LoginPopup onClose={handleClosePopup} onLogin={() => setIsLoggedIn(true)}/>}
        </div>
    )
}

export default Navbar;