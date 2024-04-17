import React from 'react'
import "./css/Navbar.css"

const Navbar = () => {
  return (
    <div className='navbar'>
      <div className='navbarTitle'>
        <h3 className='pageTitle'>QoverFlow</h3>
      </div>
        <p className='navbarText'>Home</p>
        <p className='navbarText'>Questions</p>
    </div>
  )
}

export default Navbar;