import React from 'react';
import ReactDOM from 'react-dom';
import Nav from './Nav';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('nav'))) {
        ReactDOM.render(<Nav />, document.getElementById('nav'));
    }
});
