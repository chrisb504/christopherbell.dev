import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import CryGallery from '../components/cries/crygallery.js'
import CryPoster from '../components/forms/cryposter.js'

class Home extends Component {
  render() {
    return (
        <div id="home">
          <CryPoster />
          <CryGallery />
        </div>
    );
  }
}

export default Home;