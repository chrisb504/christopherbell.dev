import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('root2'))) {
        ReactDOM.render( < App / > , document.getElementById('root2'));
    }
});