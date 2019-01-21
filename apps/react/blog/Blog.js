import React, { Component } from 'react';

export default class Footer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [] 
        }
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
            <div class="blogArticle">
                {this.state.posts.map(posts =>
                    <div>
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
