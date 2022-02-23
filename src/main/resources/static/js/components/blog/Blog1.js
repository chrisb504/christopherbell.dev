import React, { Component } from 'react';
import ReactDOM from 'react-dom';

export default class Blog extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            tags: [],
            location: '/blog/post',
            tagLocation: '/blog/tag'
        };
        this.setPostLocation = this.setPostLocation.bind(this);
    }

    componentDidMount() {
        this.getData(this.state.location);
        this.getTags();
        setInterval(async () => {
            this.getData(this.state.location);
            this.getTags();
        }, 10000);
    }

    componentDidUpdate() {
    }

    getData(fetchLocation) {
        console.log("Getting data at ", fetchLocation);
        fetch(fetchLocation)
            // passes the response and returns json from response
            .then(res => res.json())
            // posts gets that return from the last then and puts in it state
            .then((blogPostResponse) => {
                let blogPosts = blogPostResponse.blogPostPayload;
                let posts = blogPosts.reverse();
                this.setState({ posts });
            });
    }

    getTags() {
        fetch(this.state.tagLocation)
            // passes the response and returns json from response
            .then(res => res.json())
            // posts gets that return from the last then and puts in it state
            .then(tagsResponse => {
                let tags = tagsResponse.blogTagPayload;
                this.setState({ tags });
            })
    }

    setPostLocation(e) {
        const location = `/blog/tag/${e.target.value}`;
        console.log("Setting to this location", location);
        if(e.target.value) {
            this.setState({ location: `/blog/tag/${e.target.value}` }, function() {
                console.log("PostLocation", this.state.location);
                this.getData(this.state.location);
            });
        } else {
            this.setState({ location: `/blog/post/` }, function() {
                console.log("PostLocation", this.state.location);
                this.getData(this.state.location);
            });
        }
        
    }

    render() {
        return (
            <div className="blog-container">
                <div class="blogTags-container">
                    <div class="blogTags">
                        <button class='btn btn-dark' onClick={this.setPostLocation} value=''>all</button>
                    </div>
                    {this.state.tags.map(tag => (
                        <div class="blogTags">
                            <button class='btn btn-dark' onClick={this.setPostLocation} value={tag}>{tag}</button>
                        </div>
                    ))}
                </div>
                    

                {this.state.posts.map(posts => (
                    <div class="blogArticle">
                        <h2 class="text-center">{posts.title}</h2>
                        <h5 class="text-center">Author: {posts.author}</h5>
                        <h5 class="text-center">{posts.date}</h5>
                        <hr/>
                        <pre>{posts.post}</pre>
                    </div>
                ))}
            </div>
        );
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blog'))) {
        ReactDOM.render(<Blog />, document.getElementById('blog'));
    }
});
