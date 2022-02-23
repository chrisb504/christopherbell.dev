import React, { Component } from 'react';
import ReactDOM from 'react-dom';

/**
 * This component represents the list of blogs that will appear on the Blog page.
 */
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

    /**
     * Pulls from the blog service every 10 seconds for new data.
     */
    componentDidMount() {
        this.getData(this.state.location);
        this.getTags();
        setInterval(async () => {
            this.getData(this.state.location);
            this.getTags();
        }, 10000);
    }

    /**
     * Makes a GET call to the blog service for the list of blog 
     * data.
     */
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

    /**
     * Makes a GET call to the blog service to get a list of all tags for 
     * all Blog entries.
     */
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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('blog'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<Blog />, document.getElementById('blog'));
    }
});
