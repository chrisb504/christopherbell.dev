import React from 'react';
import ReactDOM from 'react-dom';
import Gallery from './Gallery';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('gallery'))) {
        ReactDOM.render(<Gallery />, document.getElementById('gallery'));
    }
});
