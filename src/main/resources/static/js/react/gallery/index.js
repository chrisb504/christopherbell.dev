import React from 'react';
import ReactDOM from 'react-dom';
import Blog from './Gallery';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('gallery'))) {
        ReactDOM.render(<Blog />, document.getElementById('gallery'));
    }
});
