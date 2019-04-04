import React from 'react';
import ReactDOM from 'react-dom';
import BlogTags from './BlogTags';

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blogtags'))) {
        ReactDOM.render(<BlogTags />, document.getElementById('blogtags'));
    }
});
