import React, { Component } from 'react';
import ReactDOM from 'react-dom';

export default class BlogTags extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: []
        };
    }

    componentDidMount() {
        fetch('/blog/post')
            // passes the response and returns json from response
            .then(res => res.json())
            // posts gets that return from the last then and puts in it state
            .then(posts => this.setState({posts}));
    }

    render() {
        return (
            <div class="blog-container">
                {this.state.posts.reverse().map(posts =>
                    <div class="blogArticle">
                        <h2 class="text-center">{posts.title}</h2>
                        <h5 class="text-center">Author: {posts.author}</h5>
                        <h5 class="text-center">{posts.date}</h5>
                        <hr/>
                        <pre>{posts.post}</pre>
                    </div>
                )}
            </div>
        );
    } 
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blogtags'))) {
        ReactDOM.render(<BlogTags />, document.getElementById('blogtags'));
    }
});
