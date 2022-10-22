import React, { Component } from 'react';
import ReactDOM from 'react-dom';

/**
 * This component represents the Gallery on the website. It should be used to display
 * images in large to mid 2 column fashion.
 */
export default class Gallery extends Component {
    constructor(props) {
        super(props);
        this.state = {
            images: [],
            location: '/api/photogallery/images',
        };
        this.getData = this.getData.bind(this);
    }

    /**
     * When the components loads, this method will call getData(), which
     * will fetch the image data from the service. It will then update the
     * state and reload the component.
     */
    componentDidMount() {
        this.getData(this.state.location);
    }

    getData(fetchLocation) {
        console.log("Getting data at ", fetchLocation);
        fetch(fetchLocation)
            // Passes the response and returns the response in JSON format.
            .then(res => res.json())
            // Passes in that JSON from the response. It will get the array of images from
            // that JSON and update the state.
            .then((galleryImagesResponse) => {
                let images = galleryImagesResponse.images;
                this.setState({ images });
            });
    }

    render() {
        return (
            <div className="container-fluid">
                <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 g-2">
                {this.state.images.map(image => (
                    <div class="col">
                        <img src={image.path} class="img-fluid rounded" alt=""></img>
                    </div>
                ))}
                </div>
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
    if (document.body.contains(document.getElementById('gallery'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<Gallery />, document.getElementById('gallery'));
    }
});
