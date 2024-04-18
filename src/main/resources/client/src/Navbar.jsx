import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import LoginPopup from './components/LoginPopup';
import "./css/Navbar.css"
import "./css/login.css"

const Navbar = () => {
    const [showPopup, setShowPopup] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLoginClick = () => {
        setShowPopup(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsLoggedIn(false);
        window.location.reload();
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
                <Link to="/" className='navbarText'><h3 className='pageTitle'>QoverFlow</h3></Link>
            </div>
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
            {showPopup && <LoginPopup onClose={handleClosePopup} onLogin={() => {setIsLoggedIn(true); window.location.reload();}}/>}
        </div>
    )
}

export default Navbar;