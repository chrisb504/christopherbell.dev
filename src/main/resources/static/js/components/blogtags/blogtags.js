import React, { Component } from 'react';
import ReactDOM from 'react-dom';

/**
 * This component represents the tags that appear above the 
 * list of blogs on the blog page.
 */
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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blogtags'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<BlogTags />, document.getElementById('blogtags'));
    }
});
