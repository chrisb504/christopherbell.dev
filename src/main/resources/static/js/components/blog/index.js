import React from 'react';
import ReactDOM from 'react-dom';
import Blog from './Blog';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blog'))) {
        ReactDOM.render(<Blog />, document.getElementById('blog'));
    }
});
