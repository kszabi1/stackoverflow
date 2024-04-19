import React, { useState } from 'react';
import "../css/home.css";

const Home = () => {
  const [showImage, setShowImage] = useState(false);

  const toggleImage = () => {
    setShowImage(!showImage);
  };

  return (
      <div className="container">
        <h1 className="welcome fancy-text">Welcome on QoverFlow</h1>
        <div className="image-container">
          <img
              src={showImage ? "https://i.imgur.com/1q2rTJ7.png" : ""}
              alt="Spoiler Image"
              className={showImage ? "show-image" : "hide-image"}
          />
          <button onClick={toggleImage} className="spoiler-button">
            {showImage ? "Hide Image" : "Show Image"}
          </button>
        </div>
      </div>
  );
};

export default Home;
