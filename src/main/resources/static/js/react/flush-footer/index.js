import React from 'react';
import ReactDOM from 'react-dom';
import Footer from './Footer';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('flush-footer'))) {
        ReactDOM.render(<Footer />, document.getElementById('flush-footer'));
    }
});
