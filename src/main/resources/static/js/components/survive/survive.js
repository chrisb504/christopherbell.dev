import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import Player from './models/Entity/Player.mjs';
import Constants from './util/Constants.mjs'
import Survive from './survive.js';

export default class Survive extends Component {
    constructor() {
        super();
        this.player = new Player();
    }

    render() {
        <div className="blog-container"></div>
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('survive'))) {
        ReactDOM.render(<Survive />, document.getElementById('survive'));
    }
});