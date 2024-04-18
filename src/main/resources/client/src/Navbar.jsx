import React, {useRef, useState} from 'react';
import { Link } from 'react-router-dom';
import LoginPopup from './components/LoginPopup';
import "./css/Navbar.css"

const Navbar = () => {
    const [showPopup, setShowPopup] = useState(false);
    const popupRef = useRef(null);

    const handleLoginClick = () => {
        setShowPopup(true);
    };

    const handleClosePopup = () => {
        setShowPopup(false);
    };

    const handleClickOutside = (e) => {
        if (popupRef.current && !popupRef.current.contains(e.target)) {
            handleClosePopup();
        }
    };

    React.useEffect(() => {
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    return (
        <div className='navbar'>
            <div className='navbarTitle'>
                <h3 className='pageTitle'>QoverFlow</h3>
            </div>
            <Link to="/" className="navbarText">Home</Link>
            <Link to="/questions" className="navbarText">Questions</Link>
            <button onClick={handleLoginClick} className="navbarText">Login</button>
            {showPopup && <LoginPopup ref={popupRef} onClose={handleClosePopup} />}
        </div>
    )
}

export default Navbar;